package com.github.flombois;


import com.github.flombois.data.Record;
import com.github.flombois.parsing.DataParser;
import com.github.flombois.parsing.ParsingException;
import com.github.flombois.process.RecordProcessor;
import com.github.flombois.process.RecordValidationProcessor;
import com.github.flombois.reporting.InvalidRecordsReport;
import com.github.flombois.reporting.Report;
import com.github.flombois.validators.ValidationResult;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.lang.System.exit;

/**
 * CLI application to validate customer statements
 */
public class App {

    public static void main(String[] args) throws Exception {
        if (args.length == 0) exitWithError("No input");
        String filePath = args[0];
        InputFormat inputFormat = checkInputFile(filePath);
        if (!InputFormat.UNSUPPORTED.equals(inputFormat)) {
            processInputFile(filePath, inputFormat);
        } else {
            exitWithError("Cannot process %s: unsupported format", filePath);
        }
    }

    public static void processInputFile(String filePath, InputFormat inputFormat) throws IOException, ParsingException {
        DataParser<Record> parser = inputFormat.dataParser();
        File file = Paths.get(filePath).toFile();
        List<Record> records;
        try (InputStream stream = new BufferedInputStream(new FileInputStream(file))) {
            records = parser.parse(stream);
        }
        // Process and report
        RecordProcessor<Record> processor = new RecordValidationProcessor();
        Report<ValidationResult<Record>> resultReport = new InvalidRecordsReport();
        printf(resultReport.report((List) processor.process(records)));
    }

    /**
     * Check if the input file can be processed and will exit application if file is not considered suitable
     * for processing
     *
     * @param filePath The file path on the filesystem
     * @return The probed {@link InputFormat}
     * @throws IOException Thrown if an error occurs while probing file format
     */
    public static InputFormat checkInputFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);

        // Check file exists and is a regular file
        if (!Files.isRegularFile(path)) exitWithError("%s is not a regular file", filePath);

        // Check file is readable
        if (!Files.isReadable(path)) exitWithError("%s is not readable, check access privileges");

        // Check file format
        String contentType = Files.probeContentType(path);
        if (InputFormat.CSV.getSupportedMimeTypes().contains(contentType)) {
            return InputFormat.CSV;
        }
        if (InputFormat.XML.getSupportedMimeTypes().contains(contentType)) {
            return InputFormat.XML;
        }
        return InputFormat.UNSUPPORTED;
    }

    private static void printf(String format, Object... args) {
        System.out.printf(format, args);
    }

    private static void exitWithError(String format, Object... args) {
        printf(format, args);
        exit(1);
    }
}

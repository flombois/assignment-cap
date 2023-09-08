package com.github.flombois.parsing;

import com.github.flombois.data.Record;

import jakarta.xml.bind.annotation.*;
import java.util.List;


/**
 * Extend {@link XmlParser} to parse {@link Record}
 */
public class RecordXmlParser extends XmlParser<Record> {

    /**
     * Map the XML root element of the input file
     */
    @XmlRootElement
    private static class Records {

        @XmlElement(name = "record")
        protected List<Record> recordList;

        public List<Record> getRecords() {
            return recordList;
        }

        public void setRecords(List<Record> recordList) {
            this.recordList = recordList;
        }
    }

    public RecordXmlParser() {
        super(Records.class);
    }

    /**
     * Convert the parsed {@link Records} object to a list of {@link Record}
     * @param unmarshalledData An instance of {@link Records}
     * @return A list of {@link Record}
     * @throws ParsingException Thrown if the supplied object is not an instance of {@link Records}
     */
    @Override
    protected List<Record> convert(Object unmarshalledData) throws ParsingException {
        if(unmarshalledData instanceof Records) {
            return ((Records) unmarshalledData).getRecords();
        }
        throw new ParsingException("Unexpected XML input");
    }
}

package com.github.flombois.parsing;

import jakarta.xml.bind.JAXBContext;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Parse data from an XML formatted input stream
 * This implementation relies on the jaxb api
 * @param <T> Type of the parsed items
 */
public class XmlParser<T> implements DataParser<T> {

    private Class<?> marshallingType;

    private static final SAXParserFactory SAX_PARSER_FACTORY = SAXParserFactory.newInstance();

    static {
        try {
            // https://cheatsheetseries.owasp.org/cheatsheets/XML_External_Entity_Prevention_Cheat_Sheet.html#java
            SAX_PARSER_FACTORY.setFeature("http://xml.org/sax/features/external-general-entities", false);
            SAX_PARSER_FACTORY.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            SAX_PARSER_FACTORY.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            SAX_PARSER_FACTORY.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        } catch (ParserConfigurationException | SAXException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Constructor
     * @param marshallingType Type of the wrapper around the list of item to be parsed
     *                        Usually a java bean mapping the XML root element
     */
    public XmlParser(Class<?> marshallingType) {
        this.marshallingType = marshallingType;
    }

    /**
     * Parse the input stream and close it
     * @param inputStream The input stream
     * @return The parsed data as a list of items
     * @throws ParsingException Thrown if an error occurs while parsing
     */
    @Override
    public List<T> parse(InputStream inputStream) throws ParsingException {
        List<T> results;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(getMarshallingType());
            results = convert(jaxbContext.createUnmarshaller().unmarshal(secureXMLSource(inputStream)));
        } catch (Exception e) {
            throw new ParsingException(e);
        }
        return results;
    }

    /**
     * Ensure the XML data source is parsed using a securely configured parser
     * @see <a href="https://cheatsheetseries.owasp.org/cheatsheets/XML_External_Entity_Prevention_Cheat_Sheet.html">XML External Entity Prevention Cheat Sheet</a>
     * @param inputStream Untrusted data source
     * @return A {@link SAXSource} securely configured
     * @throws ParserConfigurationException see {@link SAXParserFactory#newSAXParser()}
     * @throws SAXException see {@link SAXParserFactory#newSAXParser()}
     */
    private SAXSource secureXMLSource(InputStream inputStream) throws ParserConfigurationException, SAXException {
        return new SAXSource(SAX_PARSER_FACTORY.newSAXParser().getXMLReader(),
                new InputSource(new BufferedReader(new InputStreamReader(inputStream))));

    }

    /**
     * Convert unmarshalled data into a list of parsed data
     * @param unmarshalledData The parsed object which type is specified by {@link XmlParser#marshallingType}
     * @return A list of parsed items
     * @throws ParsingException Thrown if an error occurs converting parsed data into a list of items
     */
    protected List<T> convert(Object unmarshalledData) throws ParsingException {
        return (List<T>) unmarshalledData;
    }

    public Class<?> getMarshallingType() {
        return marshallingType;
    }

}

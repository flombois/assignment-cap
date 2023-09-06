package com.github.flombois.parsing;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.InputStream;
import java.util.List;

public class XmlParser<T> implements DataParser<T> {

    private Class<?> marshallingType;

    public XmlParser(Class<?> marshallingType) {
        this.marshallingType = marshallingType;
    }

    @Override
    public List<T> parse(InputStream inputStream) throws ParsingException {
        List<T> results;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(getMarshallingType());

            results = convert(jaxbContext.createUnmarshaller().unmarshal(inputStream));
        } catch (JAXBException e) {
            throw new ParsingException(e);
        }
        return results;
    }

    protected List<T> convert(Object unmarshalledData) throws ParsingException {
        return (List<T>) unmarshalledData;
    }

    public Class<?> getMarshallingType() {
        return marshallingType;
    }

    public void setMarshallingType(Class<?> marshallingType) {
        this.marshallingType = marshallingType;
    }
}

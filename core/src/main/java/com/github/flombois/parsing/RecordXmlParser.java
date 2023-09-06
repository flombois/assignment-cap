package com.github.flombois.parsing;

import com.github.flombois.data.Record;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

public class RecordXmlParser extends XmlParser<Record> {

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

    @Override
    protected List<Record> convert(Object unmarshalledData) throws ParsingException {
        if(unmarshalledData instanceof Records) {
            return ((Records) unmarshalledData).getRecords();
        }
        throw new ParsingException("Unexpected XML input");
    }
}

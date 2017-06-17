package ru.sbt.parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.ValidationFailureException;
import org.springframework.oxm.XmlMappingException;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;

public class Parser {

    @Autowired
    private Jaxb2Marshaller marshaller;

    @SuppressWarnings("unchecked")
    public <T> T objectFromNotRootXmlElementString(String source, Class<T> targetClass) throws XmlMappingException {
        if (source == null) throw new ValidationFailureException("Source must not to be null");
        try {
            marshaller.setMappedClass(targetClass);
            return (T) marshaller.unmarshal(new StreamSource(new StringReader(source)));
        } finally {
            marshaller.setMappedClass(null);
        }
    }
}

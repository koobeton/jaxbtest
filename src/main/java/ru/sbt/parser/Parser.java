package ru.sbt.parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;

public class Parser {

    @Autowired
    private Jaxb2Marshaller marshaller;

    @SuppressWarnings("unchecked")
    public <T> T objectFromNotXmlRootElementString(String source, Class<T> targetClass) {
        marshaller.setMappedClass(targetClass);
        T result = (T) marshaller.unmarshal(new StreamSource(new StringReader(source)));
        marshaller.setMappedClass(null);
        return result;
    }
}

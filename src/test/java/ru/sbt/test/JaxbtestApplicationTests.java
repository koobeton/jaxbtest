package ru.sbt.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.oxm.XmlMappingException;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.context.junit4.SpringRunner;
import ru.sbt.parser.Parser;
import ru.sbt.xml.GetApplicationDetailsRs;

import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JaxbtestApplicationTests {

    private static final String GADRs_XML = "<GetApplicationDetailsRs><Application><applicationID>GADRs</applicationID></Application></GetApplicationDetailsRs>";
    private static final String GADRs_XML_WITH_DECLARATION = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><GetApplicationDetailsRs><Application><applicationID>GADRs with declaration</applicationID></Application></GetApplicationDetailsRs>";

    private static final String APPLICATION_XML = "<Application><applicationID>12345</applicationID></Application>";
    private static final String APPLICATION_XML_WITH_DECLARATION = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><Application><applicationID>12345 with declaration</applicationID></Application>";

    private static final String BAD_APPLICATION_XML = "Application><applicationID>12345</applicationID></Application>";

    @Autowired
    private Jaxb2Marshaller marshaller;

    @Autowired
    private Parser parser;

    @Test
    public void printMarshallerDetails() throws Exception {
        delimeter();

        System.out.println(marshaller.getContextPath());
        Stream.of(marshaller.getPackagesToScan()).forEach(System.out::println);
        Stream.of(marshaller.getClassesToBeBound()).forEach(System.out::println);
    }

    @Test
    public void testGADRsXml() throws Exception {
        delimeter();

        GetApplicationDetailsRs gadrs = (GetApplicationDetailsRs) marshaller.unmarshal(new StreamSource(new StringReader(GADRs_XML)));
        System.out.println(gadrs);
    }

    @Test
    public void testGADRsXmlWithDeclaration() throws Exception {
        delimeter();

        GetApplicationDetailsRs gadrs = (GetApplicationDetailsRs) marshaller.unmarshal(new StreamSource(new StringReader(GADRs_XML_WITH_DECLARATION)));
        System.out.println(gadrs);
    }

    @Test
    public void testApplicationXml() throws Exception {
        delimeter();

        GetApplicationDetailsRs.Application app = parser.objectFromNotXmlRootElementString(APPLICATION_XML, GetApplicationDetailsRs.Application.class);
        System.out.println(app);
    }

    @Test
    public void testApplicationXmlWithDeclaration() throws Exception {
        delimeter();

        GetApplicationDetailsRs.Application app = parser.objectFromNotXmlRootElementString(APPLICATION_XML_WITH_DECLARATION, GetApplicationDetailsRs.Application.class);
        System.out.println(app);
    }

    @Test(expected = XmlMappingException.class)
    public void testBadXml() throws Exception {
        parser.objectFromNotXmlRootElementString(BAD_APPLICATION_XML, GetApplicationDetailsRs.Application.class);
    }

    @Test(expected = XmlMappingException.class)
    public void testNullXml() throws Exception {
        parser.objectFromNotXmlRootElementString(null, GetApplicationDetailsRs.Application.class);
    }

    private void delimeter() {
        System.out.println("\n*****\n");
    }
}

package ru.sbt.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.oxm.XmlMappingException;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.context.junit4.SpringRunner;
import ru.sbt.parser.Parser;
import ru.sbt.xml.CommonApplication;
import ru.sbt.xml.GetApplicationDetailsRs;

import javax.xml.transform.stream.StreamSource;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static ru.sbt.test.util.FileUtils.readResourceAsString;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JaxbtestApplicationTests {

    @Value("classpath:xml/gadrs.xml")
    private Resource gadrsXml;
    @Value("classpath:xml/gadrs_with_declaration.xml")
    private Resource gadrsWithDeclarationXml;
    @Value("classpath:xml/application.xml")
    private Resource applicationXml;
    @Value("classpath:xml/application_with_declaration.xml")
    private Resource applicationWithDeclarationXml;
    @Value("classpath:xml/bad_application.xml")
    private Resource badApplicationXml;

    @Autowired
    private Jaxb2Marshaller marshaller;

    @Autowired
    private Parser parser;

    @Test
    @SuppressWarnings("unchecked")
    public void testMarshallerDetails() throws Exception {
        assertNull(marshaller.getContextPath());
        assertThat(marshaller.getPackagesToScan(), arrayWithSize(1));
        assertThat(marshaller.getPackagesToScan(), arrayContaining("ru.sbt.xml"));
        assertThat(marshaller.getClassesToBeBound(), arrayWithSize(2));
        assertThat(marshaller.getClassesToBeBound(), arrayContaining(CommonApplication.class, GetApplicationDetailsRs.class));
    }

    @Test
    public void testGADRsXml() throws Exception {
        GetApplicationDetailsRs gadrs = (GetApplicationDetailsRs) marshaller.unmarshal(new StreamSource(gadrsXml.getFile()));
        assertNotNull(gadrs.getApplication());
        assertEquals("GADRs", gadrs.getApplication().getApplicationID());
    }

    @Test
    public void testGADRsXmlWithDeclaration() throws Exception {
        GetApplicationDetailsRs gadrs = (GetApplicationDetailsRs) marshaller.unmarshal(new StreamSource(gadrsWithDeclarationXml.getFile()));
        assertNotNull(gadrs.getApplication());
        assertEquals("GADRs with declaration", gadrs.getApplication().getApplicationID());
    }

    @Test
    public void testApplicationXml() throws Exception {
        GetApplicationDetailsRs.Application app = parser.objectFromNotRootXmlElementString(readResourceAsString(applicationXml), GetApplicationDetailsRs.Application.class);
        assertEquals("12345", app.getApplicationID());
    }

    @Test
    public void testApplicationXmlWithDeclaration() throws Exception {
        GetApplicationDetailsRs.Application app = parser.objectFromNotRootXmlElementString(readResourceAsString(applicationWithDeclarationXml), GetApplicationDetailsRs.Application.class);
        assertEquals("12345 with declaration", app.getApplicationID());
    }

    @Test(expected = XmlMappingException.class)
    public void testBadXml() throws Exception {
        parser.objectFromNotRootXmlElementString(readResourceAsString(badApplicationXml), GetApplicationDetailsRs.Application.class);
    }

    @Test(expected = XmlMappingException.class)
    public void testNullXml() throws Exception {
        parser.objectFromNotRootXmlElementString(null, GetApplicationDetailsRs.Application.class);
    }
}

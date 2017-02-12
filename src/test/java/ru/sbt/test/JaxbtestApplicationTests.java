package ru.sbt.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.sbt.xml.Application;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JaxbtestApplicationTests {

    @Autowired
    private Application application;

    @Test
    public void testApplication() {
        System.out.println(application);
    }
}

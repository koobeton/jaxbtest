package ru.sbt.test.util;

import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.file.Files;

import static java.util.stream.Collectors.joining;

public class FileUtils {

    public static String readResourceAsString(Resource resource) throws IOException {
        return Files.lines(resource.getFile().toPath()).collect(joining());
    }
}

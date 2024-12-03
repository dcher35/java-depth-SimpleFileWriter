package com.edu.chmnu.ki_123.c3;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

class SimpleFileWriterTest {

    private final String testFilePath = "src/test_user_data.txt";

    @BeforeEach
    void setup() throws IOException {
        Files.createFile(Path.of(testFilePath));
    }

    @AfterEach
    void cleanup() throws IOException {
        Files.deleteIfExists(Path.of(testFilePath));
    }

    @Test
    void testFileWritingAndReading() throws IOException {
        String name = "Dmytro";
        String age = "21";
        String hobby = "Learn programming";
        String expectedOutput = String.format("Name: %s, Age: %s, Hobby: %s%n", name, age, hobby);

        try (var writer = new FileWriter(testFilePath, true)) {
            writer.write(expectedOutput);
        }

        String fileContents;
        try (var linesStream = Files.lines(Path.of(testFilePath))) {
            fileContents = linesStream.reduce("", (line1, line2) -> line1 + line2 + System.lineSeparator());
        }

        assertEquals(expectedOutput, fileContents.trim() + System.lineSeparator());
    }

    @Test
    void testAppendingToFile() throws IOException {
        String initialData = "Name: Bob, Age: 19, Hobby: Hiking\n";
        String newData = "Name: Jane, Age: 20, Hobby: Painting\n";

        try (var writer = new FileWriter(testFilePath, true)) {
            writer.write(initialData);
        }

        try (var writer = new FileWriter(testFilePath, true)) {
            writer.write(newData);
        }

        String fileContents = Files.readString(Path.of(testFilePath));
        String expectedOutput = initialData + newData;

        assertEquals(expectedOutput, fileContents);
    }
}
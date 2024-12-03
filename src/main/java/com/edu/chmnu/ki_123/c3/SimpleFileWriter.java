package com.edu.chmnu.ki_123.c3;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class SimpleFileWriter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filePath = "src/user_data.txt";

        try (FileWriter writer = new FileWriter(filePath, true)) {
            System.out.println("Enter your name:");
            String name = scanner.nextLine();

            System.out.println("Enter your age:");
            String age = scanner.nextLine();

            System.out.println("Enter your favorite hobby:");
            String hobby = scanner.nextLine();

            String userData = String.format("Name: %s, Age: %s, Hobby: %s%n", name, age, hobby);

            writer.write(userData);
            System.out.println("Data successfully written to file!");

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

        System.out.println("Contents of the file:");
        try (var linesStream = Files.lines(Path.of(filePath))) {
            linesStream.forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }
}
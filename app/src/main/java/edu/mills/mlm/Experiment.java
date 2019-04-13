package edu.mills.mlm;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

// Code for experimenting with try-with-resources.
public class Experiment {
    String getFileContents(String inputFile) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(inputFile))) {
            while (scanner.hasNext()) {
                sb.append(scanner.nextLine());
            }
            return sb.toString();
        }
    }

    void printFile(String inputFile) throws IOException {
        try (Scanner scanner = new Scanner(new File(inputFile))) {
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }
        }
    }

    void copyFile(String inputFile, String outputFile) throws IOException {
        try (Scanner scanner = new Scanner(new File(inputFile));
             PrintWriter writer = new PrintWriter(new File(outputFile))) {
            while (scanner.hasNext()) {
                writer.print(scanner.nextLine());
            }
        }
    }
}

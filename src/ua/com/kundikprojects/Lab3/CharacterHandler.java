package ua.com.kundikprojects.Lab3;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class CharacterHandler {
    //Java 7 source level
    public static void main(String[] args) {
        handleFile("E:\\workspace\\PracticeOOP\\src\\ua\\com\\kundikprojects\\Lab3\\test.txt", "E:\\workspace\\PracticeOOP\\src\\ua\\com\\kundikprojects\\Lab3\\made.txt");
    }

    private static void handleFile(String inputFilename, String outputFilename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilename));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilename))) {
            int r;
            while ((r = reader.read()) != -1) {
                char ch = (char) r;

                if (ch == '\n' || ch == '\r')
                    continue;

                if (ch == '\t')
                    ch = ' ';

                writer.write(ch);

//                System.out.print(ch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

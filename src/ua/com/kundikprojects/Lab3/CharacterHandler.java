package ua.com.kundikprojects.Lab3;

import java.io.*;

public class CharacterHandler {

    public static void handleFile(String inputFilename, String outputFilename) {
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

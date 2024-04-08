package gui;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileWriterHelper {

    public static void saveUser(String firstName, String lastName) {
        try (FileWriter writer = new FileWriter("users.txt", true)) {
            writer.write(firstName + " " + lastName + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveResult(String firstName, String lastName, String result) {
        try (FileWriter writer = new FileWriter("results.txt", true)) {
            writer.write(firstName + " " + lastName + ": " + result + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void removeUserChoice(String firstName, String lastName, String choice) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("results.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith(firstName + " " + lastName + ": " + choice)) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("results.txt"))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


package gui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileReaderHelper {
 public static Map<String, Integer> readResults(String firstName, String lastName, String[] options) {
     Map<String, Integer> optionCounts = new HashMap<>();
     try (BufferedReader reader = new BufferedReader(new FileReader("results.txt"))) {
         String line;
         while ((line = reader.readLine()) != null) {
             if (line.startsWith(firstName + " " + lastName)) {
                 String choice = line.substring(line.lastIndexOf(":") + 1).trim();
                 optionCounts.put(choice, optionCounts.getOrDefault(choice, 0) + 1);
             }
         }
     } catch (IOException e) {
         e.printStackTrace();
     }
     return optionCounts;
 }

 public static Map<String, Integer> readAllResults(String[] options) {
     Map<String, Integer> optionCounts = new HashMap<>();
     try (BufferedReader reader = new BufferedReader(new FileReader("results.txt"))) {
         String line;
         while ((line = reader.readLine()) != null) {
             String choice = line.substring(line.lastIndexOf(":") + 1).trim();
             optionCounts.put(choice, optionCounts.getOrDefault(choice, 0) + 1);
         }
     } catch (IOException e) {
         e.printStackTrace();
     }
     return optionCounts;
 }
}

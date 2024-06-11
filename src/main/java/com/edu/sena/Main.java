package com.edu.sena;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            FileReader fileReader = new FileReader("C:\\Users\\Aprendiz\\Documents\\analisistexto\\src\\main\\java\\com\\edu\\sena\\Texto1.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            int wordCount = 0;
            int sentenceCount = 0;
            int charCount = 0;
            Map<String, Integer> wordFrequency = new HashMap<>();

            // List of common connectors to exclude
            String[] connectors = {"lo", "y", "es", "la", "de", "el", "en", "un", "una", "unos", "unas"};

            while ((line = bufferedReader.readLine())!= null) {
                String[] words = line.split("\\s+");
                wordCount += words.length;

                for (String word : words) {
                    // Convert to lowercase to ignore case
                    word = word.toLowerCase();

                    // Check if the word is not a connector
                    boolean isConnector = false;
                    for (String connector : connectors) {
                        if (word.equals(connector)) {
                            isConnector = true;
                            break;
                        }
                    }

                    if (!isConnector) {
                        wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
                    }
                }

                String[] sentences = line.split("[!?.:]+");
                sentenceCount += sentences.length;
                charCount += line.length();
            }

            String mostFrequentWord = null;
            int maxFrequency = 0;
            for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
                if (entry.getValue() > maxFrequency) {
                    maxFrequency = entry.getValue();
                    mostFrequentWord = entry.getKey();
                }
            }

            double averageWordLength = (double) charCount / wordCount;

            System.out.println("Total word count: " + wordCount);
            System.out.println("Total sentence count: " + sentenceCount);
            System.out.println("Most frequent word: " + mostFrequentWord);
            System.out.println("Average word length: " + averageWordLength);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
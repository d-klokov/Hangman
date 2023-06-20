package ru.klokov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

public class RandomWordProvider {
    private static final int WORDS_NUMBER = 479;
    private final InputStream wordsFile;
    public RandomWordProvider() {
        this.wordsFile = this.getClass().getClassLoader().getResourceAsStream("words.txt");
    }

    public String getRandomWord() {
        String word = "";
        int num = new Random().nextInt(WORDS_NUMBER + 1);

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(wordsFile))) {
            for (int i = 0; i < num; i++) {
                word = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return word;
    }
}
package ru.klokov;

import java.util.Arrays;

public class Game {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED = "\u001B[31m";
    private static final int GAME_STEPS = 6;
    private static final int FIELD_WIDTH = 80;

    private final RandomWordProvider wordProvider;
    private final ConsoleUserInput gameInput;
    private final ConsoleRenderer consoleRenderer;
    private char[] errorChars;
    private char[] correctChars;
    private int errorsCounter;
    private int correctCharsCounter;
    private final int fieldWidth;
    private int wordLength;
    private boolean result;
    private boolean gameFinished;
    private final String word;

    public Game() {
        this.wordProvider = new RandomWordProvider();
        this.gameInput = new ConsoleUserInput();
        this.consoleRenderer = new ConsoleRenderer(this);
        this.fieldWidth = FIELD_WIDTH;
        this.wordLength = 0;
        this.word = this.wordProvider.getRandomWord();
        this.gameFinished = false;
    }

    public void start() {
        String wordCopy = word;
        wordLength = word.length();
        errorsCounter = 0;
        correctCharsCounter = 0;
        errorChars = new char[wordLength];
        correctChars = new char[wordLength];
        gameFinished = false;
        result = false;

        Arrays.fill(errorChars, ' ');
        Arrays.fill(correctChars, '_');

        while (!gameFinished) {
            consoleRenderer.render(errorsCounter);

            System.out.println(ANSI_BLACK_BACKGROUND + ANSI_RED + "  Буква: " +
                    " ".repeat(FIELD_WIDTH - 9) + ANSI_RESET);

            char current = gameInput.getKeyValue();
            String currentStr = String.valueOf(current).toLowerCase();

            if (wordCopy.contains(currentStr)) {
                int index = wordCopy.indexOf(currentStr);
                correctChars[index] = current;
                correctCharsCounter++;
                wordCopy = wordCopy.replaceFirst(currentStr, "_");

            } else {
                errorChars[errorsCounter++] = current;
            }

            if (errorsCounter == GAME_STEPS) {
                gameFinished = true;
            }

            if (correctCharsCounter == wordLength) {
                gameFinished = true;
                result = true;
            }
        }

        consoleRenderer.render(errorsCounter);
    }

    public char[] getErrorChars() {
        return errorChars;
    }

    public char[] getCorrectChars() {
        return correctChars;
    }

    public int getErrorsCounter() {
        return errorsCounter;
    }

    public int getFieldWidth() {
        return fieldWidth;
    }

    public int getWordLength() { return wordLength; }

    public boolean getResult() {
        return result;
    }

    public String getWord() {
        return word;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }
}

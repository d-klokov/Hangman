package ru.klokov;

import java.util.Scanner;

public class ConsoleUserInput {

    private final Scanner scanner;

    public ConsoleUserInput() {
        this.scanner = new Scanner(System.in);
    }

    public char getKeyValue() {
        return this.scanner.nextLine().toUpperCase().charAt(0);
    }
}

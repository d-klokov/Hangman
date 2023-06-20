package ru.klokov;

public class Main {
    public static void main(String[] args) {
        ConsoleUserInput appInput = new ConsoleUserInput();

        char inputChar;
        do {
            System.out.println("Введите 'Н' чтобы начать игру или 'В' чтобы выйти из приложения:");
            inputChar = appInput.getKeyValue();
            if (inputChar == 'Н') {
                new Game().start();
            }
        } while (inputChar != 'В');
        System.out.println("Завершение работы!");
    }
}
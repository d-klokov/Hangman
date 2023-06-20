package ru.klokov;

public class ConsoleRenderer {
    private static final String[][] IMAGE = new String[][]{
            {" ".repeat(20), "     ----------     ", "    |          |    ", "               |    ",
                    "               |    ", "               |    ", "               |    ",
                    "               |    ", "  _____________|__  ", " ".repeat(20)},
            {" ".repeat(20), "     ----------     ", "    |          |    ", "    O          |    ",
                    "               |    ", "               |    ", "               |    ",
                    "               |    ", "  _____________|__  ", " ".repeat(20)},
            {" ".repeat(20), "     ----------     ", "    |          |    ", "    O          |    ",
                    "   /           |    ", "               |    ", "               |    ",
                    "               |    ", "  _____________|__  ", " ".repeat(20)},
            {" ".repeat(20), "     ----------     ", "    |          |    ", "    O          |    ",
                    "   / \\         |    ", "               |    ", "               |    ",
                    "               |    ", "  _____________|__  ", " ".repeat(20)},
            {" ".repeat(20), "     ----------     ", "    |          |    ", "    O          |    ",
                    "   /|\\         |    ", "               |    ", "               |    ",
                    "               |    ", "  _____________|__  ", " ".repeat(20)},
            {" ".repeat(20), "     ----------     ", "    |          |    ", "    O          |    ",
                    "   /|\\         |    ", "   /           |    ", "               |    ",
                    "               |    ", "  _____________|__  ", " ".repeat(20)},
            {" ".repeat(20), "     ----------     ", "    |          |    ", "    O          |    ",
                    "   /|\\         |    ", "   / \\         |    ", "               |    ",
                    "               |    ", "  _____________|__  ", " ".repeat(20)}
    };

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED = "\u001B[31m";

    private final Game game;

    public ConsoleRenderer(Game game) {
        this.game = game;
    }

    public void render(int step) {
        String greetings = "Игра началась! Отгадайте слово из " + this.game.getWordLength() + " букв!";
        int spaces = (this.game.getFieldWidth() - greetings.length()) / 2;

        String formattedGreetings = " ".repeat(spaces) + greetings;
        if ((this.game.getFieldWidth() - greetings.length()) % 2 == 0) {
            formattedGreetings = formattedGreetings + " ".repeat(spaces);
        } else formattedGreetings = formattedGreetings + " ".repeat(spaces + 1);

        System.out.print("\033\143");
        System.out.println(ANSI_BLACK_BACKGROUND + ANSI_RED + formattedGreetings + ANSI_RESET);

        for (int i = 0; i < 10; i++) {
            String img = IMAGE[step][i];
            String info = getInfo(i);
            int resultStringSpaces = this.game.getFieldWidth() - img.length() - info.length();
            System.out.println(ANSI_BLACK_BACKGROUND + ANSI_RED + img + info + " ".repeat(resultStringSpaces) + ANSI_RESET);
        }

        String endMessage;
        if (this.game.getResult()) {
            endMessage = "Вы выиграли! Загаданное слово - " + this.game.getWord();
        } else endMessage = "Вы проиграли! Загаданное слово - " + this.game.getWord();

        spaces = (this.game.getFieldWidth() - endMessage.length()) / 2;
        String formattedEndMessage = " ".repeat(spaces) + endMessage;
        if ((this.game.getFieldWidth() - endMessage.length()) % 2 == 0) {
            formattedEndMessage = formattedEndMessage + " ".repeat(spaces);
        } else formattedEndMessage = formattedEndMessage + " ".repeat(spaces + 1);

        if (this.game.isGameFinished()) System.out.println(ANSI_BLACK_BACKGROUND + ANSI_RED + formattedEndMessage + ANSI_RESET);
    }

    private String getInfo(int i) {
        StringBuilder info = new StringBuilder();

        switch (i) {
            case 4:
                info.append("Слово: ").append(getCorrectAnswersString(this.game.getCorrectChars()));
                break;
            case 5:
                info.append("Ошибки (").append(this.game.getErrorsCounter()).append("): ")
                        .append(getErrorsString(this.game.getErrorChars()));
                break;
        }
        return info.toString();
    }

    private String getCorrectAnswersString(char[] arr) {
        StringBuilder sb = new StringBuilder();
        for (char c : arr) sb.append(c);
        return sb.toString();
    }

    private String getErrorsString(char[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (i > 0 && arr[i] != ' ' && this.game.getErrorsCounter() > 1) sb.append(", ");
            sb.append(arr[i]);
        }
        return sb.toString();
    }
}

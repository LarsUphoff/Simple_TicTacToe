package tictactoe;

import java.util.Scanner;

public class Game {
    private final Scanner scanner;
    private final Grid grid;
    private String rowCoordinate;
    private String columnCoordinate;
    private char playingCharacter;

    public Game() {
        this.scanner = new Scanner(System.in);
        this.grid = new Grid();
    }

    public void startGame() {
        playingCharacter = 'O';                     //Changes to 'X' at the start of the loop
        grid.printGrid();
        while (!gameIsFinished()) {
            changePlayer();
            do {
                System.out.println("Enter the coordinates: ");
                rowCoordinate = scanner.next();
                columnCoordinate = scanner.next();
            } while (!userInputIsCorrect());
            grid.updateGrid(rowCoordinate, columnCoordinate, playingCharacter);
            grid.printGrid();
        }
    }

    private void changePlayer() {
        if (playingCharacter == 'O') {
            playingCharacter = 'X';
        } else {
            playingCharacter = 'O';
        }
    }

    public boolean gameIsFinished() {
        if (gameResultIsImpossible()) {
            System.out.println("Impossible");
            return true;
        } else if (winnerExists()) {
            System.out.println(playingCharacter + " wins");
            return true;
        } else if (!grid.containsEmptyCells() && !winnerExists()) {
            System.out.println("Draw");
            return true;
        }
        return false;
    }

    public boolean gameResultIsImpossible() {
        int sumX = 0;
        int sumO = 0;
        for (char[] row : grid.getTicTacToeGrid()) {
            for (char cell : row) {
                if (cell == 'X') sumX++;
                else if (cell == 'O') sumO++;
            }
        }
        return Math.abs(sumO - sumX) > 1;
    }

    public boolean winnerExists() {
        return horizontalWin(playingCharacter) || verticalWin(playingCharacter) || diagonalWin(playingCharacter);
    }

    public boolean horizontalWin(char ch) {
        int sum = ch;
        for (char[] row : grid.getTicTacToeGrid()) {
            for (char cell : row) {
                if (cell == ch) sum++;
            }
            if (sum == ch + 3) break;
            else sum = ch;
        }
        return sum == ch + 3;
    }

    public boolean verticalWin(char ch) {
        int sum = ch;
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                if (grid.getCellValue(j, i) == ch) sum++;
            }
            if (sum == ch + 3) break;
            else sum = ch;
        }
        return sum == ch + 3;
    }

    public boolean diagonalWin(char ch) {
        return (grid.getCellValue(0, 0) == ch && grid.getCellValue(1, 1) == ch && grid.getCellValue(2, 2) == ch)
                || grid.getCellValue(0, 2) == ch && grid.getCellValue(1, 1) == ch && grid.getCellValue(2, 0) == ch;
    }

    public boolean userInputIsCorrect() {
        int intRowCoordinate;
        int intColumnCoordinate;
        try {
            intRowCoordinate = Integer.parseInt(rowCoordinate) - 1;
            intColumnCoordinate = Integer.parseInt(columnCoordinate) - 1;
        } catch (Exception e) {
            System.out.println("You should enter numbers!");
            return false;
        }
        if (intRowCoordinate > 2 || (intRowCoordinate < 0) || (intColumnCoordinate > 2) || (intColumnCoordinate < 0)) {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        } else if (grid.getCellValue(intRowCoordinate, intColumnCoordinate) == 'X' || grid.getCellValue(intRowCoordinate, intColumnCoordinate) == 'O') {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }
        return true;
    }


}

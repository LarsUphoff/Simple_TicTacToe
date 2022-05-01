package tictactoe;

import java.util.Scanner;

public class Game {
    private final Scanner scanner = new Scanner(System.in);
    private final Grid grid = new Grid();
    private String row;
    private String column;
    private char currentPlayer;
    private int sumOfCharacters = 0;

    public Game() {
        currentPlayer = 'O';
    }

    public void startGame() {
        grid.printGrid();
        while (gameIsInProgress()) {
            changePlayer();
            getCoordinates();
            grid.updateGrid(row, column, currentPlayer);
            grid.printGrid();
        }
    }

    public boolean gameIsInProgress() {
        if (gameResultIsImpossible()) {
            System.out.println("Impossible");
            return false;
        } else if (gameHasAWinner()) {
            System.out.println(currentPlayer + " wins");
            return false;
        } else if (gameIsADraw()) {
            System.out.println("Draw");
            return false;
        }
        return true;
    }

    private boolean gameResultIsImpossible() {
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

    private boolean gameHasAWinner() {
        return horizontalWin() || verticalWin() || diagonalWin();
    }

    public boolean horizontalWin() {
        for (char[] row : grid.getTicTacToeGrid()) {
            sumOfCharacters = 0;
            for (char cell : row) {
                if (cellEqualsCurrentPlayersMark(cell)) sumOfCharacters++;
            }
            if (playerHasThreeMarksInARow()) return true;
        }
        return false;
    }

    private boolean cellEqualsCurrentPlayersMark(char cell) {
        return cell == currentPlayer;
    }

    private boolean playerHasThreeMarksInARow() {
        return sumOfCharacters == 3;
    }

    public boolean verticalWin() {
        for (int i = 0; i <= 2; i++) {
            sumOfCharacters = 0;
            for (int j = 0; j <= 2; j++) {
                if (cellEqualsCurrentPlayersMark(grid.getCellValue(j, i))) sumOfCharacters++;
            }
            if (playerHasThreeMarksInARow()) return true;
        }
        return false;
    }

    public boolean diagonalWin() {
        return (cellEqualsCurrentPlayersMark(grid.getCellValue(0, 0))) && (cellEqualsCurrentPlayersMark(grid.getCellValue(1, 1))) && (cellEqualsCurrentPlayersMark(grid.getCellValue(2, 2)))
                || (cellEqualsCurrentPlayersMark(grid.getCellValue(0, 2))) && (cellEqualsCurrentPlayersMark(grid.getCellValue(1, 1))) && (cellEqualsCurrentPlayersMark(grid.getCellValue(2, 0)));
    }

    private boolean gameIsADraw() {
        return !grid.containsEmptyCells() && !gameHasAWinner();
    }

    private void changePlayer() {
        if (currentPlayer == 'O') {
            currentPlayer = 'X';
        } else {
            currentPlayer = 'O';
        }
    }

    private void getCoordinates() {
        do {
            System.out.println("Enter the coordinates: ");
            row = scanner.next();
            column = scanner.next();
        } while (!userInputIsCorrect());
    }

    public boolean userInputIsCorrect() {
        int intRowCoordinate;
        int intColumnCoordinate;
        try {
            intRowCoordinate = Integer.parseInt(row) - 1;
            intColumnCoordinate = Integer.parseInt(column) - 1;
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

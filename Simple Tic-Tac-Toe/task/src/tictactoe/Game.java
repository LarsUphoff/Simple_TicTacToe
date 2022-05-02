package tictactoe;

import java.util.Scanner;

public class Game {
    private final Scanner scanner = new Scanner(System.in);
    private final Grid grid = new Grid();
    private int row;
    private int column;
    private char currentPlayer;
    private int sumOfCharacters = 0;
    private final int numberOfRows = 3;
    private final int numberOfColumns = 3;
    private boolean userInputViolatesExpectedFormat;

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

    private boolean gameIsInProgress() {
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
        int sumX = 0, sumO = 0;
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

    private boolean horizontalWin() {
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

    private boolean verticalWin() {
        for (int i = 0; i < numberOfColumns; i++) {
            sumOfCharacters = 0;
            for (int j = 0; j < numberOfRows; j++) {
                if (cellEqualsCurrentPlayersMark(grid.getCellMark(j, i))) sumOfCharacters++;
            }
            if (playerHasThreeMarksInARow()) return true;
        }
        return false;
    }

    private boolean diagonalWin() {
        return (cellEqualsCurrentPlayersMark(grid.getCellMark(0, 0))) && (cellEqualsCurrentPlayersMark(grid.getCellMark(1, 1))) && (cellEqualsCurrentPlayersMark(grid.getCellMark(2, 2)))
                || (cellEqualsCurrentPlayersMark(grid.getCellMark(0, 2))) && (cellEqualsCurrentPlayersMark(grid.getCellMark(1, 1))) && (cellEqualsCurrentPlayersMark(grid.getCellMark(2, 0)));
    }

    private boolean gameIsADraw() {
        return !grid.containsEmptyCells() && !gameHasAWinner();
    }

    private void changePlayer() {
        currentPlayer = currentPlayer == 'O' ? 'X' : 'O';
    }

    private void getCoordinates() {
        do {
            System.out.println("Enter the coordinates: ");
            try {
                row = Integer.parseInt(scanner.next()) - 1;
                column = Integer.parseInt(scanner.next()) - 1;
            } catch (Exception e) {
                userInputViolatesExpectedFormat = true;
            }
        } while (userInputIsIncorrect());
    }

    private boolean userInputIsIncorrect() {
        if (userInputViolatesExpectedFormat) {
            System.out.println("You should enter numbers!");
            userInputViolatesExpectedFormat = false;
            return true;
        } else if (coordinatesAreOutOfRange()) {
            System.out.println("Coordinates should be from 1 to 3!");
            return true;
        } else if (cellIsOccupied()) {
            System.out.println("This cell is occupied! Choose another one!");
            return true;
        }
        return false;
    }

    private boolean coordinatesAreOutOfRange() {
        return (row > 2) || (row < 0) || (column > 2) || (column < 0);
    }

    private boolean cellIsOccupied() {
        return grid.getCellMark(row, column) == 'X' || grid.getCellMark(row, column) == 'O';
    }

}

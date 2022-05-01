package tictactoe;

public class Grid {

    private final char[][] ticTacToeGrid;

    public Grid() {
        this.ticTacToeGrid = new char[3][3];
        setEmptyGrid();
    }

    public char[][] getTicTacToeGrid() {
        return ticTacToeGrid;
    }

    public char getCellValue(int row, int column) {
        return ticTacToeGrid[row][column];
    }

    private void setEmptyGrid() {
        for (int i = 0, charIndex = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++, charIndex++) {
                ticTacToeGrid[i][j] = ' ';
            }
        }
    }

    public void printGrid() {
        System.out.println("---------");
        System.out.println("| " + ticTacToeGrid[0][0] + " " + ticTacToeGrid[0][1] + " " + ticTacToeGrid[0][2] + " |");
        System.out.println("| " + ticTacToeGrid[1][0] + " " + ticTacToeGrid[1][1] + " " + ticTacToeGrid[1][2] + " |");
        System.out.println("| " + ticTacToeGrid[2][0] + " " + ticTacToeGrid[2][1] + " " + ticTacToeGrid[2][2] + " |");
        System.out.println("---------");
    }

    public void updateGrid(String rowCoordinate, String columnCoordinate, char playingCharacter) {
        ticTacToeGrid[Integer.parseInt(rowCoordinate) - 1][Integer.parseInt(columnCoordinate) - 1] = playingCharacter;
    }

    public boolean containsEmptyCells() {
        for (char[] row : ticTacToeGrid) {
            for (char cell : row) {
                if (cell == ' ') return true;
            }
        }
        return false;
    }
}

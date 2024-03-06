package sudoku2.sudoku;


public class SudokuSolver {

    private static final int N = 9;

    public boolean solveSudoku(int[][] board) {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isValidMove(board, row, col, num)) {
                            board[row][col] = num;
                            if (solveSudoku(board)) {
                                return true;
                            }
                            board[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValidMove(int[][] board, int row, int col, int num) {
        // Check if num is not already used in the current row, current column, and current 3x3 grid
        return !usedInRow(board, row, num) && !usedInColumn(board, col, num) && !usedInBox(board, row - row % 3, col - col % 3, num);
    }

    private boolean usedInRow(int[][] board, int row, int num) {
        for (int col = 0; col < N; col++) {
            if (board[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    private boolean usedInColumn(int[][] board, int col, int num) {
        for (int row = 0; row < N; row++) {
            if (board[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    private boolean usedInBox(int[][] board, int boxStartRow, int boxStartCol, int num) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row + boxStartRow][col + boxStartCol] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void printBoard(int[][] board) {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] board = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        SudokuSolver solver = new SudokuSolver();
        if (solver.solveSudoku(board)) {
            System.out.println("Sudoku puzzle solved successfully:");
            printBoard(board);
        } else {
            System.out.println("No solution exists for the given Sudoku puzzle.");
        }
    }
}

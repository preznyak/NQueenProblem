import java.util.Arrays;

/**
 * @author Laszlo Preznyak
 */
public class HengeresNVezerProblema {

    private int boardSize;
    private int nrOfQueens;

    public HengeresNVezerProblema(int boardSize, int nrOfQueens) {
        this.boardSize = boardSize;
        this.nrOfQueens = nrOfQueens;
    }

    void printBoard(int board[][]) {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                System.out.print("|" + board[i][j] + "|");
            }
            System.out.println();
        }
    }

    boolean canBePutDown(int board[][], int row, int col) {
        int i, j;

        for (i = 0; i < col; i++) {
            if (board[row][i] == 1) {
                return false;
            }
        }

        for (i = row, j = col; j >= 0; i--, j--) {
            if (i >= 0) {
                if (board[i][j] == 1) {
                    return false;
                }
            } else {
                if (board[boardSize + i][j] == 1) {
                    return false;
                }
            }

        }

        for (i = row, j = col; j >= 0; i++, j--) {
            if (i < boardSize) {
                if (board[i][j] == 1) {
                    return false;
                }
            } else {
                if (board[i-boardSize][j] == 1) {
                    return false;
                }
            }
        }

        return true;
    }

    boolean solveRollerNQueenProblem(int board[][], int col, int q) {
    

        if ( q >= nrOfQueens) {
            return true;
        }

        for (int i = 0; i < boardSize; i++) {

            if (canBePutDown(board, i, col)) {
                board[i][col] = 1;
                q++;

                if (solveRollerNQueenProblem(board, col + 1,q) == true) {
                    return true;
                }

                board[i][col] = 0; // Backtrack
                q--;
            }
        }

        return false;
    }

    boolean solveNQ(int k) {
        int board[][] = new int[k][k];
        Arrays.stream(board).forEach(a -> Arrays.fill(a, 0));

        if (solveRollerNQueenProblem(board, 0, 0) == false) {
            System.out.print("Solution not exists.");
            return false;
        }

        printBoard(board);
        return true;
    }

    public static void main(String args[]) {
        int Q = 4, N = 5;
        HengeresNVezerProblema Queen = new HengeresNVezerProblema(N, Q);
        Queen.solveNQ(N);
    }
}
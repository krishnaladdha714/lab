public class Nqueens {

    public static boolean isSafe(int[][] board, int row, int col) {

        for (int i = 0; i < row; i++) {

            for (int j = 0; j < board.length; j++) {

                if (board[i][j] == 1) {

                    if (col == j || Math.abs(row - i) == Math.abs(col - j)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean solution(int[][] board, int row) {

        if (row == board.length)
            return true;

        for (int i = 0; i < board.length; i++) {

            if (isSafe(board, row, i)) {
                board[row][i] = 1;

                if (solution(board, row + 1)) {
                    return true;
                }
                board[row][i] = 0;
            }
        }
        return false;

    }

    public static void main(String[] args) {
        int n = 4;

        int[][] board = new int[n][n];

        if (solution(board, 0)) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    System.out.print(board[i][j]);
                }
                System.out.println();
            }
        } else {
            System.out.println("NO solution found");
        }

    }
}
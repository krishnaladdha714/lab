import java.util.*;

public class TicTacToeAI {
    public static char player = 'O', opponent = 'X';
    char[][] board;

    TicTacToeAI() {
        board = new char[3][3];
        intializeBoard();
    }

    public void intializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i < 2) {
                System.out.println("---------");
            }

        }
    }

    public boolean isWon(char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player)
                return true;
        }
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player)
                return true;
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player)
            return true;

        if (board[0][2] == player && board[1][1] == player && board[2][0] == player)
            return true;

        return false;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ')
                    return false;
            }
        }
        return true;
    }

    public boolean isGameOver() {
        return isWon(player) || isWon(opponent) || isBoardFull();
    }

    public int minmax(char currplayer, int depth) {
        if (isWon(player))
            return 1;
        if (isWon(opponent))
            return -1;
        if (isBoardFull())
            return 0;

        int bestScore = (currplayer == opponent) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = currplayer;
                    int score = minmax(currplayer == player ? opponent : player, depth + 1);
                    board[i][j] = ' ';
                    if (currplayer == player) {
                        bestScore = Math.max(score, bestScore);
                    }
                    if (currplayer == opponent) {
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
        }
        return bestScore;
    }

    public Move findBestMove() {
        int bestScore = Integer.MAX_VALUE;
        Move bestMove = null;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = opponent;
                    int score = minmax(player, 0);
                    board[i][j] = ' ';
                    if (score < bestScore) {
                        bestScore = score;
                        bestMove = new Move(i, j);
                    }
                }
            }
        }
        return bestMove;
    }

    public class Move {
        int x;
        int y;

        Move(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        TicTacToeAI game = new TicTacToeAI();
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to TicTacToe AI");
        while (!game.isGameOver()) {
            game.printBoard();
            System.out.println();
            System.out.println("Enter your Move");
            int x = sc.nextInt();
            int y = sc.nextInt();
            if (game.board[x][y] == ' ') {
                game.board[x][y] = player;
            } else {
                System.out.println("Invalid move. Cell is already occupied. Try again.");
                continue;
            }
            if (game.isWon(player)) {
                game.printBoard();
                System.out.println("Congratulations! You win!");
                break;
            }
            if (game.isBoardFull()) {
                game.printBoard();
                System.out.println("It's a draw!");
                break;
            }
            Move move = game.findBestMove();
            int comx = move.x;
            int comy = move.y;

            game.board[comx][comy] = opponent;
            System.out.println("Computer's move: " + move.x + " " + move.y);
            if (game.isWon(opponent)) {
                game.printBoard();
                System.out.println("Comp wins! ");
                break;
            }

        }
        if (!game.isWon(player) && !game.isWon(opponent)) {
            game.printBoard();
            System.out.println("It's a draw!");
        }
        sc.close();
    }

}
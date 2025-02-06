import java.util.Scanner;

public class SudokuPlayer {
    private SudokuBoard board;
    private SudokuValidator validator;

    public SudokuPlayer(SudokuBoard board, SudokuValidator validator) {
        this.board = board;
        this.validator = validator;
    }

    public Boolean insertNumber(Integer row, Integer col, Integer num) {
        if (validator.isValidMove(board, row, col, num)) {
            board.setValue(row, col, num);
            return true;
        }
        return false;
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        while (!board.isComplete()) {
            board.printBoard();
            System.out.print("Informe a linha (0-8), a coluna (0-8) e o número (1-9): ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            int num = scanner.nextInt();
            
            if (!insertNumber(row, col, num)) {
                System.out.println("Movimento inválido. Tente novamente.");
            }
        }
        System.out.println("Tabuleiro completo!");
        board.printBoard();
        scanner.close();
    }
}

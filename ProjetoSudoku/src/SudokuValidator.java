public class SudokuValidator {
    private static final Integer SIZE = 9;
    
    public Boolean isValidMove(SudokuBoard board, Integer row, Integer col, Integer num) {
        // Verifica se o número já está na linha
        for (int i = 0; i < SIZE; i++) {
            if (board.getValue(row, i) != null && board.getValue(row, i).equals(num)) return false;
            if (board.getValue(i, col) != null && board.getValue(i, col).equals(num)) return false;
        }

        // Verifica se o número já está na caixa 3x3
        Integer boxRowStart = (row / 3) * 3;
        Integer boxColStart = (col / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getValue(boxRowStart + i, boxColStart + j) != null &&
                    board.getValue(boxRowStart + i, boxColStart + j).equals(num)) {
                    return false;
                }
            }
        }
        return true;
    }
}

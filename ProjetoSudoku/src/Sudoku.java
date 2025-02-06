public class Sudoku {
    public static void main(String[] args) {
        SudokuBoard board = new SudokuBoard();
        
        Difficulty difficulty = Difficulty.HARD;
        board.generateRandomBoard(difficulty);
        
        SudokuValidator validator = new SudokuValidator();
        SudokuPlayer player = new SudokuPlayer(board, validator);
        
        player.play();
    }
}

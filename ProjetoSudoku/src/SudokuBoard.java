import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SudokuBoard {
    private static final Integer SIZE = 9;
    private Integer[][] board = new Integer[SIZE][SIZE];
    private static final Random random = new Random();

    public SudokuBoard() {
        // Inicializa o tabuleiro com valores nulos
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = null;
            }
        }
    }

    // Método para imprimir o tabuleiro
    public void printBoard() {
        for (int i = 0; i < SIZE; i++) {
            if (i % 3 == 0 && i != 0) System.out.println("---------------------");
            for (int j = 0; j < SIZE; j++) {
                if (j % 3 == 0 && j != 0) System.out.print("| ");
                System.out.print(board[i][j] == null ? ". " : board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Método para obter o valor de uma célula
    public Integer getValue(Integer row, Integer col) {
        return board[row][col];
    }

    // Método para definir o valor de uma célula
    public void setValue(Integer row, Integer col, Integer value) {
        board[row][col] = value;
    }

    // Método para verificar se o tabuleiro está completo
    public Boolean isComplete() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == null) return false;
            }
        }
        return true;
    }

    // Método para gerar um tabuleiro de Sudoku válido (completo)
    public void generateValidBoard() {
        // Preenche o tabuleiro com um Sudoku resolvido (usando backtracking ou outro método)
        fillBoard();
    }

    // Método para gerar um tabuleiro aleatório com base na dificuldade
    public void generateRandomBoard(Difficulty difficulty) {
        generateValidBoard(); // Gera um tabuleiro completo e válido
        removeNumbers(difficulty); // Remove números aleatórios de acordo com a dificuldade
    }

    // Método auxiliar para preencher o tabuleiro (backtracking)
    private boolean fillBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == null) { // Encontrou um espaço vazio
                    ArrayList<Integer> numbers = new ArrayList<>();
                    for (int num = 1; num <= 9; num++) {
                        numbers.add(num);
                    }
                    Collections.shuffle(numbers); // Embaralha os números

                    for (Integer num : numbers) {
                        if (isValidMove(row, col, num)) {
                            board[row][col] = num;
                            if (fillBoard()) { // Recursivamente preenche o resto
                                return true;
                            }
                            board[row][col] = null; // Backtrack
                        }
                    }
                    return false; // Não conseguiu preencher este espaço
                }
            }
        }
        return true; // Preencheu todo o tabuleiro
    }

    // Método para verificar se um movimento é válido (já existente)
    public boolean isValidMove(Integer row, Integer col, Integer num) {
        // Verifica se o número já está na linha
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] != null && board[row][i].equals(num)) return false;
            if (board[i][col] != null && board[i][col].equals(num)) return false;
        }

        // Verifica se o número já está na caixa 3x3
        int boxRowStart = (row / 3) * 3;
        int boxColStart = (col / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[boxRowStart + i][boxColStart + j] != null &&
                    board[boxRowStart + i][boxColStart + j].equals(num)) {
                    return false;
                }
            }
        }
        return true;
    }

    // Método para remover números do tabuleiro conforme a dificuldade
    private void removeNumbers(Difficulty difficulty) {
        int totalCellsToRemove = difficulty.getValue() * 10; // A dificuldade influencia no número de células removidas
        for (int i = 0; i < totalCellsToRemove; i++) {
            int row = random.nextInt(SIZE);
            int col = random.nextInt(SIZE);
            while (board[row][col] == null) { // Garantir que não estamos removendo uma célula vazia
                row = random.nextInt(SIZE);
                col = random.nextInt(SIZE);
            }
            board[row][col] = null; // Remove o número
        }
    }
}

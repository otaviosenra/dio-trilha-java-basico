
public enum Difficulty {
    EASY(2),      
    MEDIUM(3),    
    HARD(4),      
    VERY_HARD(5); 

    private final int value;

    Difficulty(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

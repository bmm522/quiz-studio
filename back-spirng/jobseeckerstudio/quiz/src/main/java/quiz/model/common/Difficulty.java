package quiz.model.common;

public enum Difficulty {

    easy("easy"), hard("hard");

    private final String difficulty;

    Difficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String get() {
        return difficulty;
    }
}

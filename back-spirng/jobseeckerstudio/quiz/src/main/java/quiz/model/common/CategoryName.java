package quiz.model.common;

public enum CategoryName {

    java("java"),
    datastructure("datastructure");

    private final String category;

    CategoryName(String category) {this.category = category;}

    public String get(){
        return category;
    }
}

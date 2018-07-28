package model;

public class QuizModel {
    private String name;
    public QuizModel(){
    }

    public QuizModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

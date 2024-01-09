package com.example.befair;

public class ColumnModel {
    String answer;
    String columnA;
    String columnB;

    public ColumnModel(String answer, String columnA, String columnB) {
        this.answer = answer;
        this.columnA = columnA;
        this.columnB = columnB;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getColumnA() {
        return columnA;
    }

    public void setColumnA(String columnA) {
        this.columnA = columnA;
    }

    public String getColumnB() {
        return columnB;
    }

    public void setColumnB(String columnB) {
        this.columnB = columnB;
    }
}

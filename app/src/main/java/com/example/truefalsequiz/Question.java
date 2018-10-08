package com.example.truefalsequiz;

public class Question {
    String question;
    boolean answer;

    public boolean checkAnswer(boolean userAnswer)
    {
        if(answer = userAnswer)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}

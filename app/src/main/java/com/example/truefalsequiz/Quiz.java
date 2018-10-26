package com.example.truefalsequiz;

import java.util.List;

public class Quiz
{
    private List<Question> questions;
    private int score;
    private int currentQuestionNum;

    public Quiz(List<Question> questions) {
        this.questions = questions;
        score = 0;
        currentQuestionNum = 0;
    }

    public Question nextQuestion(){
        Question nextQuestion = null;
        if(isThereAnotherQuestion())
        {
            nextQuestion = questions.get(getCurrentQuestionNum());
        }
        currentQuestionNum++;
        return nextQuestion;
    }

    public boolean isThereAnotherQuestion(){
        if(getCurrentQuestionNum() == questions.size())
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getScore() {
        return score;
    }

    public int getCurrentQuestionNum() {
        return currentQuestionNum;
    }

    public boolean checkAnswer(boolean userAnswer)
    {
        if(questions.get(currentQuestionNum-1).isAnswer() == userAnswer)
        {
            score++;
            return true;
        }
        else
        {
            score--;
            return false;
        }
    }
}

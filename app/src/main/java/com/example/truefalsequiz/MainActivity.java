package com.example.truefalsequiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    public static final String TAG = "MainActivity";
    private TextView QuestionNumber;
    private TextView Question;
    private Button TRUE;
    private Button FALSE;

    private Quiz quiz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wireWidgets();

        InputStream inputStream = getResources().openRawResource(R.raw.questions);
        String question = readTextFile(inputStream);

        // create a gson object
        Gson gson = new Gson();
        // read your json file into an array of questions
        Question[] questions =  gson.fromJson(question, Question[].class);
        // convert your array to a list using the Arrays utility class
        List<Question> questionList = Arrays.asList(questions);
        // verify that it read everything properly
        Log.d(TAG, "onCreate: " + questionList.toString());


        quiz = new Quiz(questionList);
        runQuiz();
    }

    public void runQuiz() {
        /*setListeners();
        Question.setText(quiz.nextQuestion().getQuestion());
        QuestionNumber.setText(String.valueOf(quiz.getCurrentQuestionNum()));*/
        Question.setText(quiz.nextQuestion().getQuestion());
        QuestionNumber.setText(String.valueOf(quiz.getCurrentQuestionNum()));

        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button_mainActivity_true:
                        quiz.checkAnswer(true);
                        break;
                    case R.id.button_mainActivity_false:
                        quiz.checkAnswer(false);
                        break;
                }
                Toast.makeText(MainActivity.this, "Score = " + quiz.getScore(), Toast.LENGTH_LONG).show();
                com.example.truefalsequiz.Question nextQuestion = quiz.nextQuestion();
                if(nextQuestion != null) {
                    Question.setText(nextQuestion.getQuestion());
                    QuestionNumber.setText(String.valueOf(quiz.getCurrentQuestionNum()));
                }
            }
        };
        TRUE.setOnClickListener(listener);
        FALSE.setOnClickListener(listener);
    }

    /*public void setListeners(){
        TRUE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quiz.checkAnswer(true);
                Toast.makeText(MainActivity.this, "Score = " + quiz.getScore(), Toast.LENGTH_LONG).show();
                Question quiz.nextQuestion();
            }
        });
        FALSE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quiz.checkAnswer(false);
                Toast.makeText(MainActivity.this, "Score = " + quiz.getScore(), Toast.LENGTH_LONG).show();
            }
        });
    }*/

    private void wireWidgets() {
        Question = findViewById(R.id.textView_mainActivity_question);
        QuestionNumber = findViewById(R.id.textView_mainActivity_questionNum);
        TRUE = findViewById(R.id.button_mainActivity_true);
        FALSE = findViewById(R.id.button_mainActivity_false);
    }

    public String readTextFile(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte buf[] = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {

        }
        return outputStream.toString();
    }
}

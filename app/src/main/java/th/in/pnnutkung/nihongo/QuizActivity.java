package th.in.pnnutkung.nihongo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {

    private ArrayList<Quiz> quiz;
    private int currentQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        initInstance();
    }

    private void initInstance() {
        quiz = getIntent().getParcelableArrayListExtra("quiz");
        this.currentQuiz = 0;
        int unitNumber = getIntent().getIntExtra("unitNumber", 0);
        TextView quizTitle = findViewById(R.id.tv_quiz_title);
        quizTitle.setText(String.format("Unit %d quiz", unitNumber));
        setCurrentQuiz(currentQuiz);
        final Button next = findViewById(R.id.btn_next_quiz);
        final Button prev = findViewById(R.id.btn_prev_quiz);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentQuiz+1 < quiz.size()) {
                    prev.setEnabled(true);
                    currentQuiz++;
                    setCurrentQuiz(currentQuiz);
                    if (currentQuiz+1 == quiz.size()) {
                        next.setEnabled(false);
                    }
                } else {
                    next.setEnabled(false);
                }
            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentQuiz-1 >= 0) {
                    next.setEnabled(true);
                    currentQuiz--;
                    setCurrentQuiz(currentQuiz);
                    if (currentQuiz == 0) {
                        prev.setEnabled(false);
                    }
                } else {
                    prev.setEnabled(false);
                }
            }
        });
    }

    private void setCurrentQuiz(int quizIndex) {
        TextView quizQuestion = findViewById(R.id.tv_quiz_question);
        RadioButton answer1 = findViewById(R.id.rb_quiz_answer1);
        RadioButton answer2 = findViewById(R.id.rb_quiz_answer2);
        RadioButton answer3 = findViewById(R.id.rb_quiz_answer3);
        RadioButton answer4 = findViewById(R.id.rb_quiz_answer4);
        quizQuestion.setText(quiz.get(quizIndex).getQuestion());
        answer1.setText(quiz.get(quizIndex).getAnswers()[0]);
        answer2.setText(quiz.get(quizIndex).getAnswers()[1]);
        answer3.setText(quiz.get(quizIndex).getAnswers()[2]);
        answer4.setText(quiz.get(quizIndex).getAnswers()[3]);
    }
}

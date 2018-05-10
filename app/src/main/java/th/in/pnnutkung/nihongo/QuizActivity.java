package th.in.pnnutkung.nihongo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import th.in.pnnutkung.nihongo.fragment.QuizFragment;

public class QuizActivity extends AppCompatActivity {

    private int score = 0;
    private ArrayList<Quiz> quiz;
    private int currentQuiz;
    private TextView quizTitle;
    private TextView tvScore;
    private TextView tvQuizNo;
    private Button next;
    private Button prev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        initInstance();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.quiz_content_container,
                            QuizFragment.newInstance(0))
                    .commit();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("score", this.score);
        outState.putInt("currentQuiz", this.currentQuiz);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.score = savedInstanceState.getInt("score");
        this.currentQuiz = savedInstanceState.getInt("currentQuiz");
        if (this.currentQuiz == 0) {
            prev.setEnabled(false);
        }
        if (this.currentQuiz == 9) {
            next.setEnabled(false);
        }
        tvScore.setText(String.format("Score: %d/10" ,this.score));
        tvQuizNo.setText(String.format("Quiz %d/10", this.currentQuiz+1));
    }

    @SuppressLint("DefaultLocale")
    private void initInstance() {
        tvScore = findViewById(R.id.tv_quiz_score);
        quiz = getIntent().getParcelableArrayListExtra("quiz");
        this.currentQuiz = 0;
        int unitNumber = getIntent().getIntExtra("unitNumber", 0);
        quizTitle = findViewById(R.id.tv_quiz_title);
        tvQuizNo = findViewById(R.id.tv_quiz_no);
        tvQuizNo.setText(String.format("Quiz %d/10", this.currentQuiz+1));
        quizTitle.setText(String.format("Unit %d quiz", unitNumber));
        next = findViewById(R.id.btn_next_quiz);
        prev = findViewById(R.id.btn_prev_quiz);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentQuiz+1 < quiz.size()) {
                    prev.setEnabled(true);
                    currentQuiz+=1;
                    addNextQuiz(currentQuiz);
                    updateQuizNo();
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
                    currentQuiz-=1;
                    removeThisQuiz();
                    updateQuizNo();
                    if (currentQuiz == 0) {
                        prev.setEnabled(false);
                    }
                } else {
                    prev.setEnabled(false);
                }
            }
        });
        Button close = findViewById(R.id.btn_close_quiz);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public Quiz getCurrentQuiz(int index) {
        return this.quiz.get(index);
    }

    public void setSelectAnswer(int quizIndex, String answer) {
        this.quiz.get(quizIndex).setSelectedAnswer(answer);
    }

    private void addNextQuiz(int index) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.quiz_content_container,
                        QuizFragment.newInstance(index))
                .addToBackStack(null)
                .commit();
    }

    private void removeThisQuiz() {
        getSupportFragmentManager().popBackStack();
    }

    public void addScore() {
        this.score+=1;
        updateScore();
    }

    private void updateScore() {
        tvScore.setText(String.format("Score: %d/10" ,this.score));
    }

    private void updateQuizNo() {
        tvQuizNo.setText(String.format("Quiz %d/10", this.currentQuiz+1));
    }
}

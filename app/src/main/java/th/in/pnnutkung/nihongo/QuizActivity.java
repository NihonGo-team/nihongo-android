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

    private ArrayList<Quiz> quiz;
    private int currentQuiz;

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

    @SuppressLint("DefaultLocale")
    private void initInstance() {
        quiz = getIntent().getParcelableArrayListExtra("quiz");
        this.currentQuiz = 0;
        int unitNumber = getIntent().getIntExtra("unitNumber", 0);
        TextView quizTitle = findViewById(R.id.tv_quiz_title);
        quizTitle.setText(String.format("Unit %d quiz", unitNumber));
        final Button next = findViewById(R.id.btn_next_quiz);
        final Button prev = findViewById(R.id.btn_prev_quiz);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentQuiz+1 < quiz.size()) {
                    prev.setEnabled(true);
                    currentQuiz+=1;
                    addNextQuiz(currentQuiz);
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
                    if (currentQuiz == 0) {
                        prev.setEnabled(false);
                    }
                } else {
                    prev.setEnabled(false);
                }
            }
        });
    }

    public Quiz getCurrentQuiz(int index) {
        return this.quiz.get(index);
    }

    public void setSelectAnswer(int quizIndex, String answer) {
        this.quiz.get(quizIndex).setSelectedAnswer(answer);
    }

    private void setCurrentQuiz(final int quizIndex) {
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
}

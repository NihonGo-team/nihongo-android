package th.in.pnnutkung.nihongo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import th.in.pnnutkung.nihongo.fragment.VocabularyFragment;

public class VocabularyActivity extends AppCompatActivity {

    private ArrayList<Word> wordList;
    private int unitNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary);
        TextView tvTitle = findViewById(R.id.tv_vocabulary_title);
        Bundle bundle = getIntent().getExtras();
        unitNumber = bundle.getInt("unitNumber");
        String title = String.format("Vocabulary unit %d", unitNumber);
        tvTitle.setText(title);
        wordList = bundle.getParcelableArrayList("words");
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.vocabulary_content_container,
                            VocabularyFragment.newInstance(wordList),
                            "WordsFragment"
                    )
                    .commit();
        }
        initInstance();
    }

    private void initInstance() {
        Button goToTest = findViewById(R.id.btn_go_to_test);
        goToTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Quiz> quiz = generateQuiz();
                Intent intent = new Intent(VocabularyActivity.this, QuizActivity.class);
                intent.putExtra("unitNumber", unitNumber);
                intent.putParcelableArrayListExtra("quiz", quiz);
                startActivity(intent);
            }
        });
    }

    private ArrayList<Quiz> generateQuiz() {
        Random rng = new Random();
        Set<Integer> generated = new HashSet<Integer>(10);
        generated.clear();
        while (generated.size() < 10) {
            Integer next = rng.nextInt(this.wordList.size());
            generated.add(next);
        }
        ArrayList<Quiz> quizzes = new ArrayList<Quiz>(10);
        quizzes.clear();
        for (Integer quizIndex : generated) {
            quizzes.add(new Quiz(this.wordList.get(quizIndex).getJp(),
                    generateAnswers(quizIndex),
                    this.wordList.get(quizIndex).getTh()));
        }
        Collections.shuffle(quizzes);
        return quizzes;
    }

    private String[] generateAnswers(Integer quizIndex) {
        ArrayList<String> answers = new ArrayList<String>(4);
        answers.clear();
        Random rng = new Random();
        Set<Integer> answerSet = new HashSet<Integer>(4);
        answerSet.clear();
        answerSet.add(quizIndex);
        while (answerSet.size() < 4) {
            Integer next = rng.nextInt(this.wordList.size());
            answerSet.add(next);
        }
        for(Integer answerIndex : answerSet) {
            answers.add(this.wordList.get(answerIndex).getTh());
        }
        Collections.shuffle(answers);
        return answers.toArray(new String[4]);
    }
}

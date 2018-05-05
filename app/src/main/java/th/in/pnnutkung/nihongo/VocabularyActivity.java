package th.in.pnnutkung.nihongo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import th.in.pnnutkung.nihongo.fragment.VocabularyFragment;

public class VocabularyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary);
        TextView tvTitle = findViewById(R.id.tv_vocabulary_title);
        Bundle bundle = getIntent().getExtras();
        String title = String.format("Vocabulary unit %d", bundle.getInt("unitNumber"));
        tvTitle.setText(title);
        HashMap<String, Word> wordHashMap = (HashMap<String, Word>) bundle.getSerializable("words");
        List<Word> wordList = new ArrayList<Word>(wordHashMap.values());
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.vocabulary_content_container,
                            VocabularyFragment.newInstance(wordList),
                            "WordsFragment"
                    )
                    .commit();
        }
    }
}

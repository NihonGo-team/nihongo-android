package th.in.pnnutkung.nihongo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import th.in.pnnutkung.nihongo.R;
import th.in.pnnutkung.nihongo.Word;

public class WordDescFragment extends Fragment {

    Word word;

    public static Fragment newInstance(Word word) {
        WordDescFragment fragment = new WordDescFragment();
        Bundle args = new Bundle();
        args.putParcelable("word", word);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.word = getArguments().getParcelable("word");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_word_desc ,container ,false);
        initInstance(rootView);
        return rootView;
    }

    private void initInstance(View rootView) {
        TextView japanese = rootView.findViewById(R.id.tv_japanese_word);
        TextView kanji = rootView.findViewById(R.id.tv_kanji_word);
        TextView thai = rootView.findViewById(R.id.tv_thai_meaning);
        japanese.setText(this.word.getJp());
        if (!this.word.getKanji().equalsIgnoreCase("")) {
            kanji.setText(this.word.getKanji());
        } else {
            kanji.setVisibility(View.GONE);
        }
        thai.setText(this.word.getTh());
    }
}

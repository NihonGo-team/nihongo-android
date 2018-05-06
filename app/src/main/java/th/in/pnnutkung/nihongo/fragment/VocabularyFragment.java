package th.in.pnnutkung.nihongo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import th.in.pnnutkung.nihongo.R;
import th.in.pnnutkung.nihongo.VocabularyActivity;
import th.in.pnnutkung.nihongo.Word;

public class VocabularyFragment extends Fragment {

    ArrayList<Word> words;

    public static Fragment newInstance(ArrayList<Word> words) {
        VocabularyFragment fragment = new VocabularyFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("words", words);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            this.words = getArguments().getParcelableArrayList("words");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_vocabulary, container, false);
        initInstance(rootView);
        return rootView;
    }

    private void initInstance(View rootView) {
        RecyclerView mRecyclerView = rootView.findViewById(R.id.rv_vocabulary);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(rootView.getContext(),
                LinearLayoutManager.VERTICAL,
                false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        VocabularyAdapter mAdapter = new VocabularyAdapter(this.words, (VocabularyActivity) getActivity());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                mLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        mRecyclerView.setAdapter(mAdapter);
    }
}

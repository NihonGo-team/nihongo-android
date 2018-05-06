package th.in.pnnutkung.nihongo.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import th.in.pnnutkung.nihongo.R;
import th.in.pnnutkung.nihongo.VocabularyActivity;
import th.in.pnnutkung.nihongo.Word;

public class VocabularyAdapter extends RecyclerView.Adapter<VocabularyAdapter.ViewHolder> {

    private ArrayList<Word> mDataset;
    private VocabularyActivity activity;

    public VocabularyAdapter(ArrayList<Word> words, VocabularyActivity activity) {
        super();
        this.mDataset = words;
        this.activity = activity;
    }

    @NonNull
    @Override
    public VocabularyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_holder_vocabulary, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final VocabularyAdapter.ViewHolder holder, final int position) {
        holder.setTvJapanese(mDataset.get(position).getJp());
        holder.setTvKanji(mDataset.get(position).getKanji());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(
                                R.anim.from_right,
                                R.anim.to_left,
                                R.anim.from_left,
                                R.anim.to_right
                        )
                        .replace(R.id.vocabulary_content_container,
                                WordDescFragment.newInstance(mDataset.get(position)))
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvJapanese;
        private TextView tvKanji;

        ViewHolder(View itemView) {
            super(itemView);
            tvJapanese = itemView.findViewById(R.id.tv_japanese);
            tvKanji = itemView.findViewById(R.id.tv_kanji);
        }

        void setTvJapanese(String japanese) {
            tvJapanese.setText(japanese);
        }

        void setTvKanji(String kanji) {
            tvKanji.setText(kanji);
        }
    }
}



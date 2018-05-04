package th.in.pnnutkung.nihongo.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import th.in.pnnutkung.nihongo.R;
import th.in.pnnutkung.nihongo.Word;

public class VocabularyAdapter extends RecyclerView.Adapter<VocabularyAdapter.ViewHolder> {

    private Word[] mDataset;

    public VocabularyAdapter(Word[] words) {
        super();
        this.mDataset = words;
    }

    @NonNull
    @Override
    public VocabularyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_holder_vocabulary, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VocabularyAdapter.ViewHolder holder, int position) {
        holder.setTvJapanese(mDataset[position].getJp());
        holder.setTvKanji(mDataset[position].getKanji());
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
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



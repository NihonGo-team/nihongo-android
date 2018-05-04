package th.in.pnnutkung.nihongo;

import android.os.Parcel;
import android.os.Parcelable;

public class Word implements Parcelable {
    private String kanji;
    private String jp;
    private String th;

    public Word() {
    }

    public Word(String jp, String th, String kanji) {
        this.jp = jp;
        this.th = th;
        this.kanji = kanji;
    }

    protected Word(Parcel in) {
        kanji = in.readString();
        jp = in.readString();
        th = in.readString();
    }

    public static final Creator<Word> CREATOR = new Creator<Word>() {
        @Override
        public Word createFromParcel(Parcel in) {
            return new Word(in);
        }

        @Override
        public Word[] newArray(int size) {
            return new Word[size];
        }
    };

    public String getJp() {
        return jp;
    }

    public void setJp(String jp) {
        this.jp = jp;
    }

    public String getTh() {
        return th;
    }

    public void setTh(String th) {
        this.th = th;
    }

    public String getKanji() {
        return kanji;
    }

    public void setKanji(String kanji) {
        this.kanji = kanji;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(kanji);
        dest.writeString(jp);
        dest.writeString(th);
    }
}

package th.in.pnnutkung.nihongo;

public class Word {
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
}

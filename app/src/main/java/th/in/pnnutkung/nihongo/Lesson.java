package th.in.pnnutkung.nihongo;

import java.util.HashMap;

public class Lesson {
    private int unit;
    private HashMap<String, Word> word;

    public Lesson() {
    }

    public Lesson(int unit, HashMap<String, Word> word) {
        this.unit = unit;
        this.word = word;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public HashMap<String, Word> getWord() {
        return word;
    }

    public void setWord(HashMap<String, Word> word) {
        this.word = word;
    }
}

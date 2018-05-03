package th.in.pnnutkung.nihongo;

import java.util.Map;

public class Lesson {
    private int unit;
    private Map<String, Word> word;

    public Lesson() {
    }

    public Lesson(int unit, Map<String, Word> word) {
        this.unit = unit;
        this.word = word;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public Map<String, Word> getWord() {
        return word;
    }

    public void setWord(Map<String, Word> word) {
        this.word = word;
    }
}

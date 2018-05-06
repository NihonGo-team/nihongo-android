package th.in.pnnutkung.nihongo;

import java.util.ArrayList;

public class Lesson {
    private int unit;
    private ArrayList<Word> word;

    public Lesson() {
    }

    public Lesson(int unit, ArrayList<Word> word) {
        this.unit = unit;
        this.word = word;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public ArrayList<Word> getWord() {
        return word;
    }

    public void setWord(ArrayList<Word> word) {
        this.word = word;
    }
}

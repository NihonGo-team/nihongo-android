package th.in.pnnutkung.nihongo;

import android.os.Parcel;
import android.os.Parcelable;

public class Quiz implements Parcelable {
    private String question;
    private String[] answers;
    private String selectedAnswer;

    public Quiz(String question, String[] answers) {
        this.question = question;
        this.answers = answers;
    }

    protected Quiz(Parcel in) {
        question = in.readString();
        answers = in.createStringArray();
        selectedAnswer = in.readString();
    }

    public static final Creator<Quiz> CREATOR = new Creator<Quiz>() {
        @Override
        public Quiz createFromParcel(Parcel in) {
            return new Quiz(in);
        }

        @Override
        public Quiz[] newArray(int size) {
            return new Quiz[size];
        }
    };

    public String getQuestion() {
        return question;
    }

    public String[] getAnswers() {
        return this.answers;
    }

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectAnswer(String selectAnswer) {
        if (this.selectedAnswer == null) {
            this.selectedAnswer = selectAnswer;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeStringArray(answers);
        dest.writeString(selectedAnswer);
    }
}

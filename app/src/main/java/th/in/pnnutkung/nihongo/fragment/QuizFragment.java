package th.in.pnnutkung.nihongo.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import th.in.pnnutkung.nihongo.Quiz;
import th.in.pnnutkung.nihongo.QuizActivity;
import th.in.pnnutkung.nihongo.R;

public class QuizFragment extends Fragment {

    private int currentQuizIndex;
    private final int GREEN = Color.GREEN;
    private final int RED = Color.RED;
    private RadioButton answer1;
    private RadioButton answer2;
    private RadioButton answer3;
    private RadioButton answer4;
    private Quiz quiz;
    private RadioGroup answersGroup;

    public static Fragment newInstance(int currentQuizIndex) {
        QuizFragment fragment = new QuizFragment();
        Bundle args = new Bundle();
        args.putInt("currentQuizIndex", currentQuizIndex);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.currentQuizIndex = getArguments().getInt("currentQuizIndex");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_quiz, container, false);
        initInstance(rootView);
        return rootView;
    }

    private void initInstance(View rootView) {
        quiz = ((QuizActivity) getActivity()).getCurrentQuiz(this.currentQuizIndex);
        TextView question = rootView.findViewById(R.id.tv_quiz_question);
        answersGroup = rootView.findViewById(R.id.rg_answers_group);
        answer1 = rootView.findViewById(R.id.rb_quiz_answer1);
        answer2 = rootView.findViewById(R.id.rb_quiz_answer2);
        answer3 = rootView.findViewById(R.id.rb_quiz_answer3);
        answer4 = rootView.findViewById(R.id.rb_quiz_answer4);
        question.setText(quiz.getQuestion());
        answer1.setText(quiz.getAnswers()[0]);
        answer2.setText(quiz.getAnswers()[1]);
        answer3.setText(quiz.getAnswers()[2]);
        answer4.setText(quiz.getAnswers()[3]);
        String currentAnswer = quiz.getSelectedAnswer();
        final boolean selectedAnswer = !currentAnswer.equalsIgnoreCase("");
        if (selectedAnswer) {
            answer1.setChecked(answer1.getText().toString().equalsIgnoreCase(currentAnswer));
            answer2.setChecked(answer2.getText().toString().equalsIgnoreCase(currentAnswer));
            answer3.setChecked(answer3.getText().toString().equalsIgnoreCase(currentAnswer));
            answer4.setChecked(answer4.getText().toString().equalsIgnoreCase(currentAnswer));
            setAllDisabled ();
        }
        answersGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_quiz_answer1: {
                        String ans1 = answer1.getText().toString();
                        setAnswer(ans1);
                        setAllDisabled ();
                        if(checkAnswer(ans1)) {
                            answer1.setTextColor(GREEN);
                            ((QuizActivity) getActivity()).addScore();
                        } else {
                            answer1.setTextColor(RED);
                            revealCorrectAnswer();
                        }
                        break;
                    }
                    case R.id.rb_quiz_answer2: {
                        String ans2 = answer2.getText().toString();
                        setAnswer(ans2);
                        setAllDisabled ();
                        if(checkAnswer(ans2)) {
                            answer2.setTextColor(GREEN);
                            ((QuizActivity) getActivity()).addScore();
                        } else {
                            answer2.setTextColor(RED);
                            revealCorrectAnswer();
                        }
                        break;
                    }
                    case R.id.rb_quiz_answer3: {
                        String ans3 = answer3.getText().toString();
                        setAnswer(ans3);
                        setAllDisabled ();
                        if(checkAnswer(ans3)) {
                            answer3.setTextColor(GREEN);
                            ((QuizActivity) getActivity()).addScore();
                        } else {
                            answer3.setTextColor(RED);
                            revealCorrectAnswer();
                        }
                        break;
                    }
                    case R.id.rb_quiz_answer4: {
                        String ans4 = answer4.getText().toString();
                        setAnswer(ans4);
                        setAllDisabled ();
                        if(checkAnswer(ans4)) {
                            answer4.setTextColor(GREEN);
                            ((QuizActivity) getActivity()).addScore();
                        } else {
                            answer4.setTextColor(RED);
                            revealCorrectAnswer();
                        }
                        break;
                    }
                }
            }
        });
    }

    private void setAnswer(String answer) {
        ((QuizActivity) getActivity()).setSelectAnswer(currentQuizIndex, answer);
    }

    private boolean checkAnswer (String answer) {
        return ((QuizActivity) getActivity()).getCurrentQuiz(currentQuizIndex).isCorrect();
    }

    private void revealCorrectAnswer() {
        if (answer1.getText().toString().equalsIgnoreCase(quiz.getCorrectAnswer())) {
            answer1.setTextColor(GREEN);
        } else if (answer2.getText().toString().equalsIgnoreCase(quiz.getCorrectAnswer())) {
            answer2.setTextColor(GREEN);
        } else if (answer3.getText().toString().equalsIgnoreCase(quiz.getCorrectAnswer())) {
            answer3.setTextColor(GREEN);
        } else if (answer4.getText().toString().equalsIgnoreCase(quiz.getCorrectAnswer())) {
            answer4.setTextColor(GREEN);
        }
    }

    private void setAllDisabled () {
        for(View rb : answersGroup.getTouchables()) {
            rb.setEnabled(false);
            String rbText = ((RadioButton) rb).getText().toString();
            if (rbText.equalsIgnoreCase(quiz.getCorrectAnswer())) {
                ((RadioButton) rb).setTextColor(GREEN);
            }
            if (((RadioButton) rb).isChecked() && !checkAnswer(rbText)) {
                ((RadioButton) rb).setTextColor(RED);
            }
        }
    }
}

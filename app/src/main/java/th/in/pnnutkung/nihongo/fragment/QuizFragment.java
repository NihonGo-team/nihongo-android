package th.in.pnnutkung.nihongo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
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
        final Quiz quiz = ((QuizActivity) getActivity()).getCurrentQuiz(this.currentQuizIndex);
        TextView question = rootView.findViewById(R.id.tv_quiz_question);
        final RadioGroup answersGroup = rootView.findViewById(R.id.rg_answers_group);
        final RadioButton answer1 = rootView.findViewById(R.id.rb_quiz_answer1);
        final RadioButton answer2 = rootView.findViewById(R.id.rb_quiz_answer2);
        final RadioButton answer3 = rootView.findViewById(R.id.rb_quiz_answer3);
        final RadioButton answer4 = rootView.findViewById(R.id.rb_quiz_answer4);
        question.setText(quiz.getQuestion());
        answer1.setText(quiz.getAnswers()[0]);
        answer2.setText(quiz.getAnswers()[1]);
        answer3.setText(quiz.getAnswers()[2]);
        answer4.setText(quiz.getAnswers()[3]);
        String currentAnswer = quiz.getSelectedAnswer();
        final boolean selectedAnswer = !currentAnswer.equalsIgnoreCase("");
        if (selectedAnswer) {
            answer1.setChecked(answer1.getText().toString().equalsIgnoreCase(currentAnswer));
            Log.e("set selected answer 1", answer1.getText().toString().equalsIgnoreCase(currentAnswer) + "");
            answer2.setChecked(answer2.getText().toString().equalsIgnoreCase(currentAnswer));
            Log.e("set selected answer 2", answer2.getText().toString().equalsIgnoreCase(currentAnswer) + "");
            answer3.setChecked(answer3.getText().toString().equalsIgnoreCase(currentAnswer));
            Log.e("set selected answer 3", answer3.getText().toString().equalsIgnoreCase(currentAnswer) + "");
            answer4.setChecked(answer4.getText().toString().equalsIgnoreCase(currentAnswer));
            Log.e("set selected answer 4", answer4.getText().toString().equalsIgnoreCase(currentAnswer) + "");
        }
        answersGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_quiz_answer1:
                        setAnswer(answer1.getText().toString());
                        break;
                    case R.id.rb_quiz_answer2:
                        setAnswer(answer2.getText().toString());
                        break;
                    case R.id.rb_quiz_answer3:
                        setAnswer(answer3.getText().toString());
                        break;
                    case R.id.rb_quiz_answer4:
                        setAnswer(answer4.getText().toString());
                        break;
                }
            }
        });
    }

    private void setAnswer(String answer) {
        ((QuizActivity) getActivity()).setSelectAnswer(currentQuizIndex, answer);
    }
}

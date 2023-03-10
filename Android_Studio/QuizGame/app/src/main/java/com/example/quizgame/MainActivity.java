package com.example.quizgame;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {
    // setting up things
    private Button confirmButton;
    private ImageButton nextButton;
    private ImageButton prevButton;
    private ImageView Image;
    private EditText textInput;
    private TextView questionTextView;
    private TextView correctAnswer;
    private int correct = 0;
    // to keep current question track
    private int currentQuestionIndex = 0;

    private Question[] questionBank = new Question[] {
            // array of objects of class Question
            // providing questions from string
            // resource and the correct ans
            new Question(R.string.a, new String[]{"Super Mario 64", "Mario 64"}, false),
            new Question(R.string.b, new String[]{"Sonic The Hedgehog", "Sonic"}, false),
            new Question(R.string.c, new String[]{"Street Fighter 2"}, false),
            new Question(R.string.d, new String[]{"Super Smash Bros. Melee", "Melee", "Smash Melee"}, false),
            new Question(R.string.e, new String[]{"Call of Duty Modern Warfare 2", "Modern Warfare 2", "MW2", "COD MW2", "Call of Duty MW 2"}, false),
            new Question(R.string.f, new String[]{"Elden Ring"}, false),

    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // setting up the buttons
        // associated with id
        confirmButton = findViewById(R.id.confirm_button);
        nextButton = findViewById(R.id.next_button);
        prevButton = findViewById(R.id.prev_button);
        // register our buttons to listen to
        // click events
        Image = findViewById(R.id.myimage);
        confirmButton.setOnClickListener(this);
        textInput = findViewById(R.id.text_input);
        questionTextView = findViewById(R.id.answer_text_view);
        correctAnswer = findViewById(R.id.correct_answer);
        nextButton.setOnClickListener(this);
        prevButton.setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v)
    {
        int toastMessageId;
        // checking which button is
        // clicked by user
        // in this case user choose false
        switch (v.getId()) {
            case R.id.confirm_button:
                checkAnswer(textInput.getText().toString());
                correctAnswer.setText(questionBank[currentQuestionIndex].getAnswer());
                // go to next question
                // limiting question bank range
                //textOutput.setText(textInput.getText().toString());
//                if (currentQuestionIndex < 7) {
//                    currentQuestionIndex
//                            = currentQuestionIndex + 1;
//                    // we are safe now!
//                    // last question reached
//                    // making buttons
//                    // invisible
//                    if (currentQuestionIndex == 6) {
//                        questionTextView.setText(getString(
//                                R.string.correct, correct));
//                        confirmButton.setVisibility(
//                                View.INVISIBLE);
//                        textInput.setVisibility(
//                                View.INVISIBLE);
//                        questionTextView.setText(
//                                    "CORRECTNESS IS " + correct
//                                            + " "
//                                            + "OUT OF 6");
                            // showing correctness

//                    }
//                    else {
//                        //updateQuestion();
//                    }
//                }
                break;
            case R.id.next_button:
                if (currentQuestionIndex < 6) {
                    currentQuestionIndex
                            = currentQuestionIndex + 1;
                    updateQuestion();
                }
                else
                {

                }
                break;
            case R.id.prev_button:
                if (currentQuestionIndex > 0) {
                    currentQuestionIndex
                            = currentQuestionIndex - 1;
                    updateQuestion();
                }
                else
                {

                }
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void updateQuestion()
    {
        Log.d("Current",
                "onClick: " + currentQuestionIndex);

        questionTextView.setText(
                questionBank[currentQuestionIndex]
                        .getAnswerResId());

        if (questionBank[currentQuestionIndex].getIsAnswered() == true)
        {
            correctAnswer.setText(questionBank[currentQuestionIndex].getAnswer());
        }
        else
        {
            correctAnswer.setText("");
        }
        // setting the textview with new question
        switch (currentQuestionIndex) {
            case 0:
                // setting up image for each
                // question
                Image.setImageResource(R.drawable.bob_omb_battlefield);
                break;
            case 1:
                Image.setImageResource(R.drawable.sonicgreenhillzone);
                break;
            case 2:
                Image.setImageResource(R.drawable.sf2bonus);
                break;
            case 3:
                Image.setImageResource(R.drawable.fd);
                break;
            case 4:
                Image.setImageResource(R.drawable.codrust);
                break;
            case 5:
                Image.setImageResource(R.drawable.caelid);
                break;
        }
    }
    private void checkAnswer(String userAnswer)
    {
        boolean isCorrect = false;
        isCorrect = questionBank[currentQuestionIndex].isAnswerCorrect(userAnswer);
        // getting correct ans of current question
        int toastMessageId;
        // if ans matches with the
        // button clicked

        if (isCorrect == true) {
            toastMessageId = R.string.correct_answer;
            correct++;
        }
        else {
            // showing toast
            // message correct
            toastMessageId = R.string.wrong_answer;
        }

        Toast
                .makeText(MainActivity.this, toastMessageId,
                        Toast.LENGTH_SHORT)
                .show();
    }
}

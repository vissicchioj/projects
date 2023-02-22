package com.example.quizgame;

import java.util.Locale;

public class Question
{
        // answerResId will store question
        private int answerResId;

        // answerTrue will store correct answer
        // of the question provided

        // Perhaps make this into an array of possible correct answers
        private String answer;

        public Question(int answerResId, String answer)
        {
            // setting the values through
            // arguments passed in constructor
            this.answerResId = answerResId;
            this.answer = answer;
        }

        // returning the question passed
        public int getAnswerResId()
        {
            return answerResId;
        }

        // setting the question passed
        public void setAnswerResId(int answerResId)
        {
            this.answerResId = answerResId;
        }

        // returning the correct answer
        // of question
        public boolean isAnswerCorrect(String userAnswer)
        {
            if (answer.toLowerCase().equals(userAnswer.toLowerCase()))
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        // setting the correct
        // ans of question
        public void setAnswerTrue(String answer)
        {
            this.answer = answer;
        }

}

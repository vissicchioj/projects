package com.example.quizgame;

import java.util.Locale;

public class Question
{
        // answerResId will store question
        private int answerResId;

        // answerTrue will store correct answer
        // of the question provided

        // Perhaps make this into an array of possible correct answers
        private String answers[];

        boolean isAnswered;

        BinarySearch bSearch = new BinarySearch();
        QuickSort qSort = new QuickSort();

        public Question(int answerResId, String answers[], boolean isAnswered)
        {
            // setting the values through
            // arguments passed in constructor
            this.answerResId = answerResId;
            this.answers = answers;
            this.isAnswered = isAnswered;
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

        public String getAnswer()
        {
            return answers[0];
        }

        public boolean getIsAnswered(){return isAnswered;}

        public void setIsAnswered(boolean isAnswered){this.isAnswered = isAnswered;}

        // returning the correct answer
        // of question
        public boolean isAnswerCorrect(String userAnswer)
        {
//            if (answer.toLowerCase().equals(userAnswer.toLowerCase()))
//            {
//                return true;
//            }
//            else
//            {
//                return false;
//            }
            qSort.quickSort(answers, 0, answers.length - 1);
            boolean isFound = false;
            isFound = bSearch.search(answers, userAnswer, 0, answers.length - 1);
            if (isFound == true)
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
            this.answers[0] = answer;
        }

}

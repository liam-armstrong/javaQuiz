/*
 *  Liam Armstrong and Jacob D.
 *  Java Quiz driver class
 *  January 2016
 *  
 *  Question object constructor, used to store and print info
 */

public class Question
{
    String Question;//for returning/printing
    String Answers;//for returning/printing
    char Correct;//for returning/printing
    int Difficulty;//for returning/printing

    public Question()
    {
    }

    public String getQuestion()
    {
        return Question;
    }

    public String getAnswers()
    {
        return Answers;
    }

    public char getCorrect()
    {
        return Correct;
    }

    public int getDifficulty()
    {
        return Difficulty;
    }
}
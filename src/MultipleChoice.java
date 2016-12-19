/*
 *  Liam Armstrong and Jacob D.
 *  Java Quiz MultipleChoice object constructor  
 *  January 2016
 *  
 *  A child of Question, doesn't override anything
 */


public class MultipleChoice extends Question
{
    public MultipleChoice(String Question, String Answers, char Correct, int Difficulty)
    {
        this.Question = Question;//setting values for returning
        this.Answers = Answers;
        this.Correct = Correct;
        this.Difficulty = Difficulty;
    }
}

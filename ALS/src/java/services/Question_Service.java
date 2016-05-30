/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import java.util.List;
import model.Question;
import model.QuestionBlank;
import model.QuestionMcq;
import model.QuestionNumarical;
import model.QuizQuestions;
import repositery.Question_Rep;


public class Question_Service {
    
    Question_Rep qr = new Question_Rep();
    
    public int saveQuestion(Question ques)
    {
        return qr.saveQuestion(ques);
    }
    
    public int saveMCQ(QuestionMcq ques)
    {
        return qr.saveMCQ(ques);
    }
    
    public int saveBlank(QuestionBlank ques)
    {
        return qr.saveBlank(ques);
    }
    
    public int saveNumarical(QuestionNumarical ques)
    {
        return qr.saveNumarical(ques);
    }
   
    public int saveQuizQuestion(QuizQuestions qq)
    {
        return qr.saveQuizQuestion(qq);
    }
    
    public List<Question> getQuestions()
    {
        return qr.getQuestions();
    }
    
    public void update(Question ques)
    {
        qr.update(ques);
    }
    
    public Question getQuestionFromID(int ID)
    {
        return qr.getQuestionFromID(ID);
    }
    
}

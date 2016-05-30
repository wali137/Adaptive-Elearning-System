/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import model.Question;
import model.QuestionBlank;
import model.QuestionMcq;
import model.QuestionNumarical;
import model.Quiz;
import model.QuizQuestions;
import repositery.Quiz_Rep;
import services.Question_Service;


public class Question_Controller {
    
    Question_Service qs = new Question_Service();
    Question question;
    Quiz_Rep qr = new Quiz_Rep();
    Quiz quiz = new Quiz();

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(int ID) {
        this.quiz = qr.fetchQuiz(ID);
        if(remaining_marks == -1)
        {
            setRemaining_marks(quiz.getTotalMarks());
        }
    }
    
    public void mcq_null()
    {
        mcq.setOptionA(null);
        mcq.setOptionB(null);
        mcq.setOptionC(null);
        mcq.setOptionD(null);
    }
    public void num_null()
    {
        num.setCorrect(null);
    }
    public void blank_null()
    {
        blank.setCorrect(null);
    }
    
    public void question_null()
    {
        question.setMarks(0);
        question.setStatement("");
    }
    
    public void end_quiz() throws IOException
    {
        help.navigate("teacher_course_quiz_view.xhtml?cid=" + quiz.getCourseId());
    }
    
    QuizQuestions qq = new QuizQuestions();
    List<Question> qlist; 
    int Questions_Max;

    int QuestionType;

    public int getQuestionType() {
        return QuestionType;
    }

    public void setQuestionType(int QuestionType) {
        this.QuestionType = QuestionType;
    }
    
    public int getQuestions_Max() {
        return Questions_Max;
    }

    public void setQuestions_Max(int Questions_Max) {
        this.Questions_Max = Questions_Max;
    }

    public List<Question> getQlist() {
        return qlist;
    }

    public void setQlist(List<Question> qlist) {
        this.qlist = qlist;
    }
    int quiz_id;

    public int getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(int quiz_id) {
        this.quiz_id = quiz_id;
    }

    public Question getQuestion() {
        if(question==null)
        {
            question=new Question();
        }
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public QuestionMcq getMcq() {
        return mcq;
    }

    public void setMcq(QuestionMcq mcq) {
        this.mcq = mcq;
    }

    public QuestionNumarical getNum() {
        return num;
    }

    public void setNum(QuestionNumarical num) {
        this.num = num;
    }

    public QuestionBlank getBlank() {
        return blank;
    }

    public void setBlank(QuestionBlank blank) {
        this.blank = blank;
    }
    
    QuestionMcq mcq = new QuestionMcq();
    
    QuestionNumarical num = new QuestionNumarical();
    
    QuestionBlank blank =  new QuestionBlank();
    
    static int lastQuestionID = 0;

    public static int getLastQuestionID() {
        return lastQuestionID;
    }

    public static void setLastQuestionID(int lastQuestionID) {
        Question_Controller.lastQuestionID = lastQuestionID;
    }
    
   int remaining_marks=-1;

    public int getRemaining_marks() {
        return remaining_marks;
    }

    public void setRemaining_marks(int remaining_marks) {
        this.remaining_marks = remaining_marks;
    }
    
   public void saveQuestionDecide() throws IOException
   {
       int a = quiz.getTotalMarks();
       int b = question.getMarks();
       if(b<=remaining_marks && b>0)
       {
           remaining_marks = remaining_marks - question.getMarks();
            if(QuestionType == 1) //MCQ
            {
                mcq_null();
                saveQuestion_MCQ_Quiz();
            }
            if(QuestionType == 2) //True/False
            {
                blank_null();
                saveQuestion_Blank_Quiz();
            }
            if(QuestionType == 3) //Numarical
            {
                num_null();
                saveQuestion_Num_Quiz();
            }
       }else{
           if(remaining_marks == 0)
           {
               help.pop_msg("Questions Limit Reached!");
           }else{
               help.pop_msg("Assigned Marks must be less then " + remaining_marks);
           }
       }
   }
    
    public void saveQuestion_MCQ()
    {
        question.setDateCreated(new Date());
        question.setQuestionType("mcq");
        int id = qs.saveQuestion(question);
        setLastQuestionID(id);
    }
    
    Helper help = new Helper();
    int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    
    int total_marks;
    
    
    public void saveQuestion_MCQ_Quiz() throws IOException
    {

                question.setQuizId(ID);
                question.setDateCreated(new Date());
                question.setQuestionType("mcq");
                int id = qs.saveQuestion(question);
                setLastQuestionID(id);
                qq.setQuizQuestionId(quiz_id);
                qq.setQuestionId(id);
                qs.saveQuizQuestion(qq);
                question_null();
                help.navigate("teacher_course_quiz_create_question_mcq.xhtml?ID=" + getID());
        
    }
    
    
    public void saveQuestion_Blank()
    {
        question.setDateCreated(new Date());
        question.setQuestionType("blank");
        int id = qs.saveQuestion(question);
        setLastQuestionID(id);
    }
    
    public void saveQuestion_Num()
    {
        question.setDateCreated(new Date());
        question.setQuestionType("num");
        int id = qs.saveQuestion(question);
        setLastQuestionID(id);
    }
    
    public void saveQuestion_Blank_Quiz() throws IOException
    {
        question.setQuizId(ID);
        question.setDateCreated(new Date());
        question.setQuestionType("blank");
        int id = qs.saveQuestion(question);
        setLastQuestionID(id);
        qq.setQuizQuestionId(quiz_id);
        qq.setQuestionId(id);
        qs.saveQuizQuestion(qq);
        question_null();
        help.navigate("teacher_course_quiz_create_question_TF.xhtml?ID=" + getID());
    }
    
    public void saveQuestion_Num_Quiz() throws IOException
    {
        question.setQuizId(ID);
        question.setDateCreated(new Date());
        question.setQuestionType("num");
        int id = qs.saveQuestion(question);
        setLastQuestionID(id);
        qq.setQuizQuestionId(quiz_id);
        qq.setQuestionId(id);
        qs.saveQuizQuestion(qq);
        question_null();
        help.navigate("teacher_course_quiz_create_question_num.xhtml?ID=" + getID());
    }
    
    public void update()
    {
        qs.update(QFI);
        help.pop_msg("Question Updated Sucessfully!");
    }
    
    
    
    public void saveMCQ() throws IOException
    {
        mcq.setQuestionId(lastQuestionID);
        qs.saveMCQ(mcq);
        help.navigate("teacher_course_quiz_create_question.xhtml?ID=" + quiz.getId());
    }
    
    public void saveBlank() throws IOException
    {
        blank.setQuestionId(lastQuestionID);
        qs.saveBlank(blank);
        help.navigate("teacher_course_quiz_create_question.xhtml?ID=" + quiz.getId());
    }
    
    public void saveNumarical() throws IOException
    {
        num.setQuestionId(lastQuestionID);
        qs.saveNumarical(num);
        help.navigate("teacher_course_quiz_create_question.xhtml?ID=" + quiz.getId());
    }
    
    
    
    Question QFI;

    public Question getQFI() {
        return QFI;
    }

    public void setQFI(int id) {
        this.QFI = qs.getQuestionFromID(ID);
    }
    
    
    // Algorithms
    public List<Question> generateRandomQuestions()
    {
        // getting list
        qlist = qs.getQuestions();
        // trimming to required questions
        while(qlist.size() > Questions_Max)
        {
            qlist.remove(qlist.size()-1);
        }
        return qlist;
    }
    
}

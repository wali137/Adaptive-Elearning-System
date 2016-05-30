/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package repositery;

import hibernate.HibernateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Answers;
import model.Question;
import model.Quiz;
import model.QuestionMcq;
import model.QuizResult;
import model.QuizSubmission;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

/**
 *
 * @author DemiXsoft
 */
public class Quiz_Rep {
     private Session con;
    private Transaction tx;
    private List<Quiz> quiz;
    private Integer usrID;
    private Query q;
    

    
 public Integer save(Quiz quiz){
     try{
         con = HibernateUtil.getSessionFactory().openSession();
         tx = con.beginTransaction();
         usrID = (Integer) con.save(quiz);
         tx.commit(); 
      }
       catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }
     finally {
         con.close(); 
      }
      return usrID;
   }
 
 public Integer saveResult(QuizResult qres){
     try{
         con = HibernateUtil.getSessionFactory().openSession();
         tx = con.beginTransaction();
         usrID = (Integer) con.save(qres);
         tx.commit(); 
      }
       catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }
     finally {
         con.close(); 
      }
      return usrID;
   }
 
 public Integer saveSubmission(QuizSubmission qs){
     try{
         con = HibernateUtil.getSessionFactory().openSession();
         tx = con.beginTransaction();
         usrID = (Integer) con.save(qs);
         tx.commit(); 
      }
       catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }
     finally {
         con.close(); 
      }
      return usrID;
   }
 
 
  public void update(Quiz quiz){
     try{
         con = HibernateUtil.getSessionFactory().openSession();
         tx = con.beginTransaction();
         con.update(quiz);
         tx.commit(); 
      }
       catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }
     finally {
         con.close(); 
      }
   }
  Quiz myQuiz = new Quiz();
  
  public Quiz fetchQuiz(int ID){
     try{
         con = HibernateUtil.getSessionFactory().openSession();
         tx = con.beginTransaction();
         myQuiz = (Quiz) con.get(Quiz.class, ID);
         tx.commit(); 
      }
       catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }
     finally {
         con.close(); 
      }
     return myQuiz;
   }
  
  QuestionMcq qmcq = new QuestionMcq();
  
    public Object fetchMCQ(int QID){
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select * from question_mcq where question_id=:qid");
     q.setParameter("qid", QID);
     Object o =  q.list();
     con.close();
     return o;
   }
    
     public Question fetchQuestion(int QID){
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     Question qq = new Question();
     qq = (Question) con.get(Question.class, QID);
     con.close();
     return qq;
   }
  
  Answers ans = new Answers();
  
  public Answers fetchAnswer(int ID){
     try{
         con = HibernateUtil.getSessionFactory().openSession();
         tx = con.beginTransaction();
         ans = (Answers) con.get(Answers.class, ID);
         tx.commit(); 
      }
       catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }
     finally {
         con.close(); 
      }
     return ans;
   }

    public Integer saveAnswer(Answers sol){
        Integer id = -1;
     try{
         con = HibernateUtil.getSessionFactory().openSession();
         tx = con.beginTransaction();
         id = (Integer) con.save(sol);
         tx.commit(); 
      }
       catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }
     finally {
         con.close(); 
      }
      return id;
   }
  
  
   public  List<Quiz> getQuiz()
 {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select * from quiz");
     quiz =  q.list();
     con.close();
     return quiz;
 }
   
     public List<Quiz> submissions(int ID,String cid)
  {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select * from quiz where id not in(select quiz_submission_id from quiz_submission inner join quiz on quiz_submission.quiz_id=quiz.id where quiz_submission.student_id=:id and quiz.course_id=:cid) and course_id=:cid");
     q.setParameter("id", ID);
     q.setParameter("cid", cid);
     quiz =  q.list();
     con.close();
     return quiz;
  }
   
      public  String getMaxID()
 {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select max(id) from quiz");
     String total =  q.list().get(0).toString();
     con.close();
     return total;
 }
      
      public  String getCourseID(int id)
    {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select course_id from quiz where id=:id");
     q.setParameter("id", id);
     String total =  q.list().get(0).toString();
     con.close();
     return total;
    }
      
      public  String getTitle(int ID)
    {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select quiz_name from quiz where id=:qid");
     q.setParameter("qid", ID);
     String title =  q.list().get(0).toString();
     con.close();
     return title;
    }
      
     public  String getCorrectMcq(int QID)
    {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select correct from question_mcq where question_id=:qid");
     q.setParameter("qid", QID);
     String title =  q.list().get(0).toString();
     con.close();
     return title;
    }
     
     public  String getCorrectBlank(int QID)
    {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select correct from question_blank where question_id=:qid");
     q.setParameter("qid", QID);
     String title =  q.list().get(0).toString();
     con.close();
     return title;
    }
     
     public  String getQuestionMarks(int QID)
    {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select marks from question where id=:qid");
     q.setParameter("qid", QID);
     String title =  q.list().get(0).toString();
     con.close();
     return title;
    }
     
     
     
     public  String getCorrectNum(int QID)
    {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select correct from question_numarical where question_id=:qid");
     q.setParameter("qid", QID);
     String title =  q.list().get(0).toString();
     con.close();
     return title;
    }
     
     
     public  String getMarks(int ID)
    {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select total_marks from quiz where id=:qid");
     q.setParameter("qid", ID);
     String title =  q.list().get(0).toString();
     con.close();
     return title;
    }
     
     public  String getDuration(int ID)
    {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select quiz_duration from quiz where id=:qid");
     q.setParameter("qid", ID);
     String title =  q.list().get(0).toString();
     con.close();
     return title;
    }
     
     
     public  String getSection(int ID)
    {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select section_id from quiz where id=:qid");
     q.setParameter("qid", ID);
     String title =  q.list().get(0).toString();
     con.close();
     return title;
    }
     
     
     
     public  String getTopic(int ID)
    {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select topic_id from quiz where id=:qid");
     q.setParameter("qid", ID);
     String title =  q.list().get(0).toString();
     con.close();
     return title;
    }
     
     List<Question> ques;
     
     public  List<Question> getQuizQuestions(int QID)
    {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select * from question where quiz_id=:qid");
     q.setParameter("qid", QID);
     ques =  q.list();
     con.close();
     return ques;
    }
     
     public  List<Integer> getQuizQuestionIds(int QID)
    {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select id from question where quiz_id=:qid");
     q.setParameter("qid", QID);
     List<Integer> ids = new ArrayList<Integer>();
     ids =  q.list();
     con.close();
     return ids;
    }
     
     
     
      
     public  void del_links(int ID)
    {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("delete from quiz_questions where quiz_question_id=:qid");
     q.setParameter("qid", ID);
     con.close();
    }
      
 public void Delete(Quiz quiz)
 {
     try{
         con = HibernateUtil.getSessionFactory().openSession();
         tx = con.beginTransaction();
         con.delete(quiz);
         tx.commit(); 
      }
       catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }
     finally {
         con.close();
      }
 }   
   
}

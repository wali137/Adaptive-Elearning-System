/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package repositery;

import hibernate.HibernateUtil;
import java.util.List;
import model.QuizQuestions;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import model.Question;
import model.QuestionBlank;
import model.QuestionMcq;
import model.QuestionNumarical;
import org.hibernate.HibernateException;

public class Question_Rep {
    
    private Session con;
    private Transaction tx;
    private Integer usrID;
    private Query q;
    
    // Data Saving Functions
    public int saveQuestion(Question ques){
//    con.close();
    int ID=0;
     try{
         con = HibernateUtil.getSessionFactory().openSession();
         tx = con.beginTransaction();
         ID = (Integer) con.save(ques);
         tx.commit(); 
      }
       catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }
         con.close(); 
     return ID;
   }
    
    public int saveMCQ(QuestionMcq mcq){
    //con.close();
    int ID=0;
     try{
         con = HibernateUtil.getSessionFactory().openSession();
         tx = con.beginTransaction();
         ID = (Integer) con.save(mcq);
         tx.commit(); 
      }
       catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }
         con.close(); 
     return ID;
   }
   
    public int saveQuizQuestion(QuizQuestions qq){
    //con.close();
    int ID=0;
     try{
         con = HibernateUtil.getSessionFactory().openSession();
         tx = con.beginTransaction();
         ID = (Integer) con.save(qq);
         tx.commit(); 
      }
       catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }
         con.close(); 
     return ID;
   }
    
    
   public int saveBlank(QuestionBlank blank){
   // con.close();
    int ID=0;
     try{
         con = HibernateUtil.getSessionFactory().openSession();
         tx = con.beginTransaction();
         ID = (Integer) con.save(blank);
         tx.commit(); 
      }
       catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }
         con.close(); 
     return ID;
   }
   
   public int saveNumarical(QuestionNumarical num){
   // con.close();
    int ID=0;
     try{
         con = HibernateUtil.getSessionFactory().openSession();
         tx = con.beginTransaction();
         ID = (Integer) con.save(num);
         tx.commit(); 
      }
       catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }
         con.close(); 
     return ID;
   }
   
   public void update(Question ques){
     try{
         con = HibernateUtil.getSessionFactory().openSession();
         tx = con.beginTransaction();
         con.update(ques);
         tx.commit(); 
      }
       catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }
         con.close(); 
   }
   
   Question qq = new Question();
   
    public Question getQuestionFromID(int ID){
     try{
         con = HibernateUtil.getSessionFactory().openSession();
         tx = con.beginTransaction();
         qq = (Question) con.get(Question.class,ID);
         tx.commit(); 
      }
       catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }
         con.close(); 
         return qq;
   }
   
    
   
      public  List<Question> getQuestions()
      {
        con = HibernateUtil.getSessionFactory().openSession();
        tx = con.beginTransaction();
        q = con.createSQLQuery("select * from question");
        List<Question> qlist =  q.list();
        con.close();
        return qlist;
     }
    
    
    
}

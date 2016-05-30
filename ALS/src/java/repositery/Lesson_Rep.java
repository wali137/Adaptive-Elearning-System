/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package repositery;

import hibernate.HibernateUtil;
import java.util.List;
import model.Lesson;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

/**
 *
 * @author DemiXsoft
 */
public class Lesson_Rep {
    
    private Session con;
    private Transaction tx;
    private List<Lesson> lesson;
    private Integer usrID;
    private Query q;
    

    public int save(Lesson l){
        int ID=0;
     try{
         con = HibernateUtil.getSessionFactory().openSession();
         tx = con.beginTransaction();
         ID = (Integer) con.save(l);
         tx.commit(); 
      }
       catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }
         con.close(); 
     return ID;
   }
    
     public void update(Lesson l){
     try{
         con = HibernateUtil.getSessionFactory().openSession();
         tx = con.beginTransaction();
         con.update(l);
         tx.commit(); 
      }
       catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }
         con.close(); 
   }
     
     public void delete(Lesson l){
     try{
         con = HibernateUtil.getSessionFactory().openSession();
         tx = con.beginTransaction();
         con.delete(l);
         tx.commit(); 
      }
       catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }
         con.close(); 
   }
    
     public  List<Lesson> getLessons()
     {
        con = HibernateUtil.getSessionFactory().openSession();
        tx = con.beginTransaction();
        q = con.createSQLQuery("SELECT * FROM lesson");
        lesson =  q.list();
        con.close();
        return lesson;
     }
     
     public  List<Lesson> getLessons_Course(String cid)
     {
        con = HibernateUtil.getSessionFactory().openSession();
        tx = con.beginTransaction();
        q = con.createSQLQuery("SELECT * FROM lesson where course_id=:cid");
        q.setParameter("cid", cid);
        lesson =  q.list();
        con.close();
        return lesson;
     }
     
       public Lesson getLesson(int id){
        Lesson ls = new Lesson();
        try{
            con = HibernateUtil.getSessionFactory().openSession();
            tx = con.beginTransaction();
            ls = (Lesson) con.get(Lesson.class, id);
            tx.commit(); 
            }
                catch (HibernateException e) {
                if (tx!=null) tx.rollback();
                    e.printStackTrace(); 
                }
            con.close(); 
            return ls;
        }
     
     
     
}

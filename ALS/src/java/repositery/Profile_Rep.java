/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package repositery;

import hibernate.HibernateUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

/**
 *
 * @author DemiXsoft
 */
public class Profile_Rep {
    
    
    private Session con;
    private Transaction tx;
    private Query q;
    
    
    public  Object getUser(int ID)
    {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select * from user where id=:uid");
     q.setParameter("uid", ID);
     Object temp;
     temp = q.list().get(0);
     con.close();
     return temp;
    }
    
    
    
     public String getFirstName(int ID)
    {
        con = HibernateUtil.getSessionFactory().openSession();
        tx = con.beginTransaction();
        q = con.createSQLQuery("select First_Name from user where id=:uid");
        q.setParameter("uid", ID);
        String temp = q.list().get(0).toString();
        con.close();
        return temp;
    }
    
    public String getLastName(int ID)
    {
        con = HibernateUtil.getSessionFactory().openSession();
        tx = con.beginTransaction();
        q = con.createSQLQuery("select Last_Name from user where id=:uid");
        q.setParameter("uid", ID);
        String temp = q.list().get(0).toString();
        con.close();
        return temp;
    }
    
    public String getEmail(int ID)
    {
        con = HibernateUtil.getSessionFactory().openSession();
        tx = con.beginTransaction();
        q = con.createSQLQuery("select email from user where id=:uid");
        q.setParameter("uid", ID);
        String temp = q.list().get(0).toString();
        con.close();
        return temp;
    }
    
    public String getPhone(int ID)
    {
        con = HibernateUtil.getSessionFactory().openSession();
        tx = con.beginTransaction();
        q = con.createSQLQuery("select phone_number from user where id=:uid");
        q.setParameter("uid", ID);
        String temp = q.list().get(0).toString();
        con.close();
        return temp;
    }
    
    public String getGender(int ID)
    {
        con = HibernateUtil.getSessionFactory().openSession();
        tx = con.beginTransaction();
        q = con.createSQLQuery("select gender from user where id=:uid");
        q.setParameter("uid", ID);
        String temp = q.list().get(0).toString();
        con.close();
        return temp;
    }
    
    public String getImage(int ID)
    {
        con = HibernateUtil.getSessionFactory().openSession();
        tx = con.beginTransaction();
        q = con.createSQLQuery("select picture from user where id=:uid");
        q.setParameter("uid", ID);
        String temp = q.list().get(0).toString();
        con.close();
        return temp;
    }
    
    public String getLanguage(int ID)
    {
        con = HibernateUtil.getSessionFactory().openSession();
        tx = con.beginTransaction();
        q = con.createSQLQuery("select language from user where id=:uid");
        q.setParameter("uid", ID);
        String temp = q.list().get(0).toString();
        con.close();
        return temp;
    }
    
    public String getHobby(int ID)
    {
        con = HibernateUtil.getSessionFactory().openSession();
        tx = con.beginTransaction();
        q = con.createSQLQuery("select hobby from user where id=:uid");
        q.setParameter("uid", ID);
        String temp = q.list().get(0).toString();
        con.close();
        return temp;
    }
    
    public Date getDOB(int ID) throws ParseException
    {
        con = HibernateUtil.getSessionFactory().openSession();
        tx = con.beginTransaction();
        q = con.createSQLQuery("select DOB from user where id=:uid");
        q.setParameter("uid", ID);
        String temp = q.list().get(0).toString();
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-mm-dd");
        d = sdf.parse(temp);
        con.close();
        return d;
    }
    
    public String getAddress(int ID)
    {
        con = HibernateUtil.getSessionFactory().openSession();
        tx = con.beginTransaction();
        q = con.createSQLQuery("select address from user where id=:uid");
        q.setParameter("uid", ID);
        String temp = q.list().get(0).toString();
        con.close();
        return temp;
    }
    

       
    public void update(User u){
     try{
         con = HibernateUtil.getSessionFactory().openSession();
         tx = con.beginTransaction();
         con.update(u);
         tx.commit(); 
      }
       catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }
      con.close(); 
   }
      

    
    
}

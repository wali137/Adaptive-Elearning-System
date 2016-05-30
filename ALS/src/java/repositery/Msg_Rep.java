/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package repositery;

import hibernate.HibernateUtil;
import java.util.Date;
import java.util.List;
import model.Msg;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

/**
 *
 * @author DemiXsoft
 */
public class Msg_Rep {
    private Session con;
    private Transaction tx;
    private List<Msg> msg;
    private Integer usrID;
    private Query q;
    

    public Integer save(Msg m){
     try{
         con = HibernateUtil.getSessionFactory().openSession();
         tx = con.beginTransaction();
         usrID = (Integer) con.save(m);
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
 
     public  List<Msg> getList(int toID)
     {
        con = HibernateUtil.getSessionFactory().openSession();
        tx = con.beginTransaction();
        q = con.createSQLQuery("select * from msg where toID=:toid");
        q.setParameter("toid", toID);
        msg =  q.list();
        con.close();
        return msg;
     }
     
    
     
     public  List<Msg> getListMsgUsers(int toID)
     {
        con = HibernateUtil.getSessionFactory().openSession();
        tx = con.beginTransaction();
        q = con.createSQLQuery("select distinct fromID from msg where toID=:toid");
        q.setParameter("toid", toID);
        msg =  q.list();
        con.close();
        return msg;
     }
     
     public  List<Msg> getList(int toID,int fromID)
     {
        con = HibernateUtil.getSessionFactory().openSession();
        tx = con.beginTransaction();
        q = con.createSQLQuery("select * from msg where toID=:toid and fromID=:fid");
        q.setParameter("toid", toID);
        q.setParameter("fid", fromID);
        msg =  q.list();
        con.close();
        return msg;
     }
     
     public  String getTotalMessages(int toID,int fromID)
     {
        con = HibernateUtil.getSessionFactory().openSession();
        tx = con.beginTransaction();
        q = con.createSQLQuery("select count(id) from msg where toID=:toid and fromID=:fid");
        q.setParameter("toid", toID);
        q.setParameter("fid", fromID);
        String c =  q.list().get(0).toString();
        con.close();
        return c;
     }
     
     
     
     
     
     
}

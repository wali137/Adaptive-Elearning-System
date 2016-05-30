/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package repositery;

import hibernate.HibernateUtil;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Forum;
import model.ForumPost;
import model.User;
import org.apache.derby.client.am.DateTime;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

/**
 *
 * @author DemiXsoft
 */
public class ForumRepositery {
    
    
    private Session con;
    private Transaction tx;
    private List<Forum> f;
    private Forum cf;
    private List<ForumPost> fp;
    private Integer usrID;
    private Query q;
    

 public Integer CreateForum(Forum f){
     try{
         con = HibernateUtil.getSessionFactory().openSession();
         tx = con.beginTransaction();
         f.setDateCreated(new Date());
         f.setComments(0);
         f.setViews(0);
         usrID = (Integer) con.save(f);
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
 
  public Integer CreatePost(ForumPost fp){
     try{
         con = HibernateUtil.getSessionFactory().openSession();
         tx = con.beginTransaction();
         fp.setPostDate(new Date());
         Date d= new Date();
         fp.setPostTime(d);
         usrID = (Integer) con.save(fp);
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
 
  public void update(Forum f){
     try{
         con = HibernateUtil.getSessionFactory().openSession();
         tx = con.beginTransaction();
         //f.setDateCreated(new Date());
         //f.setComments(0);
         //f.setViews(0);
         //f.setCreatedBy("Ali");
         con.update(f);
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
 
 public  List<Forum> getForum()
 {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select u.first_name,f.forum_name,f.date_created,f.comments,f.forum_id from forum as f inner join user as u on u.ID=f.created_by");
     f =  q.list();
     tx.commit(); 
     con.close(); 
     return f;
 }
 
  public  List<Forum> getForumOfCourse(String ID)
 {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select u.first_name,f.forum_name,f.date_created,f.comments,f.forum_id from forum as f inner join user as u on u.ID=f.created_by where f.course_id=:cid");
     q.setParameter("cid", ID);
     f =  q.list();
     tx.commit(); 
     con.close(); 
     return f;
 }
 
  public  Forum getCurrentForum(int fid)
 {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select * from forum where forum_id=:fid");
     q.setParameter("fid", fid);
     cf =  (Forum) q.list().get(0);
     tx.commit(); 
     con.close(); 
     return cf;
 }
 
  public  List<ForumPost> getForumPosts(int forum_id)
 {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select u.first_name,f.post_text from forum_post as f inner join user as u on u.ID=f.user_id where f.forum_id=:fid");
     q.setParameter("fid", forum_id);
     fp =  q.list();
     tx.commit(); 
     con.close(); 
     return fp;
 }
  

 
  public  String getTitle(int ID)
 {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select forum_name from forum where forum_id=:pid");
     q.setParameter("pid", ID);
     tx.commit(); 
     String ret = q.list().get(0).toString();
     con.close(); 
     return  ret;
 }
  
    public  String getTodayComments(String cid)
 {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select count(comments) from forum where course_id=:cid");
     q.setParameter("cid", cid);
     tx.commit(); 
     String ret = q.list().get(0).toString();
     con.close(); 
     return  ret;
 }
    
    
        public  List<ForumPost> getLatestComments()
        {
            con = HibernateUtil.getSessionFactory().openSession();
            tx = con.beginTransaction();
            q = con.createSQLQuery("select * from forum_post order by ID desc limit 3");
            tx.commit(); 
            List<ForumPost> ret= q.list();
            con.close(); 
            return  ret;
        }
        
        public  List<ForumPost> getTopCommentors()
        {
            con = HibernateUtil.getSessionFactory().openSession();
            tx = con.beginTransaction();
            q = con.createSQLQuery("select count(ID),ID from forum_post limit 3");
            tx.commit(); 
            List<ForumPost> ret= q.list();
            con.close(); 
            return  ret;
        }
        
        public  List<User> getMembers(int uid)
        {
            con = HibernateUtil.getSessionFactory().openSession();
            tx = con.beginTransaction();
            q = con.createSQLQuery("select distinct u.first_name,u.last_name,u.id from user as u inner join teacher_course as tc on tc.teacher_id=u.ID inner join teacher_course as tcc on tc.course_id=tcc.course_id where tcc.teacher_id=:uid");
            q.setParameter("uid", uid);
            tx.commit(); 
            List<User> ret= q.list();
            con.close(); 
            return  ret;
        }
    
    public Forum getForumModel(Forum f)
    {
        con = HibernateUtil.getSessionFactory().openSession();
        tx = con.beginTransaction();
        q = con.createSQLQuery("select * from forum where forum_id=:fid");
        q.setParameter("fid", f.getForumId());
        tx.commit(); 
        List<Forum> fl = q.list();
        Forum ret = fl.get(0);
        con.close(); 
        return  ret;
    }
  
   public  String getMostTopicsCourse()
 {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     Query temp;
     temp = con.createSQLQuery("select max(comments) from forum");
     String maxC = temp.list().get(0).toString();
     q = con.createSQLQuery("select c.course_name from forum as f left join course as c on f.course_id=c.course_id where f.comments=:mc");
     q.setParameter("mc", maxC);
     tx.commit(); 
     String ret = q.list().get(0).toString();
     con.close(); 
     return  ret;
 }
   
      public  String getTopicsOfCourse(String ID)
 {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     //Query temp;
     q = con.createSQLQuery("select count(forum_id) from forum where course_id=:cid");
     q.setParameter("cid", ID);
     tx.commit(); 
     String ret = q.list().get(0).toString();
     con.close(); 
     return  ret;
 }
   
      public  String getMostCommentedTopic()
 {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     Query temp;
     temp = con.createSQLQuery("select max(comments) from forum");
     String maxC = temp.list().get(0).toString();
     q = con.createSQLQuery("select forum_name from forum where comments=:mc");
     q.setParameter("mc", maxC);
     tx.commit(); 
     String ret = q.list().get(0).toString();
     con.close(); 
     return  ret;
 }
      
            public  String getMostViewedTopic()
 {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     Query temp;
     temp = con.createSQLQuery("select max(views) from forum");
     String maxV = temp.list().get(0).toString();
     q = con.createSQLQuery("select forum_name from forum where views=:mv");
     q.setParameter("mv", maxV);
     tx.commit(); 
     String ret = q.list().get(0).toString();
     con.close(); 
     return  ret;
 }
  
    public  String getTopic(int ID)
 {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select details from forum where forum_id=:pid");
     q.setParameter("pid", ID);
     tx.commit(); 
     String ret = q.list().get(0).toString();
     con.close(); 
     return  ret;
 }
  
    public  String getCourseID(int ID)
 {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select course_id from forum where forum_id=:pid");
     q.setParameter("pid", ID);
     tx.commit(); 
     String ret = q.list().get(0).toString();
     con.close(); 
     return  ret;
 }
    
        public  void resetComments(int fid, int comments)
 {

     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("update forum set comments=:c where forum_id=:fid");
     q.setParameter("fid", fid);
     q.setParameter("c", comments);
     int rec = q.executeUpdate();
     tx.commit();
     //System.out.println("rec = " + rec);
     con.close(); 
 }
    
        public  String getUserID(int ID)
 {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select created_by from forum where forum_id=:pid");
     q.setParameter("pid", ID);
     tx.commit(); 
     String ret = q.list().get(0).toString();
     con.close(); 
     return  ret;
 }
    
    public  String getCourseName(String CID)
 {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select course_name from course where course_id=:cid");
     q.setParameter("cid", CID);
     tx.commit(); 
     String ret = q.list().get(0).toString();
     con.close(); 
     return  ret;
 }
    
     public  String getUserName(String UID)
    {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select first_name from user where ID=:uid");
     q.setParameter("uid", UID);
     tx.commit(); 
     String ret = q.list().get(0).toString();
     con.close(); 
     return  ret;
     
    }
     

     
      public  String getMembers(String CID)
    {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select count(teacher_id) from teacher_course where course_id=:cid");
     q.setParameter("cid", CID);
     String mems = q.list().get(0).toString();
     tx.commit(); 
     con.close(); 
     return  mems;
     
    }
        
    
    
    
 
 public void DeleteForum(Forum f)
 {
    try{
         con = HibernateUtil.getSessionFactory().openSession();
         tx = con.beginTransaction();
         con.delete(f);
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

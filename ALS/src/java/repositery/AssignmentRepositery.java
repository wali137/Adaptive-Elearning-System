
package repositery;

import controller.Helper;
import hibernate.HibernateUtil;
import java.util.List;
import model.Assignment;
import model.AssignmentResult;
import model.AssignmentSubmission;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

public class AssignmentRepositery {

    private Session con;
    private Transaction tx;
    private List<Assignment> a;
    private List<AssignmentSubmission> sub;
    private Integer usrID;
    private Query q;
    private List<AssignmentResult> res;
    

 public Integer CreateAssignment(Assignment a){
     try{
         con = HibernateUtil.getSessionFactory().openSession();
         tx = con.beginTransaction();
         a.setSubmissions(0);
         usrID = (Integer) con.save(a);
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
 
 public Integer SubmitAssignment(AssignmentSubmission sub){
     try{
         con = HibernateUtil.getSessionFactory().openSession();
         tx = con.beginTransaction();
         usrID = (Integer) con.save(sub);
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
 
 
  public Integer SubmitResult(AssignmentResult res){
     try{
         con = HibernateUtil.getSessionFactory().openSession();
         tx = con.beginTransaction();
         usrID = (Integer) con.save(res);
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
 
 
  public void update(Assignment a){
     try{
         con = HibernateUtil.getSessionFactory().openSession();
         tx = con.beginTransaction();
         con.update(a);
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
  


public  String getAssignStatement(int ID)
 {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select statement from assignment where id=:aid");
     q.setParameter("aid", ID);
     String temp = q.list().get(0).toString();
     con.close();
     return temp;
 }

public  String getCreatedBy(int ID)
 {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select first_name from user where id=:uid");
     q.setParameter("uid", ID);
     String temp = q.list().get(0).toString();
     con.close();
     return temp;
 }

public  String getAttachment(int ID)
 {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select Slide from assignment where id=:uid");
     q.setParameter("uid", ID);
     String temp = q.list().get(0).toString();
     con.close();
     return temp;
 }

public  void IncrementSubmission(int ID)
 {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select submissions from assignment where id=:id");
     q.setParameter("id", ID);
     String temp = q.list().get(0).toString();
     int num = Integer.parseInt(temp);
     num = num +  1;
     q = con.createSQLQuery("update assignment set submissions=:num where id=:id");
     q.setParameter("id", ID);
     q.setParameter("num", num);
     q.executeUpdate();
     tx.commit();
     con.close();
 }


 public  List<Assignment> getAssignment(String CID)
 {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("SELECT * FROM assignment where course_id=:cid");
     q.setParameter("cid", CID);
     a =  q.list();
     con.close();
     return a;
 }
 
 Helper help = new Helper();
 
 

  
    public  int isSubmitted(int UID,int AID)
 {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("SELECT count(id) from assignment_submission where submitted_by=:uid and assignment_id=:aid");
     q.setParameter("uid", UID);
     q.setParameter("aid", AID);
     
     String temp = q.list().get(0).toString();
     int ret = 0;
     int c = Integer.parseInt(temp);
     if (c >0)
     {
         q = con.createSQLQuery("SELECT assignment_id from assignment_submission where submitted_by=:uid and assignment_id=:aid");
         q.setParameter("uid", UID);
         q.setParameter("aid", AID);
     
         ret = Integer.parseInt(q.list().get(0).toString());
         //ret= ret + 1;
     }
     con.close();
     return  ret;
 }
 
 
  public  String getTitle(int ID)
 {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select assignment_name from assignment where id=:pid");
     q.setParameter("pid", ID);
     String temp = q.list().get(0).toString();
     con.close();
     return  temp;
 }
  
  
  public List<Assignment> submissions(int ID,String cid)
  {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select * from assignment where id not in(select assignment_id from assignment_submission inner join assignment on assignment_submission.assignment_id=assignment.id where assignment_submission.submitted_by=:id and assignment.course_id=:cid) and course_id=:cid");
     q.setParameter("id", ID);
     q.setParameter("cid", cid);
     a =  q.list();
     con.close();
     return a;
  }
  
  public List<AssignmentResult> results(int ID)
  {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select r.*,u.first_name from assignment_result r, user u where r.assignment_id=:id and u.id=r.student_id;");
     q.setParameter("id", ID);
     res =  q.list();
     con.close();
     return res;
  }
  
  
    public  String getDueDate(int ID)
 {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select due_date from assignment where id=:pid");
     q.setParameter("pid", ID);
     String temp = q.list().get(0).toString();
     con.close();
     return  temp;
 }
    
      public  String getMarks(int ID)
 {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select total_marks from assignment where id=:pid");
     q.setParameter("pid", ID);
     String temp = q.list().get(0).toString();
     con.close();
     return  temp;
 }
  
    public  String getCourseID(int ID)
 {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select course_id from assignment where id=:pid");
     q.setParameter("pid", ID);
     String temp = q.list().get(0).toString();
     con.close();
     return  temp;
 }
 
 public void Delete(Assignment a)
 {
     try{
         con = HibernateUtil.getSessionFactory().openSession();
         tx = con.beginTransaction();
         con.delete(a);
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

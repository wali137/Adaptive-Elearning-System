
package repositery;

import hibernate.HibernateUtil;
import java.util.List;
import model.Course;
import model.TeacherCourse;
import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

public class admin_rep {

    private Session con;
    private Transaction tx;
    private List<User> a;
    private Query q,q1,q2,q3,q4,q5;
    //private Course_Rep cr;
    public List<User> getFollowUp(User u, Course c)
    {
        //cr = new Course_Rep();
        con = HibernateUtil.getSessionFactory().openSession();
        tx = con.beginTransaction();
        if(c.getCourseId() != null && u.getFirstName() != null && u.getUserType() != null)
        {
            q = con.createSQLQuery("select u.first_name from user as u inner join course as c on u.course_id=c.course_id where u.user_type=:utype and u.first_name like '%:uname' and c.course_id=:cid");
        }
        if(c.getCourseId() == null && u.getFirstName() != null && u.getUserType() != null)
        {
            q = con.createSQLQuery("select first_name from user where user_type=:utype and first_name like '%:uname'");
        }
        if(c.getCourseId() == null && u.getFirstName() == null && u.getUserType() != null)
        {
            q = con.createSQLQuery("select first_name from user where u.user_type=:utype");
        }
        if(c.getCourseId() != null && u.getFirstName() == null && u.getUserType() != null)
        {
            q = con.createSQLQuery("select u.first_name from user as u inner join course as c on u.course_id=c.course_id where u.user_type=:utype and c.course_id=:cid");
        }
        if(c.getCourseId() != null && u.getFirstName() != null && u.getUserType() == null)
        {
            q = con.createSQLQuery("select u.first_name from user as u inner join course as c on u.course_id=c.course_id where u.first_name like '%:uname' and c.course_id=:cid");
        }
        if(c.getCourseId() == null && u.getFirstName() == null && u.getUserType() == null)
        {
            q = con.createSQLQuery("select first_name from user");
        }
        
        q.setParameter("user", u.getId());
        q.setParameter("utype", u.getUserType());
        q.setParameter("uname", u.getFirstName());
        q.setParameter("cid", c.getCourseId());
        List<User> ret = q.list();
        tx.commit();
        con.close(); 
        return  ret;
    }
    
    
        public void assign_course(TeacherCourse tc){
     try{
         con = HibernateUtil.getSessionFactory().openSession();
         tx = con.beginTransaction();         
         con.save(tc);
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

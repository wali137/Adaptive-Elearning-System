
package repositery;

import hibernate.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Notifications;
import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

public class User_Rep {
    
    private Session con;
    private Transaction tx;
    private List<User> a;
    private Query q,q1,q2,q3,q4,q5,q6;
    
    public String check_login(User u)
    {
        String exists="none";
        con = HibernateUtil.getSessionFactory().openSession();
        tx = con.beginTransaction();
        q1 = con.createSQLQuery("select ID from user where user_name=:user and password=:pass");
        q2 = con.createSQLQuery("select first_name from user where user_name=:user and password=:pass");
        q3 = con.createSQLQuery("select user_type from user where user_name=:user and password=:pass");
        q4 = con.createSQLQuery("select last_name from user where user_name=:user and password=:pass");
        q5 = con.createSQLQuery("select email from user where user_name=:user and password=:pass");
        q6 = con.createSQLQuery("select picture from user where user_name=:user and password=:pass");
        
        q1.setParameter("user", u.getUserName());
        q1.setParameter("pass", u.getPassword());
        q2.setParameter("user", u.getUserName());
        q2.setParameter("pass", u.getPassword());
        q3.setParameter("user", u.getUserName());
        q3.setParameter("pass", u.getPassword());
        q4.setParameter("user", u.getUserName());
        q4.setParameter("pass", u.getPassword());
        q5.setParameter("user", u.getUserName());
        q5.setParameter("pass", u.getPassword());
        q6.setParameter("user", u.getUserName());
        q6.setParameter("pass", u.getPassword());
        
        int x = q1.list().size();
        
        if(x==0){
            exists = "none";
        }else{
            FacesContext context = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
            String id = q1.list().get(0).toString();
            session.setAttribute("id", id);
            String name = q2.list().get(0).toString();
            session.setAttribute("name", name);
            String lname = q4.list().get(0).toString();
            session.setAttribute("lname", lname);
            String email = q5.list().get(0).toString();
            session.setAttribute("email", email);
            
            exists = q3.list().get(0).toString();
            session.setAttribute("type", exists);
            
            String pic = q6.list().get(0).toString();
            session.setAttribute("pic", pic);
            
            
        }
        tx.commit();
        con.close();
        return exists;
    }
    
    public String getUserFirstName(int ID)
    {
        if(ID != 0)
        {
        con = HibernateUtil.getSessionFactory().openSession();
        tx = con.beginTransaction();
        q1 = con.createSQLQuery("select first_name from user where ID=:user");
        q1.setParameter("user", ID);
        String ret = q1.list().get(0).toString();
        tx.commit();
        con.close(); 
        return  ret;
        }else{
            return null;
        }
    }
    

    
    
    public String getImageOfUser(int ID)
    {
        if(ID != 0)
        {
        con = HibernateUtil.getSessionFactory().openSession();
        tx = con.beginTransaction();
        q1 = con.createSQLQuery("select picture from user where ID=:user");
        q1.setParameter("user", ID);
        String ret = q1.list().get(0).toString();
        tx.commit();
        con.close(); 
        return  ret;
        }else{
            return null;
        }
    }
    
    public String logged_username(int ID)
    {
        if(ID != 0)
        {
        con = HibernateUtil.getSessionFactory().openSession();
        tx = con.beginTransaction();
        q1 = con.createSQLQuery("select user_name from user where ID=:user");
        q1.setParameter("user", ID);
        String ret = q1.list().get(0).toString();
        tx.commit();
        con.close(); 
        return  ret;
        }else{
            return null;
        }
    }
    
    
    public List<User> getTeachers()
    {
        con = HibernateUtil.getSessionFactory().openSession();
        tx = con.beginTransaction();
        q1 = con.createSQLQuery("select * from user where user_type='teacher'");
        a = q1.list();
        tx.commit();
        con.close(); 
        return  a;
    }
    
    
    
    public String logged_password(int ID)
    {
        if(ID != 0)
        {
        con = HibernateUtil.getSessionFactory().openSession();
        tx = con.beginTransaction();
        q1 = con.createSQLQuery("select password from user where ID=:user");
        q1.setParameter("user", ID);
        String ret = q1.list().get(0).toString();
        tx.commit();
        con.close(); 
        return  ret;
        }else{
            return null;
        }
    }
    
    public String logged_usertype(int ID)
    {
        if(ID != 0)
        {
        con = HibernateUtil.getSessionFactory().openSession();
        tx = con.beginTransaction();
        q1 = con.createSQLQuery("select user_type from user where ID=:user");
        q1.setParameter("user", ID);
        String ret = q1.list().get(0).toString();
        tx.commit();
        con.close(); 
        return  ret;
        }else{
            return null;
        }
    }
    
    public void change_password(int UID, String pass)
    {
        con = HibernateUtil.getSessionFactory().openSession();
        tx = con.beginTransaction();
        q1 = con.createSQLQuery("update user set password=:pass where id=:uid");
        q1.setParameter("uid", UID);
        q1.setParameter("pass", pass);
        q1.executeUpdate();
        tx.commit();
        con.close(); 
    }
    
    public List<String> getAdminNoties()
    {
        List<String> ret;
        ret = new ArrayList<String>();
        con = HibernateUtil.getSessionFactory().openSession();
        tx = con.beginTransaction();
        q1 = con.createSQLQuery("SELECT  CONCAT(u.first_name,' ',u.last_name,' ',n.details,' of Course ',c.course_name) FROM notifications AS n LEFT JOIN USER AS u ON n.`Student_id`=u.`ID`LEFT JOIN course c ON c.`course_id`=n.`course_id`;");
        ret = q1.list();
        tx.commit();
        con.close();
        return ret;
    }
    
    public List<String> getNoties(User u)
    {
        List<String> ret;
        ret = new ArrayList<String>();
        con = HibernateUtil.getSessionFactory().openSession();
        tx = con.beginTransaction();
        q1 = con.createSQLQuery("select course_id from teacher_course where teacher_id=:user");
        q1.setParameter("user", u.getId());
        int len = q1.list().size();
        if (len==0)
        {
            
        }else{
            for(int i = 0 ; i< len; i++)
            {
                String cid = q1.list().get(i).toString();
                String ut = u.getUserType();
                if(ut.contains("teacher"))
                {
                    q2 = con.createSQLQuery("select details from notifications where course_id=:course and noti_for=:nf");
                    q2.setParameter("nf", "teacher");
                    q2.setParameter("course", cid);
                    
                    q3 = con.createSQLQuery("select student_id from notifications where course_id=:course and noti_for=:nf");
                    q3.setParameter("nf", "teacher");
                    q3.setParameter("course", cid);
                
                    
                }else{
                    q2 = con.createSQLQuery("select details from notifications where course_id=:course and noti_for=:nf");
                    q2.setParameter("nf", "student");
                    q2.setParameter("course", cid);
                    
                    q3 = con.createSQLQuery("select student_id from notifications where course_id=:course and noti_for=:nf");
                    q3.setParameter("nf", "student");
                    q3.setParameter("course", cid);
                
                    
                }
                
                
                int len2 = q2.list().size();
                if(len2 == 0)
                {
                    
                }else{
                    for(int j =0; j<len2;j++)
                    {
                        q4 = con.createSQLQuery("select first_name from user where ID=:uidx");
                        q4.setParameter("uidx", q3.list().get(j).toString());
                        //ret.add("abc");
                        ret.add(q4.list().get(0).toString() +" : "+q2.list().get(j).toString());
                        //String test1 = q4.list().get(0).toString(); 
                                //String t2=q1.list().get(j).toString();
                    }
                }
            } 
        }
        

        tx.commit();
        con.close(); 

        return ret;
    }
    
    
    public int Register_Student(User u){
        int ID=0;
     try{
         con = HibernateUtil.getSessionFactory().openSession();
         tx = con.beginTransaction();
         u.setUserType("student");
         u.setUserName(u.getEmail());
         ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
         String rootDir = "data/user/" + u.getUserName() + ".jpg";
         u.setPicture(rootDir);
         ID = (Integer) con.save(u);
         tx.commit(); 
      }
       catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }
     finally {
         con.close(); 
      }
     return ID;
   }
   
    public int Register_Admin(User u){
        int ID=0;
     try{
         con = HibernateUtil.getSessionFactory().openSession();
         tx = con.beginTransaction();
         u.setUserType("admin");
         u.setUserName(u.getEmail());
         ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
         String rootDir = "data/user/" + u.getUserName() + ".jpg";
         u.setPicture(rootDir);
         ID = (Integer) con.save(u);
         tx.commit(); 
      }
       catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }
     finally {
         con.close(); 
      }
     return ID;
   }
    
    
    
    public int Register_Teacher(User u){
        int ID=0;
     try{
         con = HibernateUtil.getSessionFactory().openSession();
         tx = con.beginTransaction();
         u.setUserType("teacher");
         u.setUserName(u.getEmail());
         ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
         String rootDir = "data/user/" + u.getUserName() + ".jpg";
         u.setPicture(rootDir);
         ID = (Integer) con.save(u);
         tx.commit(); 
      }
       catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }
     finally {
         con.close(); 
      }
     return ID;
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
     finally {
         con.close(); 
      }
   }
    
    
    
    
}

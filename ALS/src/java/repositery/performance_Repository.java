/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package repositery;

import controller.Helper;
import hibernate.HibernateUtil;
import java.util.List;
import model.AssignmentResult;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
/**
 *
 * @author DemiXsoft
 */
public class performance_Repository {
    
    Helper help = new Helper();
    
    private Session con;
    private Transaction tx;
    private Integer usrID;
    private Query q;
    private Query q1;
    
    
    public  int getStudent_Marks_Assignment()
     {
         int id = help.toInt(help.getCurrentUserID());
        con = HibernateUtil.getSessionFactory().openSession();
        tx = con.beginTransaction();
        q = con.createSQLQuery("SELECT sum(marks) FROM assignment_result where student_id=:id");
        q.setParameter("id", id);
        int marks =  help.toInt(q.list().get(0).toString());
        con.close();
        return marks;
     }
    
    public  int getStudent_Marks_Quiz()
     {
         int id = help.toInt(help.getCurrentUserID());
        con = HibernateUtil.getSessionFactory().openSession();
        tx = con.beginTransaction();
        q = con.createSQLQuery("SELECT sum(marks_obtained) FROM quiz_result where student_id=:id");
        q.setParameter("id", id);
        int marks =  help.toInt(q.list().get(0).toString());
        con.close();
        return marks;
     }
    
    public  int getStudent_Marks_Quiz_Topic(int tid,int sid,String cid)
     {
        con = HibernateUtil.getSessionFactory().openSession();
        tx = con.beginTransaction();
        q = con.createSQLQuery("SELECT sum(marks_obtained) as myObtained FROM quiz_result qr left join quiz_submission qs on qr.quiz_submission_id=qs.quiz_submission_id left join quiz q on qs.quiz_id=q.id where qr.student_id=:sid and q.topic_id=:tid and q.course_id=:cid");
        q.setParameter("sid", sid);
        q.setParameter("tid", tid);
        q.setParameter("cid", cid);
        int marks = 0;
        int x = q.list().size();
        if(q.list().size() > 1)
        {
            marks =  help.toInt(q.list().get(0).toString());
        }else{
            marks = 0;
        }
        con.close();
        return marks;
     }
    
    public  List getStudent_Quiz_Topic(int tid,int sid,String cid)
     {
        con = HibernateUtil.getSessionFactory().openSession();
        tx = con.beginTransaction();
        q = con.createSQLQuery("SELECT sum(marks_obtained),sum(totak_marks) FROM quiz_result qr left join quiz_submission qs on qr.quiz_submission_id=qs.quiz_submission_id left join quiz q on qs.quiz_id=q.id where qr.student_id=:sid and q.topic_id=:tid and q.course_id=:cid");
        q.setParameter("sid", sid);
        q.setParameter("tid", tid);
        q.setParameter("cid", cid);
        List marks;
        marks = q.list();
        con.close();
        return marks;
     }
    
    
    
    
    
    public  String getOtherStudent_Marks()
     {
         int id = help.toInt(help.getCurrentUserID());
        con = HibernateUtil.getSessionFactory().openSession();
        tx = con.beginTransaction();
        q1 = con.createSQLQuery("SELECT student_id FROM assignment_result where student_id!=:id");
        q1.setParameter("id", id);
        int total = q1.list().size();
        String mark_list = "";
        for (int i =0; i< total;i++)
        {
            int osi = help.toInt(q1.list().get(i).toString());
            q = con.createSQLQuery("SELECT sum(marks) FROM assignment_result where student_id=:id");
            q.setParameter("id", id);
            int marks =  help.toInt(q.list().get(0).toString());
            if(i<total-1)
            {
                mark_list = mark_list + marks + ",";
            }else{
                mark_list = mark_list + marks;
            }
        }
        
        con.close();
        return mark_list;
     }
    
    public  int getStudent_TotalMarks()
     {
         int id = help.toInt(help.getCurrentUserID());
        con = HibernateUtil.getSessionFactory().openSession();
        tx = con.beginTransaction();
        q = con.createSQLQuery("SELECT sum(a.total_marks) FROM assignment a left join assignment_submission s on a.id=s.assignment_id where s.submitted_by=:id");
        q.setParameter("id", id);
        int marks =  help.toInt(q.list().get(0).toString());
        con.close();
        return marks;
     }
    
    public  String getOtherStudent_TotalMarks()
     {
         int id = help.toInt(help.getCurrentUserID());
        con = HibernateUtil.getSessionFactory().openSession();
        tx = con.beginTransaction();
        q1 = con.createSQLQuery("SELECT student_id FROM assignment_result where student_id!=:id");
        q1.setParameter("id", id);
        int total = q1.list().size();
        String mark_list = "";
        for (int i =0; i< total;i++)
        {
            int osi = help.toInt(q1.list().get(i).toString());
            q = con.createSQLQuery("SELECT sum(a.total_marks) FROM assignment a left join assignment_submission s on a.id=s.assignment_id where s.submitted_by=:id");
            q.setParameter("id", id);
            int marks =  help.toInt(q.list().get(0).toString());
            if(i<total-1)
            {
                mark_list = mark_list + marks + ",";
            }else{
                mark_list = mark_list + marks;
            }
        }
        
        con.close();
        return mark_list;
     }
    
    
    
    
}

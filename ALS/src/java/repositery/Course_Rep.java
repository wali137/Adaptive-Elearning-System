
package repositery;

import hibernate.HibernateUtil;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import model.Course;
import model.CourseSection;
import model.CouseTopic;
import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;


public class Course_Rep {
    
    private Session con;
    private Transaction tx;
    private List<Course> c;
    private List<CourseSection> sec;
    private List<CouseTopic> topic;
    private Integer usrID;
    private Query q;
    
    public  List<Course> getCourseList()
    {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select course_name from course");
     c = q.list();
     List<Course> temp = c;
     con.close();
     return temp;
    }
    
    public  String getTopicName(int tid)
    {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select topic_name from couse_topic where topic_id=:tid");
     q.setParameter("tid", tid);
     String x = q.list().get(0).toString();
     con.close();
     return x;
    }
    
    public  String getQuizAtt(int lid,String cid)
    {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("SELECT (SELECT CASE WHEN `quiz_result`.`marks_obtained` < 7 THEN 'False' ELSE 'True' END  Result FROM quiz INNER JOIN `quiz_submission` ON quiz.`ID` = `quiz_submission`.`quiz_id` INNER JOIN `quiz_result` ON `quiz_result`.`quiz_submission_id` = `quiz_submission`.`quiz_submission_id` WHERE quiz.`Topic_ID` = lesson.`Pre_Topic_id`) FROM lesson WHERE lesson.`ID`=:lid  AND lesson.`Course_ID` =:cid");
     q.setParameter("cid", cid);
     q.setParameter("lid", lid);
     String x="True";
     if(q.list().size()>0)
     {
        x = q.list().get(0).toString();
     }
     con.close();
     return x;
    }
    
    public  List<CourseSection> getSectionList(String CID)
    {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select section_name from course_section where course_id=:cid");
     q.setParameter("cid", CID);
     sec = q.list();
     List<CourseSection> temp = sec;
     con.close();
     return temp;
    }
    
    public  List<CourseSection> getSectionListID(String CID)
    {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select section_id from course_section where course_id=:cid");
     q.setParameter("cid", CID);
     sec = q.list();
     List<CourseSection> temp = sec;
     con.close();
     return temp;
    }
    
    public  List<CouseTopic> getTopicList(int SID)
    {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select topic_name from couse_topic where section_id=:sid");
     q.setParameter("sid", SID);
     topic = q.list();
     List<CouseTopic> temp = topic;
     con.close();
     return temp;
    }
    
    public  String getPreReqList(int SID)
    {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select sum(pre_id) from couse_topic where section_id=:sid");
     q.setParameter("sid", SID);
     String t;
     if(q.list().size() > 0)
     {
         try{
        t = q.list().get(0).toString();
         }catch(Exception e)
        {
            t = "";
        }
     }else{
         t="";
     }
     int x = 0;
     if (t.length()>0)
     {
         x = Integer.parseInt(t);
     }
     if (x>0)
     {
         t="Yes";
     }else{
         t="No";
     }
     String temp = t;
     con.close();
     return temp;
    }
    
    public  List<CouseTopic> getTopicListID(int SID)
    {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select topic_id from couse_topic where section_id=:sid");
     q.setParameter("sid", SID);
     topic = q.list();
     List<CouseTopic> temp = topic;
     con.close();
     return temp;
    }
    
    public  List<Course> getCourseListID()
    {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select course_id from course");
     c = q.list();
     List<Course> temp = c;
     con.close();
     return temp;
    }
    
    public List<Course> getTeacherCourse(int teacherID)
    {
        con = HibernateUtil.getSessionFactory().openSession();
        tx = con.beginTransaction();
        q = con.createSQLQuery("select c.course_name from course as c inner join teacher_course as tc on c.course_id=tc.course_id inner join user as u on tc.teacher_id=u.ID where u.ID=:tid");
        q.setParameter("tid", teacherID);
        
        c=q.list();
        List<Course> temp = c;
        con.close();
        return temp;
    }
    
    public List<Course> getTeacherCourseID(int teacherID)
    {
        con = HibernateUtil.getSessionFactory().openSession();
        tx = con.beginTransaction();
        q = con.createSQLQuery("select c.course_id from course as c inner join teacher_course as tc on c.course_id=tc.course_id inner join user as u on tc.teacher_id=u.ID where u.ID=:tid");
        q.setParameter("tid", teacherID);
        
        c=q.list();
        List<Course> temp = c;
        con.close();
        return temp;
    }
    
    public List<Course> getStudentCourseX(int studentID)
    {
        con = HibernateUtil.getSessionFactory().openSession();
        tx = con.beginTransaction();
        q = con.createSQLQuery("select c.course_name from course as c inner join student_registration as tc on c.course_id=tc.course_id inner join user as u on tc.student_id=u.ID where u.ID=:sid");
        q.setParameter("sid", studentID);
        
        c=q.list();
        List<Course> temp = c;
        con.close();
        return temp;
    }
    
    public List<Course> getStudentCourseIDX(int studentID)
    {
        con = HibernateUtil.getSessionFactory().openSession();
        tx = con.beginTransaction();
        q = con.createSQLQuery("select c.course_id from course as c inner join student_registration as tc on c.course_id=tc.course_id inner join user as u on tc.student_id=u.ID where u.ID=:sid");
        q.setParameter("sid", studentID);
        
        c=q.list();
        List<Course> temp = c;
        con.close();
        return temp;
    }
    
    
    public  String getCourseName(String ID)
    {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select course_name from course where course_id=:cid");
     q.setParameter("cid", ID);
     String temp = q.list().get(0).toString();
     con.close();
     return temp;
    }
    
    public  String getCourseProgram(String ID)
    {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select program_id from course where course_id=:cid");
     q.setParameter("cid", ID);
     int pid = Integer.parseInt(q.list().get(0).toString());
     q = con.createSQLQuery("select program_name from program where program_id=:pid");
     q.setParameter("pid", pid);
     String temp = q.list().get(0).toString();
     con.close();
     return temp;
    }
    
    public  String getCourseDpt(String ID)
    {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select program_id from course where course_id=:cid");
     q.setParameter("cid", ID);
     int pid = Integer.parseInt(q.list().get(0).toString());
     q = con.createSQLQuery("select department_id from program where program_id=:pid");
     q.setParameter("pid", pid);
     int did = Integer.parseInt( q.list().get(0).toString());
     q = con.createSQLQuery("select department_name from department where department_id=:did");
     q.setParameter("did", did);
     String temp = q.list().get(0).toString();
     con.close();
     return temp;
    }
    
    public  String getCourseStudents(String ID)
    {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("SELECT count(tc.id) FROM student_registration AS tc RIGHT JOIN user AS u ON tc.student_id = u.id where u.user_type=:utype and tc.course_id=:cid");
     q.setParameter("cid", ID);
     q.setParameter("utype", "student");
     String temp = q.list().get(0).toString();
     con.close();
     return temp;
    }
    
    public  List<String> getStudentList(String ID)
    {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("SELECT concat(u.first_name,' ',u.last_name) FROM user AS u LEFT JOIN student_registration AS ur ON ur.student_id = u.id where ur.course_id=:cid");
     q.setParameter("cid", ID);
     List<String> temp = q.list();
     con.close();
     return temp;
    }
    
    public  List<User> getStudentListwithID(String ID)
    {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("SELECT u.id,concat(u.first_name,' ',u.last_name) FROM user AS u LEFT JOIN student_registration AS ur ON ur.student_id = u.id where ur.course_id=:cid");
     q.setParameter("cid", ID);
     List<User> temp = q.list();
     con.close();
     return temp;
    }
    
    
    public  String getAssignmentPercentage(int sid,String CID)
    {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("SELECT SUM(marks_obtained)/SUM(totak_marks) * 100 AS perc FROM student INNER JOIN quiz_submission ON student.student_id =  quiz_submission.student_id INNER JOIN quiz ON quiz.ID = quiz_submission.quiz_id INNER JOIN quiz_result ON quiz_result.quiz_submission_id = quiz_submission.quiz_submission_id INNER JOIN course ON course.course_id = quiz.course_id WHERE student.student_id  = :sid and course.course_id=:cid");
     q.setParameter("cid", CID);
     q.setParameter("sid", sid);
     String temp = q.list().get(0).toString();
     con.close();
     return temp;
    }
    
    public  String getQuizPercentage(int sid,String CID)
    {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("SELECT SUM(marks)/SUM(total_marks) * 100 AS perc FROM student INNER JOIN assignment_submission ON student.student_id =  assignment_submission.submitted_by INNER JOIN assignment ON assignment.ID = assignment_submission.assignment_id INNER JOIN assignment_result ON assignment_result.assignment_id = assignment_submission.assignment_id INNER JOIN course ON course.course_id = assignment.course_id WHERE student.student_id  =:sid and course.course_id=:cid");
     q.setParameter("cid", CID);
     q.setParameter("sid", sid);
     String temp = q.list().get(0).toString();
     con.close();
     return temp;
    }
    
    public  List<Object> getAdminBayse()
    {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("SELECT t1.*,t2.assignmentPerc, CASE WHEN t2.assignmentPerc > 90 THEN 'Excellent' WHEN t2.assignmentPerc < 90 AND assignmentPerc > 75 THEN 'Good' WHEN t2.assignmentPerc < 75 AND assignmentPerc > 65 THEN 'Average' WHEN t2.assignmentPerc < 60 THEN 'Below Average' END AssignmentPerformance,CASE WHEN t1.quizPerc > 90 THEN 'Excellent' WHEN t1.quizPerc < 90 AND quizPerc > 75 THEN 'Good' WHEN t1.quizPerc < 75 AND quizPerc > 60 THEN 'Average' WHEN t1.quizPerc < 60 THEN 'Below Average' END QuizPerformance, t2.total_assignmnt,t3.total_courses,student.ranking FROM  (SELECT student.student_id ,COUNT(*) AS total_quizes,(SUM(marks_obtained)/SUM(totak_marks)) * 100 AS quizPerc FROM student INNER JOIN quiz_submission ON quiz_submission.student_id = student.student_id INNER JOIN quiz_result ON quiz_result.quiz_submission_id = quiz_submission.quiz_submission_id GROUP BY student.student_id ) t1 INNER JOIN (SELECT student_registration.student_id,COUNT(*) AS total_assignmnt,(SUM(Marks)/SUM(Total_marks))*100 AS assignmentPerc FROM student_registration INNER JOIN assignment ON student_registration.course_id = assignment.course_id INNER JOIN assignment_result ON assignment_result.assignment_id = assignment.id GROUP BY student_registration.student_id ) t2 ON t1.student_id = t2.student_id LEFT OUTER JOIN  (SELECT student_id,COUNT(*) AS total_courses FROM student_registration GROUP BY student_id) t3 ON t2.student_id = t3.student_id LEFT OUTER JOIN student ON student.`student_id` = t1.student_id;");
     List<Object> temp = q.list();
     con.close();
     return temp;
    }
    
    public  List<Object> getStudentBayse(int sid)
    {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("SELECT t1.*,t2.assignmentPerc, CASE WHEN t2.assignmentPerc > 90 THEN 'Excellent' WHEN t2.assignmentPerc < 90 AND assignmentPerc > 75 THEN 'Good' WHEN t2.assignmentPerc < 75 AND assignmentPerc > 65 THEN 'Average' WHEN t2.assignmentPerc < 60 THEN 'Below Average' END AssignmentPerformance,CASE WHEN t1.quizPerc > 90 THEN 'Excellent' WHEN t1.quizPerc < 90 AND quizPerc > 75 THEN 'Good' WHEN t1.quizPerc < 75 AND quizPerc > 60 THEN 'Average' WHEN t1.quizPerc < 60 THEN 'Below Average' END QuizPerformance, t2.total_assignmnt,t3.total_courses,student.ranking FROM  (SELECT student.student_id ,COUNT(*) AS total_quizes,(SUM(marks_obtained)/SUM(totak_marks)) * 100 AS quizPerc FROM student INNER JOIN quiz_submission ON quiz_submission.student_id = student.student_id INNER JOIN quiz_result ON quiz_result.quiz_submission_id = quiz_submission.quiz_submission_id GROUP BY student.student_id ) t1 INNER JOIN (SELECT student_registration.student_id,COUNT(*) AS total_assignmnt,(SUM(Marks)/SUM(Total_marks))*100 AS assignmentPerc FROM student_registration INNER JOIN assignment ON student_registration.course_id = assignment.course_id INNER JOIN assignment_result ON assignment_result.assignment_id = assignment.id GROUP BY student_registration.student_id ) t2 ON t1.student_id = t2.student_id LEFT OUTER JOIN  (SELECT student_id,COUNT(*) AS total_courses FROM student_registration GROUP BY student_id) t3 ON t2.student_id = t3.student_id LEFT OUTER JOIN student ON student.`student_id` = t1.student_id where student.student_id=:sid");
     q.setParameter("sid", sid);
     List<Object> temp = q.list();
     con.close();
     return temp;
    }
    
    
    public  String getStudentName(int sid)
    {
     con = HibernateUtil.getSessionFactory().openSession();
     tx = con.beginTransaction();
     q = con.createSQLQuery("select concat(first_name,' ',last_name) from user where id=:sid");
     q.setParameter("sid", sid);
     String temp = q.list().get(0).toString();
     con.close();
     return temp;
    }
    
    
    
    

    
    
    public int saveSection(CourseSection sec){
           //con.close();
        int ID=0;
     try{
         con = HibernateUtil.getSessionFactory().openSession();
         tx = con.beginTransaction();
         ID = (Integer) con.save(sec);
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
       
       
      public int saveTopic(CouseTopic topic){
          //con.close();
        int ID=0;
     try{
         con = HibernateUtil.getSessionFactory().openSession();
         tx = con.beginTransaction();
         ID = (Integer) con.save(topic);
         tx.commit(); 
      }
       catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }
         con.close(); 
     return ID;
   }
      


    
    
    
    
}

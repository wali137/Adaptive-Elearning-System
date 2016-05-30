
package services;

import java.util.List;
import model.Course;
import model.CourseSection;
import model.CouseTopic;
import repositery.Course_Rep;

public class Course_Serv {
    
    Course_Rep cr = new Course_Rep();
    
    public List<Course> getCourseList()
    {
        return cr.getCourseList();
    }
    
    public List<Course> getTeacherCourse(int tid)
    {
        return cr.getTeacherCourse(tid);
    }
    
    public List<Course> getTeacherCourseID(int tid)
    {
        return cr.getTeacherCourseID(tid);
    }
    
    public List<Course> getStudentCourseX(int sid)
    {
        return cr.getStudentCourseX(sid);
    }
    
    public List<Course> getStudentCourseIDX(int sid)
    {
        return cr.getStudentCourseIDX(sid);
    }
    
    
    public List<Course> getCourseListID()
    {
        return cr.getCourseListID();
    }
    
    public String getCourseName(String ID)
    {
        return cr.getCourseName(ID);
    }
    
    public String getCourseDpt(String ID)
    {
        return cr.getCourseDpt(ID);
    }
    
    public String getCourseProgram(String ID)
    {
        return cr.getCourseProgram(ID);
    }
    
    public String getCourseStudents(String ID)
    {
        return cr.getCourseStudents(ID);
    }
    
    public int saveSection(CourseSection sec)
    {
        return cr.saveSection(sec);
    }
    
    public List<CourseSection> getSectionList(String CID)
    {
        return cr.getSectionList(CID);
    }
    
    public List<CourseSection> getSectionListId(String CID)
    {
        return cr.getSectionListID(CID);
    }
    
    public int saveTopic(CouseTopic topic)
    {
        return cr.saveTopic(topic);
    }
    
    public List<CouseTopic> getTopicList(int SID)
    {
        return cr.getTopicList(SID);
    }
    
    public String getPreReqList(int SID)
    {
        return cr.getPreReqList(SID);
    }
    
    public List<CouseTopic> getTopicListID(int SID)
    {
        return cr.getTopicListID(SID);
    }
}

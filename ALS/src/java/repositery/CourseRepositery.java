/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package repositery;


import model.Course;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author dell
 */
public class CourseRepositery {
    InstituteRepositery _repInstituteRepositery;
    
        private List<Course> List;
        DbConnection connection;
        Session CoursesSession;
        Query query;
        private List<Course> list;
        private Session session;
        
        public CourseRepositery()
        {
            connection = new DbConnection();
        }
        
         public boolean Save(Course _modCourses){
        try{
            
        
        session = connection.OpenConnection();
        session.save(_modCourses);
        connection.CloseConnection(session);
        
        return true;
        }catch(Exception ex){
            
          //  connection.rollBack(session);
            
            return false;
            
        }
    }
         //========================================
          public boolean Delete(Course _modCourses){
        try{
            
        
        session = connection.OpenConnection();
        session.delete(_modCourses);
        connection.CloseConnection(session);
        
        return true;
        }catch(Exception ex){
            
            connection.rollBack(session);
            
            return false;
            
        }
    }
          
          public boolean Update(Course _modCourses) {
        try{
           
        session = connection.OpenConnection();
        session.update(_modCourses);
        connection.CloseConnection(session);
        return true;
        }
        catch(Exception ex){
            return false;
        }
    }
          
          
          
          
          
         //========================================
        public List<Course> getCoursesList(){
            
            try{
            CoursesSession = connection.OpenConnection();
            query = CoursesSession.createSQLQuery("select * from Course");
            list = query.list();
            connection.CloseConnection(CoursesSession);
            
            return list;
            }
            catch(Exception ex){
                
                System.out.println(ex.getMessage());
                return null;
            
            }
        }




}


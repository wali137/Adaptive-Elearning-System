/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


//import model.Course;

import model.Program;
import model.Course;
import repositery.ProgramRepositery;
import repositery.CourseRepositery;
import java.util.List;
/**
 *
 * @author dell
 */
public class CourseService {
     ProgramRepositery _repProgramRepositery;
  CourseRepositery _repCourseRepositery;

    public CourseService() {

        _repCourseRepositery = new CourseRepositery();
    _repProgramRepositery=new ProgramRepositery();
    }

    public boolean Save(Course _modCourse) {

        return _repCourseRepositery.Save(_modCourse);

    }

    public boolean Delete(Course _modCourse) {

        return _repCourseRepositery.Delete(_modCourse);



    }

    public boolean Update(Course _modCourse) {
        return _repCourseRepositery.Update(_modCourse);
    }

    public List<Course> getCourseList() {

        return _repCourseRepositery.getCoursesList();
    }

 public List<model.Program> getProgramList() {

        return _repProgramRepositery.getProgramsList();
    }

    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Course;
import model.Program;
import repositery.frequentRepo;
import services.CourseService;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author mushii
 */
public class CourseController {
Course course;
frequentRepo fr = new frequentRepo();

    public Course getCourse() {
        if (course == null) {
            course = new Course();
            course.setProgram(new Program());
        }
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
    Course _modCourse;
    CourseService _serCourseService;

    public Course getModCourse() {
        if (_modCourse == null) {

            _modCourse = new Course();
       _modCourse.setProgram(new Program());
       
        }
        return _modCourse;
    }

    public void setModCourse(Course _modCourse) {
        this._modCourse = _modCourse;
    }

    public CourseService getSerCourseService() {
        if (_serCourseService == null) {
            _serCourseService = new CourseService();
        }
        return _serCourseService;
    }

    public void setSerCourseService(CourseService _serCourseService) {
        this._serCourseService = _serCourseService;
    }

    public void Save() {
        getSerCourseService().Save(getModCourse());
    }

    public List<Course> getCourseesList() {

        return getSerCourseService().getCourseList();
    }

    public void onCreate() {

        if (course != null) {
            _serCourseService.Save(course);
            
        }
        
        course=null;

    }

    public void onUpdate() {
        _serCourseService.Update(_modCourse);
    }

    public void onDelete() {
        _serCourseService.Delete(_modCourse);
    }

    public void myUnedit(SelectEvent event) {
        //selected = null;
    }
    
    
    public String progName(int id){
      Program dp =  (Program) fr.getid2obj(id, Program.class);
      return dp.getProgramName();
    }

    public void myEdit(SelectEvent event) {

        Object o = event.getObject();
        Object[] ob = (Object[]) o;


        _modCourse.setCourseId(ob[5].toString())    ;
        _modCourse.setCourseName(ob[0].toString());
         _modCourse.setCategoryId((Integer) ob[4]);
        _modCourse.setCoRequisiteId(ob[2].toString());
         _modCourse.setCreditHrs((Integer) ob[3]) ;
         _modCourse.setPreRequisiteId(ob[1].toString()); 
        Program dp =  (Program) fr.getid2obj((Integer)ob[6], Program.class);
       _modCourse.setProgram(dp);
               //setdepartmentId((Integer) ob[2])  ;

    }



 public List<SelectItem> getlist(){
        
    List<Program> list =  getSerCourseService().getProgramList();
    List<SelectItem> items = new ArrayList<SelectItem>();
    
    ArrayList<Object[]> objlist = (ArrayList<Object[]>) (Object)list;
    
    for(Object[] o : objlist){
   
        items.add(new SelectItem(o[0],o[1].toString()));
        
    }

        return items;
}



}

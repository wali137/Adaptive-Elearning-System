
package services;

import java.util.List;
import model.Course;
import model.TeacherCourse;
import model.User;
import repositery.admin_rep;

public class admin_service {

    admin_rep ar = new admin_rep();
    
    public List<User> getFollowUp(User u, Course c)
    {
        return ar.getFollowUp(u,c);
    }
    
    public void save(TeacherCourse tc)
    {
        ar.assign_course(tc);
    }
    
}

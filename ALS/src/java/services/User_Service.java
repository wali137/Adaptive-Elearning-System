
package services;

import java.util.List;
import model.User;
import repositery.User_Rep;

public class User_Service {
    
    User_Rep ur = new User_Rep();
    
    public String check_user(User u)
    {
        return ur.check_login(u);
    }
    
    public List<User> getTeachers()
    {
        return ur.getTeachers();
    }
    
    public int Register_Student(User u)
    {
        return ur.Register_Student(u);
    }
    
    public int Register_Admin(User u)
    {
        return ur.Register_Admin(u);
    }
    
    public int Register_Teacher(User u)
    {
        return ur.Register_Teacher(u);
    }
    
    public List<String> getNoties(User u)
    {
       return  ur.getNoties(u);
    }
    
    public String getUserFirstName(int ID)
    {
        return ur.getUserFirstName(ID);
    }
    
    public String logged_username(int ID)
    {
        return ur.logged_username(ID);
    }
    
    public String logged_usertype(int ID)
    {
        return ur.logged_usertype(ID);
    }
    
    public String logged_password(int ID)
    {
        return ur.logged_password(ID);
    }
    
    public String getImageOfUser(int ID)
    {
        return ur.getImageOfUser(ID);
    }
    
    public void update(User u)
    {
        ur.update(u);
    }
    
    public void update_password(int UID, String pass)
    {
        ur.change_password(UID, pass);
    }
    
}

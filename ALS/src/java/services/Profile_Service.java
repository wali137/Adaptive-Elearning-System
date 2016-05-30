
package services;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import model.User;
import repositery.Profile_Rep;

public class Profile_Service {
    
    Profile_Rep pr = new Profile_Rep();
    User user = new User();
    int UserID;
    
    public void update(User u)
    {
        pr.update(u);
    }
    
    public Profile_Service(int ID)
    {
        UserID = ID;
    }
    
    public String getFirstName()
    {
        return pr.getFirstName(UserID);
    }
    
    public String getLastName()
    {
        return pr.getLastName(UserID);
    }
    
    public String getEmail()
    {
        return pr.getEmail(UserID);
    }
    
    public String getPhone()
    {
        return pr.getPhone(UserID);
    }
    
    public String getGender()
    {
        return pr.getGender(UserID);
    }
    
    public String getImage()
    {
        return pr.getImage(UserID);
    }
    
    public String getLanguage()
    {
        return pr.getLanguage(UserID);
    }
    
    public String getHobby()
    {
        return pr.getHobby(UserID);
    }
    
    public Date getDOB() throws ParseException
    {
        return pr.getDOB(UserID);
    }
    
    public String getAddress()
    {
        return pr.getAddress(UserID);
    }
    
}

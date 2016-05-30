
package controller;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import javax.faces.context.FacesContext;
import model.User;
import model.Student;
import model.Teacher;
import services.Profile_Service;

public class Profile_Controller {
    
    User u = new User();

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }
    Student s = new Student();
    Teacher t = new Teacher();
    Profile_Service ps;   
    String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }
    String username;
    String usertype;
    
    int UserID;
    

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int TeacherID) {
        this.UserID = TeacherID;
        ps = new Profile_Service(UserID);
    }

    public void update() throws IOException
    {
        u.setUserName(username);
        u.setPassword(password);
        u.setUserType(usertype);
        u.setId(UserID);
        ps.update(u);
        jump("profile.xhtml?ID=" + getUserID());
    }
    
    
    public void jump(String pageName) throws IOException
    {
        FacesContext.getCurrentInstance().getExternalContext().redirect(pageName); 
    }
    
    public void initModel() throws ParseException
    {
        u.setAddress(ps.getAddress());
        u.setDob(ps.getDOB());
        u.setEmail(ps.getEmail());
        u.setFirstName(ps.getFirstName());
        u.setGender(ps.getGender());
        u.setHobby(ps.getHobby());
        u.setLanguage(ps.getLanguage());
        u.setLastName(ps.getLastName());
        u.setPhoneNumber(ps.getPhone());
        u.setPicture(ps.getImage());
    }
    
    public String getFirstName()
    {
        return ps.getFirstName();
    }
    
    public String getLastName()
    {
        return ps.getLastName();
    }
    
    public String getEmail()
    {
        return ps.getEmail();
    }
    
    public String getPhone()
    {
        return ps.getPhone();
    }
    
    public String getGender()
    {
        return ps.getGender();
    }
    
    public String getImage()
    {
        return ps.getImage();
    }
    
    public String getLanguage()
    {
        return ps.getLanguage();
    }
    
    public String getHobby()
    {
        return ps.getHobby();
    }
    
    public Date getDOB() throws ParseException
    {
        return ps.getDOB();
    }
    
    public String getAddress()
    {
        return ps.getAddress();
    }
    
}


package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import model.Course;
import model.User;
import model.TeacherCourse;
import org.apache.commons.io.FileUtils;
import org.primefaces.context.RequestContext;
import services.User_Service;
import services.admin_service;

public class admin_Controller {
    
    private List<User> users;
    private User u;

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }

    public Course getC() {
        return c;
    }
    
    int cid;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }
    
    TeacherCourse tc = new TeacherCourse();

    public TeacherCourse getTc() {
        if(tc == null)
        {
            tc = new TeacherCourse();
        }
        return tc;
    }

    public void setTc(TeacherCourse tc) {
        this.tc = tc;
    }
    
    
    public void assign_course()
    {
        as.save(tc);
        help.pop_msg("Course Assigned Sucessfully!");
    }
    
    Helper help = new Helper();
    
    public void checkFirstTime()
    {
        if(help.getParam("ftime") != null)
        {
            String test = help.getParam("ftime");
            if(help.getParam("ftime").contains("true"))
            {
                RequestContext rc = RequestContext.getCurrentInstance();
                rc.execute("prof.show()");
            }
        }else{
            RequestContext rc = RequestContext.getCurrentInstance();
            rc.execute("prof.hide()");
        }
    }
    

    public void setC(Course c) {
        this.c = c;
    }
    private admin_service as = new admin_service();
    private Course c;
    
    public List<User> getFollowUp()
    {
        return users;
    }
    
    public void setFollowUp() throws IOException
    {
        jump("admin.xhtml?follow=1");
    }
    
    public void setttings(int x)
    {
        if (x==1)
        {
            users= as.getFollowUp(u,c);
        }
    }
    
    
    private static final Pattern emailFormat = Pattern.compile("^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$");
    User user = new User();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    String repeatPassword;

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
    
    User_Service us = new User_Service();
    
    
    public void registerAdmin() throws IOException
    {
        if(user.getPassword().contains(repeatPassword))
        {
            if (Character.isDigit(user.getFirstName().charAt(0)))
            {
                //error
                FacesMessage msg = new FacesMessage("Firstname must start with a character!");  
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }else{
                if(user.getFirstName().matches(".*\\d.*"))
                {
                    //error
                    FacesMessage msg = new FacesMessage("Firstname must be alphabatic!");  
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                }else{
                    if(user.getLastName().matches(".*\\d.*"))
                    {
                        //error
                        FacesMessage msg = new FacesMessage("Lastname must be alphabatic!");  
                        FacesContext.getCurrentInstance().addMessage(null, msg);
                    }else{
                        // email varification
                        if (emailFormat.matcher(user.getEmail()).matches()) {
                            // creating temporary profile picture
                            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                            File source = new File(ec.getRealPath("/data/newuser.gif"));
                            File desc = new File(ec.getRealPath("/data/user/" + user.getEmail() + ".jpg"));
                            try {
                                FileUtils.copyFile(source, desc);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            //sucess
                            int uid = us.Register_Admin(user);
                            Email_Controller.email("Your username is, " + user.getUserName() + " and your password is, " + user.getPassword(), user.getEmail());
                            user.setUserType("admin");
                            us.check_user(user);
                            jump("admin.xhtml?ID="+ uid);
                        }else{
                            //error
                            FacesMessage msg = new FacesMessage("Not a correct Email Format!");  
                            FacesContext.getCurrentInstance().addMessage(null, msg);
                        }
                    }
                }
            }
        }else{
            //error
            FacesMessage msg = new FacesMessage("Password(s) provided doesn't match");  
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    
    
    public void registerTeacher() throws IOException, InterruptedException
    {
        if(user.getPassword().contains(repeatPassword))
        {
            if (Character.isDigit(user.getFirstName().charAt(0)))
            {
                //error
                FacesMessage msg = new FacesMessage("Firstname must start with a character!");  
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }else{
                if(user.getFirstName().matches(".*\\d.*"))
                {
                    //error
                    FacesMessage msg = new FacesMessage("Firstname must be alphabatic!");  
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                }else{
                    if(user.getLastName().matches(".*\\d.*"))
                    {
                        //error
                        FacesMessage msg = new FacesMessage("Lastname must be alphabatic!");  
                        FacesContext.getCurrentInstance().addMessage(null, msg);
                    }else{
                        // email varification
                        if (emailFormat.matcher(user.getEmail()).matches()) {
                            // creating temporary profile picture
                            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                            File source = new File(ec.getRealPath("/data/newuser.gif"));
                            File desc = new File(ec.getRealPath("/data/user/" + user.getEmail() + ".jpg"));
                            try {
                                FileUtils.copyFile(source, desc);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            //sucess
                            int uid = us.Register_Teacher(user);
                            Email_Controller.email("Your username is, " + user.getUserName() + " and your password is, " + user.getPassword(), user.getEmail());
                            user.setUserType("teacher");
                            help.pop_msg("New Teacher Created Sucessfully!");
                            Thread.sleep(3000);
                            help.navigate("admin.xhtml");
                        }else{
                            //error
                            FacesMessage msg = new FacesMessage("Not a correct Email Format!");  
                            FacesContext.getCurrentInstance().addMessage(null, msg);
                        }
                    }
                }
            }
        }else{
            //error
            FacesMessage msg = new FacesMessage("Password(s) provided doesn't match");  
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    
    
    
    private void jump(String pageName) throws IOException
    {
        FacesContext.getCurrentInstance().getExternalContext().redirect(pageName); 
    }
    
    
    
}

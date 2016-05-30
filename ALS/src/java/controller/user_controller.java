
package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import model.User;
import org.apache.commons.io.FileUtils;
import org.primefaces.event.FileUploadEvent;
import repositery.User_Rep;
import services.User_Service;

public class user_controller {
    
    
    private Date date1;  
    String logged_username;
    int logged_id;
    String imageOfUser;
    Helper help = new Helper();
    String old_password;
    
    public void change_password()
    {
        logged_id = help.toInt(getCurrentUserId());
        String pass = getLogged_password();
        if(getOld_password().contains(pass))
        {
            if(getNew_password().contains(getRep_password()))
            {
                us.update_password(help.toInt(getCurrentUserId()), getNew_password());
                help.pop_msg("Password Updated Sucessfully!");
            }else{
                help.pop_msg("New and Repeated Passwords Doesn't Match!");
            }
        }else{
            help.pop_msg("Wrong Old Password!");
        }
    }

    public String getOld_password() {
        return old_password;
    }
    
    List<String> adminNoties;

    User_Rep ur = new User_Rep();
    
    public List<String> getAdminNoties() {
        return ur.getAdminNoties();
    }

    public void setAdminNoties(List<String> adminNoties) {
        this.adminNoties = adminNoties;
    }
    

    public void setOld_password(String old_password) {
        this.old_password = old_password;
    }

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    public String getRep_password() {
        return rep_password;
    }

    public void setRep_password(String rep_password) {
        this.rep_password = rep_password;
    }
    String new_password;
    String rep_password;
    
    
    
    public void update() throws IOException
    {
        us.update(user);
        check_user();
    }

    public String getImageOfUser() {
        return imageOfUser;
    }

    public void setImageOfUser(int ID) {
        this.imageOfUser = us.getImageOfUser(ID);
    }
    

    public int getLogged_id() {
        return logged_id;
    }

    public void setLogged_id(int logged_id) {
        this.logged_id = logged_id;
    }
    

    public String getLogged_username() {
        return us.logged_username(logged_id);
    }

    public void setLogged_username(String logged_username) {
        this.logged_username = logged_username;
    }

    public String getLogged_usertype() {
        return us.logged_usertype(logged_id);
    }

    public void setLogged_usertype(String logged_usertype) {
        this.logged_usertype = logged_usertype;
    }

    public String getLogged_password() {
        return us.logged_password(logged_id);
    }

    public void setLogged_password(String logged_password) {
        this.logged_password = logged_password;
    }
    String logged_usertype;
    String logged_password;
      
    public Date getDate1() {  
        return date1;  
    }  
  
    public void setDate1(Date date1) {  
        this.date1 = date1;  
    }  
    
    
    User user = new User();
    User_Service us = new User_Service();
    private String repeatPassword;

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    String FirstName;
    String user1;

    public String getUser1() {
        return user1;
    }

    public void setUser1(int ID) {
        this.user1  = us.getUserFirstName(ID);
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(int ID) {
        this.user2  = us.getUserFirstName(ID);
    }

    public String getUser3() {
        return user3;
    }

    public void setUser3(int ID) {
        this.user3  = us.getUserFirstName(ID);
    }
    String user2;
    String user3;
    

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(int ID) {
        this.FirstName = us.getUserFirstName(ID);
    }
    
    
    public String getUserFirstName(int ID)
    {
        return us.getUserFirstName(ID);
    }
    
    
    private static final Pattern emailFormat = Pattern.compile("^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$");
     
     
     
    public void registerStudent() throws IOException
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
                            int uid = us.Register_Student(user);
                            us.check_user(user);
                            jump("Register_Step_2.xhtml?ID="+ uid);
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
    
    public List<User> getTeacher()
    {
        return us.getTeachers();
    }
    
    private String imgName = null;

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }
    
    int redirect = 0;

    public int getRedirect() {
        return redirect;
    }

    public void setRedirect(int redirect) {
        this.redirect = redirect;
    }
    
    
    
    public void uploadFile(FileUploadEvent event) throws IOException {  
        FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded."); 
        FacesContext.getCurrentInstance().addMessage(null, msg);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String rootDir = ec.getRealPath("/data/user/" + getImgName() + ".jpg");
        rootDir = getItRight(rootDir);
        try {
            copyFile(event.getFile().getInputstream(),rootDir);
        } catch (IOException e) {
        e.printStackTrace();
        }
        if(redirect > 0)
        {
            jump("Register_Step_2.xhtml?ID=" + redirect);
        }
    } 
    
    public String getItRight(String str)
    {
        return str.replace("\\", "/");
    }
    
    public void copyFile( InputStream in,String rootDir) {
        try {
            OutputStream out = new FileOutputStream(new File(rootDir));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = in.read(bytes)) != -1) {
            out.write(bytes, 0, read);
            }
        in.close();
        out.flush();
        out.close();
        System.out.println("New file created!");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
    }
    
    
    public void check_user() throws IOException
    {
        String check = us.check_user(user);
        if (check.contains("none"))
        {
             // send message to growl
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid login! Try again...", "Wrong username/password combination");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        else
        {
           // nevigate to respected page
            if (check.contains("teacher"))
            {  
                FacesContext.getCurrentInstance().getExternalContext().redirect("teacher.xhtml"); 
            }
            if (check.contains("student"))
            {
                FacesContext.getCurrentInstance().getExternalContext().redirect("student.xhtml"); 
            }
            if(check.contains("admin"))
            {
                FacesContext.getCurrentInstance().getExternalContext().redirect("admin.xhtml"); 
            }
        }
    }
    
    
    public void check_session(String check_for) throws IOException
    {
        // check for which user type? teacher,student,admin...
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        String uType = (String) session.getAttribute("type");
        if(uType !=null){
        if (uType.contains(check_for))
        {
            // let user stay on page    
        }else{
            jump("Login.xhtml");
        }
        }else{
            jump("Login.xhtml");
        }
    }
    
    public List<String> getNoties()
    {
        
        user.setId(Integer.parseInt(getCurrentUserId()));
        user.setUserType(getCurrentUserType());
        return us.getNoties(user);
    }
    
    public String getCurrentUserName()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        String name = (String) session.getAttribute("name");
        return name;
    }
    
    public String getCurrentUserId()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        String name = (String) session.getAttribute("id");
        return name;
    }
    
    public String getCurrentUserLastName()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        String name = (String) session.getAttribute("lname");
        return name;
    }
    
    public String getCurrentUserEmail()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        String name = (String) session.getAttribute("email");
        return name;
    }
    
    public String getCurrentUserPPIC()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        String name = (String) session.getAttribute("pic");
        return name;
    }
    
    
    public String getCurrentUserType()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        String name = (String) session.getAttribute("type");
        return name;
    }
    
   
    public void jump(String pageName) throws IOException
    {
        FacesContext.getCurrentInstance().getExternalContext().redirect(pageName); 
    }
    
    public void logout() throws IOException
    {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        session.removeAttribute("id");
        session.removeAttribute("name");
        session.removeAttribute("type");
        session.removeAttribute("pic");
        jump("Login.xhtml");
    }
    
}

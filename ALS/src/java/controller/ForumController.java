/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Forum;
import model.ForumPost;
import model.User;
import services.ForumService;

/**
 *
 * @author DemiXsoft
 */
public class ForumController {
    
    public ForumController() {
       fs =new ForumService();
       //setTodayDate();
       //setZero();
    }
    // Getter / setter + Global Variables
    ForumService fs;
    Forum f;
    Forum cf;
    ForumPost FP;
    String title = null;
    String CourseID = null;
    String todayDate = null;
    static int zero;
    String user = null;
    String topic = null;
    String mostTopicsCourse = null;
    String mostCommentedTopic = null;
    String mostViewedTopic = null;
    String todayComments = null;
    String mostTopicsOfCourse = null;
    List<Forum> forumOfCourse = null;
    String members = null;
    String paramID = null;
    Forum MyForum = new Forum();
    List<ForumPost> latestComments;
    List<ForumPost> topComments;
    List<User> allMembers;
   

    public List<User> getAllMembers() {
        return allMembers;
    }

    public void setAllMembers() {
        this.allMembers = fs.getMembers(getCurrentUserID());
    }
    

    public List<ForumPost> getTopComments() {
        return topComments;
    }

    public void setTopComments() {
        this.topComments = fs.getTopCommentors();
    }

    public List<ForumPost> getLatestComments() {
        return latestComments;
    }

    public void setLatestComments() {
        this.latestComments = fs.getLatestComments();
    }

    public String getParamID() {
        return paramID;
    }

    public void setParamID(String paramID) {
        this.paramID = paramID;
    }
    
    public void setForumModel(int ID)
    {
        f.setForumId(ID);
    }
    public void getForumModel()
    {
        this.MyForum = fs.getForumModel(f);
    }
    

    public String getMembers() {
        return members;
    }

    public void setMembers() {
        this.members = fs.getMembers(paramID);
    }

    public List<Forum> getForumOfCourse() {
        return forumOfCourse;
    }

    public void setForumOfCourse(String ID) {
        this.forumOfCourse = fs.getForumOfCourse(ID);
    }

    public String getMostTopicsOfCourse() {
        return mostTopicsOfCourse;
    }

    public void setMostTopicsOfCourse(String ID) {
        this.mostTopicsOfCourse = fs.getTopicsOfCourse(ID);
    }

    public String getTodayComments() {
        return todayComments;
    }

    public void setTodayComments() {
        this.todayComments = fs.getTodayComments(paramID);
    }
    

    public String getMostViewedTopic() {
        return mostViewedTopic;
    }

    public void setMostViewedTopic() {
        this.mostViewedTopic = fs.getMostViewedTopic();
    }
    

    public String getMostCommentedTopic() {
        return mostCommentedTopic;
    }

    public void setMostCommentedTopic() {
        this.mostCommentedTopic = fs.getMostCommentedTopic();
    }
    

    public String getMostTopicsCourse() {
        return mostTopicsCourse;
    }

    public void setMostTopicsCourse() {
        this.mostTopicsCourse = fs.getMostTopicsCourse();
    }
    
    
    
    public ForumPost getForumPost()
    {
        if (FP != null)
        {
            return FP;
        }else{
            FP = new ForumPost();
            return FP;
        }
    }
    
    public void setForumPost(ForumPost fp)
    {
        FP = fp;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(int ID) {
        this.topic =fs.getTopic(ID);
    }
    

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    

    public int getZero() {
        return zero;
    }

    public void setZero(int z) {
        this.zero = z;
    }

    public String getTodayDate() {
        return todayDate;
    }

    public void setTodayDate() {
        DateFormat df = new SimpleDateFormat("yyyy/mm/dd");
        Date d = new Date();
        this.todayDate = df.format(d);
    }

    public void setValues(int ID)
    {
        setZero(ID);
        setTopic(ID);
        setTitle(ID);
        f = new Forum();
        String id = fs.getCourseID(ID);
        f.setCourseId(id);
        f.setForumName(fs.getTitle(ID));
    }
    
    public void setValues2()
    {
        setMostTopicsCourse();  
        setMostCommentedTopic();
        setMostViewedTopic();
        setTodayComments();
    }
    
    public List<ForumPost> getForumPosts()
    {
        int id = getZero();
        return fs.getForumPosts(id);
    }
    
    public String[] newLineString(String txt)
    {
            String lines[] = txt.split("\n");
            return lines;
    }
    
    public int newLineStringCount(String txt)
    {
            String lines[] = txt.split("\n");
            return lines.length;
    }
    
    public void setComments(int Comments)
    {
        fs.resetComments(zero, Comments);
    }
    
    
    public String getCourseID() {
        return CourseID;
    }

    public void setCourseID(int ID) {
        this.CourseID = fs.getCourseID(ID);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(int ID) {
        this.title = fs.getTitle(ID);
    }
    
    public String getCourseName()
    {
        String cid = fs.getCourseID(zero);
        return fs.getCourseName(cid);
    }
    
    public String getCreatedBy()
    {
        String uid = fs.getUserID(zero);
        return fs.getUserName(uid);
    }
    
    private Forum getCurrentForum()
    {
        return fs.getCurrentForum(zero);
    }
    
    public void createPost()
    {
        //FP = new ForumPost();
        FP.setUserId(getCurrentUserID());
        FP.setForumId(zero);
        int pid = fs.createPost(FP);
        setComments(getForumPosts().size());
    }

    public Forum getForum() {
        if(f==null)
        {
            f = new Forum();
        }
        return f;  
    }

    public void setForum(Forum f) {
        this.f = f;
    }
        
    public void createForum(){
        f.setCreatedBy(getCurrentUserID());
        fs.save(f);
    }
    
 
    
    public int getCurrentUserID()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        String name = (String) session.getAttribute("id");
        return Integer.parseInt(name);
    }
    
    public void updateForum(int ID){
        f.setForumId(getZero());
        fs.update(f);
    }
    
    public List<Forum> getForumList()
    {
        return fs.getForum();
    }
    

    
    public void deleteForum(int id)
    {
//        FacesContext fc = FacesContext.getCurrentInstance();
//	String strID = getParam(fc,"ID");
//        int ID = Integer.parseInt(strID);
        f= new Forum();
        f.setForumId(id);
        fs.delete(f);
    }
    
    public void deleteForumNavigate(int id,String page) throws IOException
    {
//        FacesContext fc = FacesContext.getCurrentInstance();
//	String strID = getParam(fc,"ID");
//        int ID = Integer.parseInt(strID);
        f= new Forum();
        f.setForumId(id);
        fs.delete(f);
        user_controller uc = new user_controller();
        uc.jump("forum_course.xhtml?ID=" + page);
    }
    
    private String amp = "&";

    public String getAmp() {
        return amp;
    }

    public void setAmp() {
        this.amp = "&";
    }
    
    
    
    // getting parameter function via Views
    public String getParam(FacesContext fc,String ParamName){
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get(ParamName);
    }
    // refreshing page via BEAN!!
    public void refreshPage()
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        String page = fc.getViewRoot().getViewId();
        
        ViewHandler vh = fc.getApplication().getViewHandler();
        UIViewRoot vr = vh.createView(fc, page);
        vr.setViewId(page);
    }
    
    
}

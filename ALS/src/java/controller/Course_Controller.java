
package controller;

import agents.Agent;
import agents.AgentManager;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import learners.main.alsBayse;
import model.Course;
import model.CourseSection;
import model.CouseTopic;
import model.User;
import org.primefaces.event.FileUploadEvent;
import repositery.Course_Rep;
import services.Course_Serv;

public class Course_Controller {

   
    
    CourseSection sec;
    CouseTopic topic;
    public static String finalprediction;

    public String getFinalprediction() {
        return finalprediction;
    }

    public void setFinalprediction(String finalprediction) {
        this.finalprediction = finalprediction;
    }

    String topicName;

    public String topicName(int tid) {
        return cr.getTopicName(tid);
    }

    
 

    public CouseTopic getTopic() {
        if(topic==null)
        {
            topic = new CouseTopic();
        }
        return topic;
    }
    Course_Rep cr = new Course_Rep();
    
    List<String> studentList;

    public List<String> getStudentList() {
        return cr.getStudentList(courseID);
    }

    public void setStudentList(List<String> studentList) {
        this.studentList = studentList;
    }
    
    List<User> userNameIDs;

    public List<User> getUserNameIDs() {
        return cr.getStudentListwithID(courseID);
    }
    
    alsBayse bays = new alsBayse();
    
    String prediction;
    int predict_id;
    String predict_student_name;

    user_controller uc = new user_controller();
    
    public String getPredict_student_name() {
        uc.setUser1(predict_id);
        return uc.user1;
    }

    public void setPredict_student_name(String predict_student_name) {
        this.predict_student_name = predict_student_name;
    }
    

    public int getPredict_id() {
        return predict_id;
    }
    
    String predict_quiz_percentage;

    public String getPredict_quiz_percentage() {
        return predict_quiz_percentage;
    }

    public void setPredict_quiz_percentage(String predict_quiz_percentage) {
        this.predict_quiz_percentage = predict_quiz_percentage;
    }

    public String getPredict_assignment_percentage() {
        return predict_assignment_percentage;
    }

    public void setPredict_assignment_percentage(String predict_assignment_percentage) {
        this.predict_assignment_percentage = predict_assignment_percentage;
    }
    String predict_assignment_percentage;
    

    public void setPredict_id(int predict_id) {
        predict_quiz_percentage = cr.getQuizPercentage(predict_id, courseID);
        predict_assignment_percentage = cr.getAssignmentPercentage(predict_id, courseID); 
        prediction = bays.test(bays.classifer, predict_id);
        this.predict_id = predict_id;
    }
    
//     public void setPredict_id2(int predict_id) {
//        predict_quiz_percentage = cr.getQuizPercentage(predict_id, "CS105");
//        predict_assignment_percentage = cr.getAssignmentPercentage(predict_id, "CS105"); 
//        prediction = bays.test(bays.classifer, predict_id);
//        this.predict_id = predict_id;
//    }

    
    int bayes_sid;

    public int getBayes_sid() {
        return bayes_sid;
    }

    public void setBayes_sid(int bayes_sid) {
        this.bayes_sid = bayes_sid;
    }
    
    List<Object> adminBayes;
    List<Object> studentBayes;

    public List<Object> getStudentBayes() {
        return cr.getStudentBayse(bayes_sid);
    }

    public void setStudentBayes(List<Object> studentBayes) {
        this.studentBayes = studentBayes;
    }
    

    public List<Object> getAdminBayes() {
        return cr.getAdminBayse();
    }
    
    public String fetchStudentName(int sid)
    {
        return cr.getStudentName(sid);
    }

    public void setAdminBayes(List<Object> adminBayes) {
        this.adminBayes = adminBayes;
    }
    

    public String getPrediction() {
        return prediction;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }
    

    public void setUserNameIDs(List<User> userNameIDs) {
        this.userNameIDs = userNameIDs;
    }
    

    public void setTopic(CouseTopic topic) {
        this.topic = topic;
    }
    

    public CourseSection getSec() {
        if(sec==null)
        {
            sec = new CourseSection();
        }
        return sec;
    }
    
    public List<CourseSection> getSectionList()
    {
        return cs.getSectionList(getCourseID());
    }
    
    public List<CourseSection> getSectionListID()
    {
        return cs.getSectionListId(getCourseID());
    }
    
    private static String mySec = "0";
    private static String myTopic = "0";

    public String getMyTopic() {
        return myTopic;
    }

    public void setMyTopic(String myTopic) {
        this.myTopic = myTopic;
    }


    public String getMySec() {
        return mySec;
    }

    public void setMySec(String mySec) {
        this.mySec = mySec;
    }

   

    private List<CouseTopic> topicsList;

    public List<CouseTopic> getTopicsList() {
        return topicsList;
    }

    public void setTopicsList() {
        this.topicsList = cs.getTopicList(Integer.parseInt(mySec));
    }

    public List<CouseTopic> getTopicsID() {
        return topicsID;
    }

    public void setTopicsID() {
        this.topicsID = cs.getTopicListID(Integer.parseInt(mySec));
    }
    private List<CouseTopic> topicsID;
    
    public void setTopicsAll()
    {
        setTopicsID();
        setTopicsList();
    }
    
    
   
    

    public void setSec(CourseSection sec) {
        this.sec = sec;
    }
    
    Course_Serv cs = new Course_Serv();
    int SectionID;
    private static String courseID = null;

    public static String getCourseID() {
        return courseID;
    }

    public static void setCourseID(String courseID) {
        Course_Controller.courseID = courseID;
    }

    public int getSectionID() {
        return SectionID;
    }

    public void setSectionID(int SectionID) {
        this.SectionID = SectionID;
    }
    
    public List<Course> getCourseList()
    {
        return cs.getCourseList();
    }
    
    public List<Course> getTeacherCourses()
    {
        int tid = Integer.parseInt(getCurrentUserId());
        return cs.getTeacherCourse(tid);
    }
    
    public void saveSection() throws IOException
    {
        sec.setCourseId(getCourseID());
        cs.saveSection(sec);
        jump("teacher_course_create.xhtml?cid="+ getCourseID());
    }
    
    public String PreReqs;

    public String getPreReqs() {
        return PreReqs;
    }

    public void setPreReqs() {
        this.PreReqs = cs.getPreReqList(toInt(mySec));
    }
    
    
    private int toInt(String Number)
    {
        return Integer.parseInt(Number);
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
        
    public String getItRight(String str)
    {
        return str.replace("\\", "/");
    }
    
    private static String uFileName;

    public static String getuFileName() {
        return uFileName;
    }

    public static void setuFileName(String uFileName) {
        Course_Controller.uFileName = uFileName;
    }
    
    
    private String getFileName()
    {
        int rand1 = (int) (Math.random()*357);
        int rand2 = (int) (Math.random()*137); 
        double rand3 =  (Math.random() * (rand1 - rand2));
        Date d = new Date();
        rand3 = rand3 + Math.random()*d.getTime(); 
        return mySec+ "_" + rand3;
    }
    
    public void uploadFile(FileUploadEvent event) {  
        FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded."); 
        FacesContext.getCurrentInstance().addMessage(null, msg);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String fname = event.getFile().getFileName();
        int fnLen = fname.length();
        String ext = fname.substring(fnLen-3, fnLen);
        String rootDir = ec.getRealPath("/data/" + getFileName() + "." + ext);
        setuFileName(getFileName() + "." + ext);
        rootDir = getItRight(rootDir);
        try {
            copyFile(event.getFile().getInputstream(),rootDir);
        } catch (IOException e) {
        e.printStackTrace();
        }
    } 
    
    public void saveTopic() throws IOException
    {
        topic.setSectionId(toInt(mySec));
        topic.setContent("data\\" + uFileName);
        cs.saveTopic(topic);
        jump("teacher_course_create.xhtml?cid="+ getCourseID());
    }
    
    public List<Course> getStudentCourses()
    {
        int sid = Integer.parseInt(getCurrentUserId());
        return cs.getStudentCourseX(sid);
    }
    
    public List<Course> getStudentCoursesID()
    {
        int sid = Integer.parseInt(getCurrentUserId());
        return cs.getStudentCourseIDX(sid);
    }
    
    
    public List<Course> getTeacherCoursesID()
    {
        int tid = Integer.parseInt(getCurrentUserId());
        return cs.getTeacherCourseID(tid);
    }
    
    public String getCurrentUserId()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        String name = (String) session.getAttribute("id");
        return name;
    }
    
    public List<Course> getCourseListID()
    {
        return cs.getCourseListID();
    }
    
    public String getCourseName()
    {
        String ID = getCourseID();
        return cs.getCourseName(ID);
    }
    
    public String getCourseDpt()
    {
        String ID = getCourseID();
        return cs.getCourseDpt(ID);
    }
    
    public String getCourseProgram()
    {
        String ID = getCourseID();
        return cs.getCourseProgram(ID);
    }
    
    public String getCourseStudents()
    {
        String ID = getCourseID();
        return cs.getCourseStudents(ID);
    }
    
    
    
    public String addSectionView(int CID)
    {
        return "dialog:teacher_course_section?cid="+CID;
    }
    
    public void addSection(int CouseID)
    {
        
    }
    
    public String addTopicView(int CID)
    {
        return "dialog:teacher_course_topic?cid="+CID;
    }
    
    public void addTopic(int CourseID)
    {
        int sid = getSectionID();
    }
    
    public void createCourse()
    {
        
    }
    
    public void navigate_CourseCreate() throws IOException
    {
        jump("teacher_course_create.xhtml?cid="+getCourseID());
    }
    
    public void navigate_AssignmentCreate() throws IOException
    {
        jump("teacher_course_assignment_create.xhtml?cid="+getCourseID());
    }   
    
    public void navigate_AssignmentView() throws IOException
    {
        jump("teacher_course_assignment_view.xhtml?cid=" + getCourseID());
    }
    
    public void navigate_LessonCreate() throws IOException
    {
        jump("teacher_course_lesson_create.xhtml?cid="+getCourseID());
    }   
    
    public void navigate_LessonView() throws IOException
    {
        jump("teacher_course_lesson_view.xhtml?cid=" + getCourseID());
    }
    
    public void navigate_QuizView() throws IOException
    {
        jump("teacher_course_quiz_view.xhtml?cid=" + getCourseID());
    }
    
    public void student_QuizView() throws IOException
    {
        jump("student_course_quiz_view.xhtml?cid=" + getCourseID());
    }
    
    public void student_AssignmentView() throws IOException
    {
        
        jump("student_course_assignment_view.xhtml?cid=" + getCourseID());
    }
    
    
    public void navigate_QuizCreate() throws IOException
    {
        jump("teacher_course_quiz_create.xhtml?cid="+getCourseID());
    }
    
    

    
    private void jump(String pageName) throws IOException
    {
        FacesContext.getCurrentInstance().getExternalContext().redirect(pageName); 
    }
    
    
    public void agentPredict()
    {
           AgentManager am = new AgentManager();
        am.StartAgentManager();  
        am.InitAgent(Agent.STUDENTTEACHER.name());

    }
    
}

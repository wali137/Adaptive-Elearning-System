/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Assignment;
import model.AssignmentSubmission;
import model.AssignmentResult;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import services.AssignmentServices;

public class AssignmentController {
    
    // Local Variables
    static int ID;
    int myID;
    

    public int getMyID() {
        return myID;
    }

    public void setMyID(int myID) {
        this.myID = myID;
    }
    
    public AssignmentResult result= new AssignmentResult();

    public AssignmentResult getResult() {
        return result;
    }

    public void setResult(AssignmentResult result) {
        this.result = result;
    }
    
    AssignmentServices aserv = new AssignmentServices();
    public Assignment a = new Assignment();
    public AssignmentSubmission sub = new AssignmentSubmission();
    private Helper help = new Helper();

    public AssignmentSubmission getSub() {
        return sub;
    }

    public void setSub(AssignmentSubmission sub) {
        this.sub = sub;
    }
    private static String course_id;

    public static String getCourse_id() {
        return course_id;
    }

    public static void setCourse_id(String course_id) {
        AssignmentController.course_id = course_id;
    }
    
    public void submitResult(int sid) throws InterruptedException, IOException
    {
        if(help.toInt(getMarks()) < result.getMarks())
        {
            help.pop_msg("Maximum Marks are " + getMarks() + ", You must Mark accordingly!");
        }else{
            result.setMarkedDate(new Date());
            result.setAssignmentId(getID());
            result.setStudentId(sid);
            aserv.submitResult(result);
            help.pop_msg("Result has been submitted sucessfully!");
            Thread.sleep(2000);
            jump("teacher_course_Assignment_view_check.xhtml?ID=" + getID());
        }        
    }
    
    List<AssignmentResult> results;

    public List<AssignmentResult> getResults() {
        return  aserv.results(getID());
    }

    public void setResults(List<AssignmentResult> results) {
        this.results = results;
    }
    
 
    public Assignment getA() {
        return a;
    }
    
    String CreatedBy;

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(int ID) {
        this.CreatedBy = aserv.getCreatedBy(ID);
    }
    

    public void setA(Assignment a) {
        this.a = a;
    }
    
    public static int getID() {
        return ID;
    }

    public static void setID(int ID) {
        AssignmentController.ID = ID;
    }
    
    
    public List<Assignment> getList()
    {
        return aserv.getList(getCourse_id());
    }
    
    String cid;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
    
    
    
    private String AssignmentTitle;

    public String getAssignmentTitle() {
        setAssignmentTitle(getID());
        return AssignmentTitle;
    }

    public void setAssignmentTitle(int ID) {
        this.AssignmentTitle = aserv.getTitle(ID);
    }
    
    List<AssignmentSubmission> submissions;

    public List<Assignment> getSubmissions() {
        return aserv.submissions(help.toInt(help.getCurrentUserID()),getCourse_id());
    }

    public void setSubmissions(List<AssignmentSubmission> submissions) {
        this.submissions = submissions;
    }
    
    
    public String getAssignmentStatement(int ID)
    {
        return aserv.getAssignStatement(ID);
    }
    
    public String getStatement()
    {
        int id = getID();
        return aserv.getAssignStatement(id);
    }
    
    public void cancel() throws IOException
    {
        jump("teacher_course.xhtml?cid=" + getCourse_id());
    }
    
    public void cancel_del() throws IOException
    {
        jump("teacher_course_assignment_view.xhtml?cid=" + getCourse_id());
    }
    
    
    public void createAssignment() throws IOException
    {
        //a.setAssignmentId(1);
        a.setCourseId(getCourse_id());
        a.setCreatedBy(Integer.parseInt(getCurrentUserId()));
        a.setSlide("data\\" + uFileName);
        aserv.CreateAssignment(a);
        jump("teacher_course.xhtml?cid=" + getCourse_id());
    }
    
    public String getCurrentUserId()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        String name = (String) session.getAttribute("id");
        return name;
    }
    
    public void updateAssignment() throws IOException, ParseException
    {
        int id = getID();
        a.setId(id);
        a.setAssignmentName(AssignmentTitle);
        a.setDueDate(toDateFormat(getDueDate()));
        a.setSlide("data\\" + uFileName);
        a.setStatement(getStatement());
        a.setCourseId(course_id);
        a.setCreatedBy(Integer.parseInt(getCurrentUserId()));
        a.setTotalMarks(Integer.parseInt(getMarks()));
        aserv.UpdateAssignment(a);
        cancel_del();
    }
    
    public void back() throws IOException
    {
        help.navigate("student_course_assignment_view.xhtml?cid=" + getCourse_id());
    }
    
    public void back_to_menu() throws IOException
    {
        help.navigate("student_course.xhtml?cid=" + getCourse_id());
    }
    
    public void submitAssignment() throws ParseException, IOException
    {
        int id = getID();
        sub.setAssignmentId(id);
        sub.setAttachment("data\\" + uFileName);
        sub.setSubmissionDate(new Date());
        sub.setSubmissionTime(help.toTimeFormat(help.getCurrentTime()));
        sub.setSubmittedBy(help.toInt(help.getCurrentUserID()));
        aserv.submitAssignment(sub);
        help.navigate("student_course_assignment_view_submit_complete.xhtml?ID=" + getID());
    }
    

    int isSubmitted;
    

    public int getIsSubmitted() {
        
        return aserv.isSubmitted(help.toInt(help.getCurrentUserID()), getMyID());
    }

    public void setIsSubmitted(int isSubmitted) {
        this.isSubmitted = isSubmitted;
    }
    
    
    public void deleteAssignment() throws IOException
    {
        int id= getID();
        a = new Assignment();
        a.setId(id);
        aserv.DeleteAssignment(a);
        cancel_del();
    }
    
    String attachment;

    public String getAttachment() {
        int ID = getID();
        return aserv.getAttachment(ID);
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
    
    public String getDueDate() 
    {
        int id = getID();
        String string = aserv.getDueDate(id);
        return string;
    }
    
    Date dueDate2;

    public Date getDueDate2() throws ParseException {
        return toDateFormat(getDueDate());
    }

    public void setDueDate2(Date dueDate2) {
        this.dueDate2 = dueDate2;
    }
    
    
    public Date toDateFormat(String d) throws ParseException
    {
        Date date = new SimpleDateFormat("yyyy-M-d", Locale.ENGLISH).parse(d);
        return date;
    }
    
    public String getMarks()
    {
        int id = getID();
        return aserv.getMarks(id);
    }
    
    
    private void jump(String pageName) throws IOException
    {
        FacesContext.getCurrentInstance().getExternalContext().redirect(pageName); 
    }
    
    // File uploading logic
    
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
        AssignmentController.uFileName = uFileName;
    }
    
    
    private String getFileName()
    {
        int rand1 = (int) (Math.random()*357);
        return "assignment_" + rand1;
    }
    
    public void uploadFile(FileUploadEvent event) {  
        FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded."); 
        FacesContext.getCurrentInstance().addMessage(null, msg);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String fname = event.getFile().getFileName();
        int fnLen = fname.length();
        String ext = fname.substring(fnLen-3, fnLen);
        setuFileName(getFileName() + "." + ext);
        String rootDir = ec.getRealPath("/data/" + getuFileName());
        rootDir = getItRight(rootDir);
        try {
            copyFile(event.getFile().getInputstream(),rootDir);
        } catch (IOException e) {
        e.printStackTrace();
        }
    } 
    
    
}

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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import model.Forum;
import model.Lesson;
import org.primefaces.event.FileUploadEvent;
import repositery.Course_Rep;
import services.ForumService;
import services.Lesson_Service;

/**
 *
 * @author DemiXsoft
 */
public class Lesson_Controller {

    Lesson_Service ls = new Lesson_Service();
    Lesson lesson = new Lesson();
    Helper help = new Helper();
    private static String course_id;

    public static String getCourse_id() {
        return course_id;
    }

    public static void setCourse_id(String course_id) {
        Lesson_Controller.course_id = course_id;
    }
    
    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public void save() throws IOException
    {
        lesson.setDateCreated(new Date());
        lesson.setCreatedBy(help.toInt(help.getCurrentUserID()));
        lesson.setCourseId(getCourse_id());
        lesson.setAttachment(uFileName);
        int id = ls.save(lesson);
        help.navigate("teacher_course.xhtml?cid=" + getCourse_id());
    }
    
    public void navigate(int id) throws IOException
    {
        algo_logic(id);
        if(algo)
        {
            help.navigate("student_course_lesson_attend.xhtml?ID=" + id);
        }else{
            help.pop_msg("You cannot proceed to this lesson, take pre-requisite first!");
        }
    }
    
    public boolean algo;
    
    public void algo_logic(int id)
    {
        //algo=true;
        
        Course_Rep cr = new Course_Rep();
        String x = cr.getQuizAtt(id, getCourse_id());
        if(x.contains("False"))
        {
            algo=false;
        }else{
            algo=true;
        }
    }
    
    public void update() throws IOException
    {
        ls.update(lesson);
        help.pop_msg("Updated Sucessfully!");
        help.navigate("teacher_course_lesson_view.xhtml?cid=" + getCourse_id());
    }
    
    public void delete() throws IOException
    {
        ls.delete(lesson);
        help.pop_msg("Deleted Sucessfully!");
        help.navigate("teacher_course_lesson_view.xhtml?cid=" + getCourse_id());
    }
    
    public void back() throws IOException
    {
        help.navigate("teacher_course_lesson_view.xhtml?cid=" + getCourse_id());
    }
    
    public void sback() throws IOException
    {
        help.navigate("student_course_lesson_view.xhtml?cid=" + getCourse_id());
    }
    
    public void back_back() throws IOException
    {
        help.navigate("teacher_course.xhtml?cid=" + getCourse_id());
    }
    
    public void sback_back() throws IOException
    {
        help.navigate("student_course.xhtml?cid=" + getCourse_id());
    }
    
    
    public List<Lesson> getLessons()
    {
        return ls.getLessons();
    }
    
    public void setLessonByID()
    {
        lesson = ls.getLesson(help.toInt(help.getParam("ID")));
    }
    
    
    public List<Lesson> getLessons_Course()
    {
        return ls.getLessons_Course(getCourse_id());
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
        Lesson_Controller.uFileName = uFileName;
    }
    
    
    private String getFileName()
    {
        int rand1 = (int) (Math.random()*357);
        return "Lesson_" + rand1;
    }
    
    public void uploadFile(FileUploadEvent event) {  
        FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded."); 
        FacesContext.getCurrentInstance().addMessage(null, msg);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String fname = event.getFile().getFileName();
        int fnLen = fname.length();
        String ext = fname.substring(fnLen-3, fnLen);
        setuFileName(getFileName() + "." + ext);
        String rootDir = ec.getRealPath("/data/lesson/" + getuFileName());
        rootDir = getItRight(rootDir);
        try {
            copyFile(event.getFile().getInputstream(),rootDir);
        } catch (IOException e) {
        e.printStackTrace();
        }
    } 
   
    
}

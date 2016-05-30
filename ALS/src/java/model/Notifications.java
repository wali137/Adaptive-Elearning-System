package model;
// Generated Jun 18, 2013 3:04:47 AM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * Notifications generated by hbm2java
 */
public class Notifications  implements java.io.Serializable {


     private Integer id;
     private String courseId;
     private String details;
     private Date ndate;
     private int studentId;
     private String notiFor;
     private String link;

    public Notifications() {
    }

    public Notifications(String courseId, String details, Date ndate, int studentId, String notiFor, String link) {
       this.courseId = courseId;
       this.details = details;
       this.ndate = ndate;
       this.studentId = studentId;
       this.notiFor = notiFor;
       this.link = link;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getCourseId() {
        return this.courseId;
    }
    
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
    public String getDetails() {
        return this.details;
    }
    
    public void setDetails(String details) {
        this.details = details;
    }
    public Date getNdate() {
        return this.ndate;
    }
    
    public void setNdate(Date ndate) {
        this.ndate = ndate;
    }
    public int getStudentId() {
        return this.studentId;
    }
    
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    public String getNotiFor() {
        return this.notiFor;
    }
    
    public void setNotiFor(String notiFor) {
        this.notiFor = notiFor;
    }
    public String getLink() {
        return this.link;
    }
    
    public void setLink(String link) {
        this.link = link;
    }




}



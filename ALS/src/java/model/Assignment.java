package model;
// Generated Jun 18, 2013 3:04:47 AM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * Assignment generated by hbm2java
 */
public class Assignment  implements java.io.Serializable {


     private Integer id;
     private String assignmentName;
     private String statement;
     private Integer totalMarks;
     private Date dueDate;
     private String courseId;
     private String slide;
     private Integer createdBy;
     private int submissions;

    public Assignment() {
    }

	
    public Assignment(int submissions) {
        this.submissions = submissions;
    }
    public Assignment(String assignmentName, String statement, Integer totalMarks, Date dueDate, String courseId, String slide, Integer createdBy, int submissions) {
       this.assignmentName = assignmentName;
       this.statement = statement;
       this.totalMarks = totalMarks;
       this.dueDate = dueDate;
       this.courseId = courseId;
       this.slide = slide;
       this.createdBy = createdBy;
       this.submissions = submissions;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getAssignmentName() {
        return this.assignmentName;
    }
    
    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }
    public String getStatement() {
        return this.statement;
    }
    
    public void setStatement(String statement) {
        this.statement = statement;
    }
    public Integer getTotalMarks() {
        return this.totalMarks;
    }
    
    public void setTotalMarks(Integer totalMarks) {
        this.totalMarks = totalMarks;
    }
    public Date getDueDate() {
        return this.dueDate;
    }
    
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    public String getCourseId() {
        return this.courseId;
    }
    
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
    public String getSlide() {
        return this.slide;
    }
    
    public void setSlide(String slide) {
        this.slide = slide;
    }
    public Integer getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }
    public int getSubmissions() {
        return this.submissions;
    }
    
    public void setSubmissions(int submissions) {
        this.submissions = submissions;
    }




}



package model;
// Generated Jun 18, 2013 3:04:47 AM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * Quiz generated by hbm2java
 */
public class Quiz  implements java.io.Serializable {


     private Integer id;
     private String quizName;
     private Integer quizDuration;
     private Date createdDate;
     private Integer createdBy;
     private String courseId;
     private Integer sectionId;
     private Integer topicId;
     private Integer totalMarks;

    public Quiz() {
    }

	
    public Quiz(String quizName) {
        this.quizName = quizName;
    }
    public Quiz(String quizName, Integer quizDuration, Date createdDate, Integer createdBy, String courseId, Integer sectionId, Integer topicId, Integer totalMarks) {
       this.quizName = quizName;
       this.quizDuration = quizDuration;
       this.createdDate = createdDate;
       this.createdBy = createdBy;
       this.courseId = courseId;
       this.sectionId = sectionId;
       this.topicId = topicId;
       this.totalMarks = totalMarks;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getQuizName() {
        return this.quizName;
    }
    
    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }
    public Integer getQuizDuration() {
        return this.quizDuration;
    }
    
    public void setQuizDuration(Integer quizDuration) {
        this.quizDuration = quizDuration;
    }
    public Date getCreatedDate() {
        return this.createdDate;
    }
    
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public Integer getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }
    public String getCourseId() {
        return this.courseId;
    }
    
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
    public Integer getSectionId() {
        return this.sectionId;
    }
    
    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }
    public Integer getTopicId() {
        return this.topicId;
    }
    
    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }
    public Integer getTotalMarks() {
        return this.totalMarks;
    }
    
    public void setTotalMarks(Integer totalMarks) {
        this.totalMarks = totalMarks;
    }




}


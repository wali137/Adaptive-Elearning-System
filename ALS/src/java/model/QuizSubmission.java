package model;
// Generated Jun 18, 2013 3:04:47 AM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * QuizSubmission generated by hbm2java
 */
public class QuizSubmission  implements java.io.Serializable {


     private Integer quizSubmissionId;
     private Integer studentId;
     private Integer quizId;
     private String courseId;
     private Date submissionDate;

    public QuizSubmission() {
    }

    public QuizSubmission(Integer studentId, Integer quizId, String courseId, Date submissionDate) {
       this.studentId = studentId;
       this.quizId = quizId;
       this.courseId = courseId;
       this.submissionDate = submissionDate;
    }
   
    public Integer getQuizSubmissionId() {
        return this.quizSubmissionId;
    }
    
    public void setQuizSubmissionId(Integer quizSubmissionId) {
        this.quizSubmissionId = quizSubmissionId;
    }
    public Integer getStudentId() {
        return this.studentId;
    }
    
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
    public Integer getQuizId() {
        return this.quizId;
    }
    
    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }
    public String getCourseId() {
        return this.courseId;
    }
    
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
    public Date getSubmissionDate() {
        return this.submissionDate;
    }
    
    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }




}



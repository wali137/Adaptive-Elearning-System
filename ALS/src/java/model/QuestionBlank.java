package model;
// Generated Jun 18, 2013 3:04:47 AM by Hibernate Tools 3.2.1.GA



/**
 * QuestionBlank generated by hbm2java
 */
public class QuestionBlank  implements java.io.Serializable {


     private Integer id;
     private String correct;
     private int questionId;

    public QuestionBlank() {
    }

    public QuestionBlank(String correct, int questionId) {
       this.correct = correct;
       this.questionId = questionId;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getCorrect() {
        return this.correct;
    }
    
    public void setCorrect(String correct) {
        this.correct = correct;
    }
    public int getQuestionId() {
        return this.questionId;
    }
    
    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }




}



package model;
// Generated Jun 18, 2013 3:04:47 AM by Hibernate Tools 3.2.1.GA



/**
 * Teacher generated by hbm2java
 */
public class Teacher  implements java.io.Serializable {


     private Integer teacherId;
     private String qualification;
     private String designation;
     private Integer departmentId;
     private int userId;
     private String ranking;

    public Teacher() {
    }

	
    public Teacher(int userId) {
        this.userId = userId;
    }
    public Teacher(String qualification, String designation, Integer departmentId, int userId, String ranking) {
       this.qualification = qualification;
       this.designation = designation;
       this.departmentId = departmentId;
       this.userId = userId;
       this.ranking = ranking;
    }
   
    public Integer getTeacherId() {
        return this.teacherId;
    }
    
    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }
    public String getQualification() {
        return this.qualification;
    }
    
    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
    public String getDesignation() {
        return this.designation;
    }
    
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    public Integer getDepartmentId() {
        return this.departmentId;
    }
    
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }
    public int getUserId() {
        return this.userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getRanking() {
        return this.ranking;
    }
    
    public void setRanking(String ranking) {
        this.ranking = ranking;
    }




}



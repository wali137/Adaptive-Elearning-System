package model;
// Generated Jun 18, 2013 3:04:47 AM by Hibernate Tools 3.2.1.GA



/**
 * CourseDivision generated by hbm2java
 */
public class CourseDivision  implements java.io.Serializable {


     private Integer divisionId;
     private Integer sectionId;
     private String courseId;

    public CourseDivision() {
    }

    public CourseDivision(Integer sectionId, String courseId) {
       this.sectionId = sectionId;
       this.courseId = courseId;
    }
   
    public Integer getDivisionId() {
        return this.divisionId;
    }
    
    public void setDivisionId(Integer divisionId) {
        this.divisionId = divisionId;
    }
    public Integer getSectionId() {
        return this.sectionId;
    }
    
    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }
    public String getCourseId() {
        return this.courseId;
    }
    
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }




}



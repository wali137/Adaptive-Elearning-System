package model;
// Generated Jun 18, 2013 3:04:47 AM by Hibernate Tools 3.2.1.GA



/**
 * Questiontype generated by hbm2java
 */
public class Questiontype  implements java.io.Serializable {


     private Integer typeId;
     private String typeName;

    public Questiontype() {
    }

    public Questiontype(String typeName) {
       this.typeName = typeName;
    }
   
    public Integer getTypeId() {
        return this.typeId;
    }
    
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
    public String getTypeName() {
        return this.typeName;
    }
    
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }




}


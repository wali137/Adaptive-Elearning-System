package model;
// Generated Jun 18, 2013 3:04:47 AM by Hibernate Tools 3.2.1.GA



/**
 * Attachment generated by hbm2java
 */
public class Attachment  implements java.io.Serializable {


     private Integer attachmentId;
     private String type;
     private String path;
     private String size;

    public Attachment() {
    }

    public Attachment(String type, String path, String size) {
       this.type = type;
       this.path = path;
       this.size = size;
    }
   
    public Integer getAttachmentId() {
        return this.attachmentId;
    }
    
    public void setAttachmentId(Integer attachmentId) {
        this.attachmentId = attachmentId;
    }
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    public String getPath() {
        return this.path;
    }
    
    public void setPath(String path) {
        this.path = path;
    }
    public String getSize() {
        return this.size;
    }
    
    public void setSize(String size) {
        this.size = size;
    }




}


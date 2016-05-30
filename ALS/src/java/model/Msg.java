package model;
// Generated Jun 18, 2013 3:04:47 AM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * Msg generated by hbm2java
 */
public class Msg  implements java.io.Serializable {


     private Integer id;
     private int toId;
     private int fromId;
     private Date sentDate;
     private Date sentTime;
     private String sentMessage;
     private Integer replyId;
     private Integer userId;

    public Msg() {
    }

	
    public Msg(int toId, int fromId, Date sentDate, Date sentTime) {
        this.toId = toId;
        this.fromId = fromId;
        this.sentDate = sentDate;
        this.sentTime = sentTime;
    }
    public Msg(int toId, int fromId, Date sentDate, Date sentTime, String sentMessage, Integer replyId, Integer userId) {
       this.toId = toId;
       this.fromId = fromId;
       this.sentDate = sentDate;
       this.sentTime = sentTime;
       this.sentMessage = sentMessage;
       this.replyId = replyId;
       this.userId = userId;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public int getToId() {
        return this.toId;
    }
    
    public void setToId(int toId) {
        this.toId = toId;
    }
    public int getFromId() {
        return this.fromId;
    }
    
    public void setFromId(int fromId) {
        this.fromId = fromId;
    }
    public Date getSentDate() {
        return this.sentDate;
    }
    
    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }
    public Date getSentTime() {
        return this.sentTime;
    }
    
    public void setSentTime(Date sentTime) {
        this.sentTime = sentTime;
    }
    public String getSentMessage() {
        return this.sentMessage;
    }
    
    public void setSentMessage(String sentMessage) {
        this.sentMessage = sentMessage;
    }
    public Integer getReplyId() {
        return this.replyId;
    }
    
    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }
    public Integer getUserId() {
        return this.userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }




}


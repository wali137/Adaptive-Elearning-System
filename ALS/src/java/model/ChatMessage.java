package model;
// Generated Jun 18, 2013 3:04:47 AM by Hibernate Tools 3.2.1.GA



/**
 * ChatMessage generated by hbm2java
 */
public class ChatMessage  implements java.io.Serializable {


     private Integer messageId;
     private int chatId;
     private int userId;

    public ChatMessage() {
    }

    public ChatMessage(int chatId, int userId) {
       this.chatId = chatId;
       this.userId = userId;
    }
   
    public Integer getMessageId() {
        return this.messageId;
    }
    
    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }
    public int getChatId() {
        return this.chatId;
    }
    
    public void setChatId(int chatId) {
        this.chatId = chatId;
    }
    public int getUserId() {
        return this.userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }




}



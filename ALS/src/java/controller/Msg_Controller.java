/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import model.Msg;
import model.User;
import repositery.User_Rep;
import services.Msg_Service;

/**
 *
 * @author DemiXsoft
 */
public class Msg_Controller {
      
    public Msg_Controller() {
       ms =new Msg_Service();
       msg = new Msg();
    }
    // Getter / setter + Global Variables
    Msg_Service ms;
    User_Rep ur = new User_Rep();
    Msg msg;

    public Msg getMsg() {
        return msg;
    }

    public void setMsg(Msg msg) {
        this.msg = msg;
    }
    int logged_id;
    List<User> teachers;

    public List<User> getTeachers() {
        return ur.getTeachers();
    }

    public void setTeachers(List<User> teachers) {
        this.teachers = teachers;
    }
    
    
    
    Helper help = new Helper();
    
    public int getLogged_id() {
        return logged_id;
    }

    public void setLogged_id(int logged_id) {
        this.logged_id = logged_id;
    }
    
   
    List<Msg> msgList;
    List<Msg> msgUsers;
    List<Msg> messages;
    int from_id;

    public int getFrom_id() {
        return from_id;
    }

    public void setFrom_id(int from_id) {
        this.from_id = from_id;
    }
    

    public List<Msg> getMessages() {
        return ms.getList(logged_id, from_id);
    }

    public void setMessages(List<Msg> messages) {
        this.messages = messages;
    }
    
    String totalMessages;

    public String getTotalMessages() {
        return totalMessages;
    }

    public void setTotalMessages(int fromID) {
        this.totalMessages = ms.getTotalMessages(help.toInt(help.getCurrentUserID()), fromID);
    }
    

    public List<Msg> getMsgUsers() {
        return ms.getMsgUsers(help.toInt(help.getCurrentUserID()));
    }

    public void setMsgUsers(List<Msg> msgUsers) {
        this.msgUsers = msgUsers;
    }
    

    public List<Msg> getMsgList() {
        return ms.getList(help.toInt(help.getCurrentUserID()));
    }

    public void setMsgList(int fromID) {
        this.msgList = ms.getList(help.toInt(help.getCurrentUserID()), fromID);
    }
    
    public void send() throws ParseException
    {
        msg.setFromId(help.toInt(help.getCurrentUserID()));
        Date date = new Date(); 
        DateFormat df; 
        df = DateFormat.getDateInstance(DateFormat.SHORT); 
        String shrt =  df.format(date);
        date = new SimpleDateFormat("mm/dd/yy", Locale.ENGLISH).parse(shrt);
        msg.setSentDate(date);
        msg.setSentTime(help.toTimeFormat(help.getCurrentTime()));
        int toID = msg.getToId();
        int fromID = msg.getFromId();
        msg.setUserId(fromID);
        msg.setReplyId(toID);
        ms.save(msg);
        // 2nd msg
        msg.setToId(fromID);
        msg.setFromId(toID);
        msg.setUserId(fromID);
        msg.setReplyId(null);
        ms.save(msg);
        help.pop_msg("Message Sent Sucessfully!");
        msg.setSentMessage(null);
        msg.setReplyId(null);
    }
    
}

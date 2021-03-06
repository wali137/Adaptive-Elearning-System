package model;
// Generated Jun 18, 2013 3:04:47 AM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * Todo generated by hbm2java
 */
public class Todo  implements java.io.Serializable {


     private Integer id;
     private int userId;
     private String todoTitle;
     private Date todoDate;

    public Todo() {
    }

    public Todo(int userId, String todoTitle, Date todoDate) {
       this.userId = userId;
       this.todoTitle = todoTitle;
       this.todoDate = todoDate;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public int getUserId() {
        return this.userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getTodoTitle() {
        return this.todoTitle;
    }
    
    public void setTodoTitle(String todoTitle) {
        this.todoTitle = todoTitle;
    }
    public Date getTodoDate() {
        return this.todoDate;
    }
    
    public void setTodoDate(Date todoDate) {
        this.todoDate = todoDate;
    }




}



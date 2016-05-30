/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.util.Date;
import java.util.List;
import model.Todo;
import services.todo_serv;
/**
 *
 * @author DemiXsoft
 */
public class todo_Controller {
    
    todo_serv tds = new todo_serv();
    private static Todo selectedTodo = new Todo();
    
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    private Date date;
    

    public Todo getSelectedTodo() {
        return selectedTodo;
    }

    public void setSelectedTodo(Todo selectedTodo) {
        this.selectedTodo = selectedTodo;
    }
    
    public List<Todo> getTodoList()
    {
        return tds.getTodoList();
    }
    
}

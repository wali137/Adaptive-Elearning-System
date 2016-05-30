/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import java.util.List;
import model.Todo;
import repositery.todo_rep;
/**
 *
 * @author DemiXsoft
 */
public class todo_serv {
    
    todo_rep tdr = new todo_rep();
    
    public List<Todo> getTodoList()
    {
        return tdr.getTodoList();
    }
    
}

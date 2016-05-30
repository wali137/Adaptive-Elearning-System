/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import model.Quiz;
import repositery.Quiz_Rep;

/**
 *
 * @author DemiXsoft
 */
public class Quiz_Service {
    
      Quiz_Rep qr;
    
      public Quiz_Service() {
        qr = new Quiz_Rep();
    }
 
    public int save(Quiz q){
        
        return qr.save(q);
    }
    
    public String getMaxID()
    {
        return qr.getMaxID();
    }
    
    public String getTitle(int ID)
    {
        return qr.getTitle(ID);
    }
    
    public void update(Quiz q)
    {
        qr.update(q);
    }
    
    public List<Quiz> getQuiz()
    {
        return qr.getQuiz();
    }
    
    public void delete(Quiz q)
    {
        qr.Delete(q);
        qr.del_links(q.getId());
    }
    
    public List<Quiz> submissions(int id, String cid)
    {
        return qr.submissions(id,cid);
    }
    
}

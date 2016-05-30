/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import model.Msg;
import repositery.Msg_Rep;

/**
 *
 * @author DemiXsoft
 */
public class Msg_Service {
    
      Msg_Rep mr;
    
      public Msg_Service() {
        mr = new Msg_Rep();
      }
 
      public void save(Msg m){        
        mr.save(m);
      }

      public List<Msg> getList(int UserID)
      {
        return mr.getList(UserID);
      }
      
      public List<Msg> getMsgUsers(int UserID)
      {
          return mr.getListMsgUsers(UserID);
      }
    
      public List<Msg> getList(int UserID,int fromID)
      {
          return mr.getList(UserID, fromID);
      }
      
      public String getTotalMessages(int UID, int fromID)
      {
          return mr.getTotalMessages(UID, fromID);
      }
}

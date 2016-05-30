/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package repositery;

import hibernate.HibernateUtil;
import org.hibernate.Session;


public class DbConnection {
    
    
     public Session OpenConnection()
    {
          try {
              Session session ;
              
            
                
                  session = HibernateUtil.getSessionFactory().openSession();
                  session.beginTransaction();
            
              
              return session;
          }
          catch(Exception ex)
          {
              System.out.println(ex.getMessage());
              return null;
          }
    
    }
     
    public void CloseConnection(Session session)
    {
        session.getTransaction().commit();
        session.close();
    }
    
    public void rollBack(Session session)
    {
        session.getTransaction().rollback();
    }
     
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package repositery;

import hibernate.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author dell
 */
public class frequentRepo {
    
         public Object getid2obj(int id,Class<?> type)
    {
        Transaction transaction = null;
          Session session = null;
            Object obj = null;
                try
                {
                    session = HibernateUtil.getSessionFactory().openSession();
                    transaction = session.beginTransaction();               
                    obj = session.get(type, id);
                 
                    transaction.commit();    
                }catch (HibernateException e) {
                       System.out.println(e.getMessage()); 
                }finally{
                    session.flush();
                }
                     return type.cast(obj);
    }
    
}

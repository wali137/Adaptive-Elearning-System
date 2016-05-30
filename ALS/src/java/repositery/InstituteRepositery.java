/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package repositery;


import model.Institute;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author dell
 */
public class InstituteRepositery {
    
        private List<Institute> List;
        DbConnection connection;
        Session InstitutesSession;
        Query query;
        private List<Institute> list;
        private Session session;
        
        public InstituteRepositery()
        {
            connection = new DbConnection();
        }
        
         public boolean Save(Institute _modInstitutes){
        try{
            
        
        session = connection.OpenConnection();
        session.save(_modInstitutes);
        connection.CloseConnection(session);
        
        return true;
        }catch(Exception ex){
            
            connection.rollBack(session);
            
            return false;
            
        }
    }
         //========================================
          public boolean Delete(Institute _modInstitutes){
        try{
            
        
        session = connection.OpenConnection();
        session.delete(_modInstitutes);
        connection.CloseConnection(session);
        
        return true;
        }catch(Exception ex){
            
            connection.rollBack(session);
            
            return false;
            
        }
    }
          
          public boolean Update(Institute _modInstitutes) {
        try{
           
        session = connection.OpenConnection();
        session.update(_modInstitutes);
        connection.CloseConnection(session);
        return true;
        }
        catch(Exception ex){
            return false;
        }
    }
          
          
          
          
          
         //========================================
        public List<Institute> getInstitutesList(){
            
            try{
            InstitutesSession = connection.OpenConnection();
            query = InstitutesSession.createSQLQuery("select * from institute");
            list = query.list();
            connection.CloseConnection(InstitutesSession);
            
            return list;
            }
            catch(Exception ex){
                
                System.out.println(ex.getMessage());
                return null;
            
            }
        }
}

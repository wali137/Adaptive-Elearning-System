/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package repositery;


import model.Program;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author dell
 */
public class ProgramRepositery {
    InstituteRepositery _repInstituteRepositery;
    
        private List<Program> List;
        DbConnection connection;
        Session ProgramsSession;
        Query query;
        private List<Program> list;
        private Session session;
        
        public ProgramRepositery()
        {
            connection = new DbConnection();
        }
        
         public boolean Save(Program _modPrograms){
        try{
            
        
        session = connection.OpenConnection();
        session.save(_modPrograms);
        connection.CloseConnection(session);
        
        return true;
        }catch(Exception ex){
            
          //  connection.rollBack(session);
            
            return false;
            
        }
    }
         //========================================
          public boolean Delete(Program _modPrograms){
        try{
            
        
        session = connection.OpenConnection();
        session.delete(_modPrograms);
        connection.CloseConnection(session);
        
        return true;
        }catch(Exception ex){
            
            connection.rollBack(session);
            
            return false;
            
        }
    }
          
          public boolean Update(Program _modPrograms) {
        try{
           
        session = connection.OpenConnection();
        session.update(_modPrograms);
        connection.CloseConnection(session);
        return true;
        }
        catch(Exception ex){
            return false;
        }
    }
          
          
          
          
          
         //========================================
        public List<Program> getProgramsList(){
            
            try{
            ProgramsSession = connection.OpenConnection();
            query = ProgramsSession.createSQLQuery("select * from Program");
            list = query.list();
            connection.CloseConnection(ProgramsSession);
            
            return list;
            }
            catch(Exception ex){
                
                System.out.println(ex.getMessage());
                return null;
            
            }
        }




}


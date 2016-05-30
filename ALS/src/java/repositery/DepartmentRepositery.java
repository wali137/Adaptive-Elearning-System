/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package repositery;


import model.Department;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author dell
 */
public class DepartmentRepositery {
    InstituteRepositery _repInstituteRepositery;
    
        private List<Department> List;
        DbConnection connection;
        Session DepartmentsSession;
        Query query;
        private List<Department> list;
        private Session session;
        
        public DepartmentRepositery()
        {
            connection = new DbConnection();
        }
        
         public boolean Save(Department _modDepartments){
        try{
            
        
        session = connection.OpenConnection();
        session.save(_modDepartments);
        connection.CloseConnection(session);
        
        return true;
        }catch(Exception ex){
            
          //  connection.rollBack(session);
            
            return false;
            
        }
    }
         //========================================
          public boolean Delete(Department _modDepartments){
        try{
            
        
        session = connection.OpenConnection();
        session.delete(_modDepartments);
        connection.CloseConnection(session);
        
        return true;
        }catch(Exception ex){
            
            connection.rollBack(session);
            
            return false;
            
        }
    }
          
          public boolean Update(Department _modDepartments) {
        try{
           
        session = connection.OpenConnection();
        session.update(_modDepartments);
        connection.CloseConnection(session);
        return true;
        }
        catch(Exception ex){
            return false;
        }
    }
          
          
          
          
          
         //========================================
        public List<Department> getDepartmentsList(){
            
            try{
            DepartmentsSession = connection.OpenConnection();
            query = DepartmentsSession.createSQLQuery("select * from department");
            list = query.list();
            connection.CloseConnection(DepartmentsSession);
            
            return list;
            }
            catch(Exception ex){
                
                System.out.println(ex.getMessage());
                return null;
            
            }
        }




}


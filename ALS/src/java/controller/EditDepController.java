/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import hibernate.HibernateUtil;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import model.Department;
//import model.Person;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author mubashar187
 */
public class EditDepController 
{
    //private Department dep = new Department();
    private Department dep;
    
    
    //private  List<Department> pdep ;
    
    
    private Session s;
    private Transaction tx;
    
    private int persid;
    public String pass;

//    public Department getIns() {
//        return dep;
//    }
//
//    public void setIns(Department dep) {
//        this.dep = dep;
//    }
//
//   

    public Department getDep() {
        return dep;
    }

    public void setDep(Department dep) {
        this.dep = dep;
    }
    
    
    


    //************All Patient Row Selection***********//
    
    public void onRowSelect(SelectEvent event) {
    
     System.out.println("............HERE...................");
     try
     {
     
       dep = (Department) event.getObject();
       System.out.println("Department department_id "+dep.getDepartmentId());
      
      
     }
     
     catch(Exception ex)
        {
            ex.printStackTrace();
            System.out.println("Department Row Select Exception :" + ex);
        }
     
     }
    
    
     public void onRowUnSelect(UnselectEvent event) {
    
     
     try
     {
        dep = (Department) event.getObject();
       System.out.println("Department department_id "+dep.getDepartmentId());
      
      
     }
      
     catch(Exception ex)
        {
            ex.printStackTrace();
            System.out.println("Department Row Select Exception :" + ex);
     
     }
     

     }


public List<Department> getPdep() {
        try {

            s = HibernateUtil.getSessionFactory().openSession();
            tx = s.beginTransaction();
            Query q1 = s.createQuery("from Department as dep");

            tx.commit();

            List<Department> dp = (List<Department>) q1.list();

            return dp;

        } catch (HibernateException ex) {
            System.out.println("Department Error " + ex.getMessage());

        }
//        finally {
//            s.close();
//
//        }

        return null;
    }
//public List<Department> getInss() {
//        try {
//
//            s = HibernateUtil.getSessionFactory().openSession();
//            tx = s.beginTransaction();
//            Query q1 = s.createQuery("from Department as Ins");
//
//            tx.commit();
//
//            List<Department> Ins = (List<Department>) q1.list();
//
//            return Ins;
//
//        } catch (HibernateException ex) {
//            System.out.println("Department Error " + ex.getMessage());
//
//        } finally {
//            s.close();
//
//        }
//
//        return null;
//    }


         
         //Update Patient Personal Details
     
      public void updateDepartment(ActionEvent action)
        
        {
            
            Transaction transaction = null;
            Session session = null;
            //Integer autoId = 0;
                try
                {
                    session = HibernateUtil.getSessionFactory().openSession();
                    transaction = session.beginTransaction();
                    session.update(dep);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Edit Successfully!"));
                    transaction.commit();    
                }catch (HibernateException e) {
                       System.out.println(e.getMessage()); 
                }
                
                finally{
                   session.close();
               }
        
       
        
        }

      
     
}

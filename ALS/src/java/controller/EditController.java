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
import model.Institute;
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
public class EditController 
{
    private Institute ins = new Institute();
    
    
    //private  List<Institute> pins ;
    
    
    private Session s;
    private Transaction tx;
    
    private int persid;
    public String pass;

    public Institute getIns() {
        return ins;
    }

    public void setIns(Institute ins) {
        this.ins = ins;
    }

   
    
    
    


    //************All Patient Row Selection***********//
    
    public void onRowSelect(SelectEvent event) {
    
     System.out.println("............HERE...................");
     try
     {
     
       ins = (Institute) event.getObject();
       System.out.println("Institute institute_id "+ins.getInstituteId());
      
      
     }
     
     catch(Exception ex)
        {
            ex.printStackTrace();
            System.out.println("Institute Row Select Exception :" + ex);
        }
     
     }
    
    
     public void onRowUnSelect(UnselectEvent event) {
    
     
     try
     {
        ins = (Institute) event.getObject();
       System.out.println("Institute institute_id "+ins.getInstituteId());
      
      
     }
      
     catch(Exception ex)
        {
            ex.printStackTrace();
            System.out.println("Institute Row Select Exception :" + ex);
     
     }
     

     }


public List<Institute> getPins() {
        try {

            s = HibernateUtil.getSessionFactory().openSession();
            tx = s.beginTransaction();
            Query q1 = s.createQuery("from Institute as Ins");

            tx.commit();

            List<Institute> Ins = (List<Institute>) q1.list();

            return Ins;

        } catch (HibernateException ex) {
            System.out.println("Institute Error " + ex.getMessage());

        } finally {
            s.close();

        }

        return null;
    }
//public List<Institute> getInss() {
//        try {
//
//            s = HibernateUtil.getSessionFactory().openSession();
//            tx = s.beginTransaction();
//            Query q1 = s.createQuery("from Institute as Ins");
//
//            tx.commit();
//
//            List<Institute> Ins = (List<Institute>) q1.list();
//
//            return Ins;
//
//        } catch (HibernateException ex) {
//            System.out.println("Institute Error " + ex.getMessage());
//
//        } finally {
//            s.close();
//
//        }
//
//        return null;
//    }


         
         //Update Patient Personal Details
     
      public void updateInstitute(ActionEvent action)
        
        {
            
            Transaction transaction = null;
            Session session = null;
            //Integer autoId = 0;
                try
                {
                    session = HibernateUtil.getSessionFactory().openSession();
                    transaction = session.beginTransaction();
                    session.update(ins);
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

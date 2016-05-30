/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author dell
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import hibernate.HibernateUtil;

import model.Institute;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Department;
import model.Program;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;




/**
 *
 * @author mushii
 */
public class InstituteController {

    Institute _modInstitute;
    private List<Institute> instList;
    private List<Department>depList;
    Session s=null;
    Transaction tx=null;
   
    public Institute getModInstitute() {
        if (_modInstitute == null) {

            _modInstitute = new Institute();
        }
        return _modInstitute;
    }

    public void setModInstitute(Institute _modInstitute) {
        this._modInstitute = _modInstitute;
    }

   
Department department;

    public Department getDepartment() {
        if (department == null) {

            department = new Department();
        }
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
    
   Program program;

    public Program getProgram() {
        if (program == null) {
            program = new Program();
            program.setDepartment(new Department());
        }
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }
    
    Institute inst = new Institute();

public void addpdetail(ActionEvent actions)
{
Session s=null;
Transaction tx=null;
        //String url=null;
        try 
        {
            s = HibernateUtil.getSessionFactory().openSession();
            tx = s.beginTransaction();
            inst.setInstituteName(_modInstitute.getInstituteName());
            inst.setInstituteAddress(_modInstitute.getInstituteAddress());
            inst.setInstituteWebsite(_modInstitute.getInstituteWebsite());
            inst.setContactUs(_modInstitute.getContactUs());
            
            s.save(inst);
            
           
                try {
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.getExternalContext().getFlash().setKeepMessages(true);

                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Institute details added succesfully!"));
                    FacesContext.getCurrentInstance().getExternalContext().redirect("AddDepartment.xhtml");
                    //FacesContext.getExternalContext).getFlash().setKeepMessages(true);

                } 
                catch (IOException ex) {
                    
                  Logger.getLogger(InstituteController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
             
            
            //System.out.println("???"+person.getGender()+account.getUsername());

           tx.commit();
            
        } catch (HibernateException ex) 
        {
            System.out.println("Inst Details Error " + ex.getMessage());

        } finally
        {
            s.close();

        }
        //return url; 


    }
  Department dep = new Department();
public void adddepdetail(ActionEvent actions)
{
Session s=null;
Transaction tx=null;
        //String url=null;
        try 
        {
            s = HibernateUtil.getSessionFactory().openSession();
            tx = s.beginTransaction();
            dep.setDepartmentName(department.getDepartmentName());
            dep.setDescription(department.getDescription());
            Institute in = (Institute) s.createQuery("from Institute as ins where ins.instituteName ='" + _modInstitute.getInstituteName() + "' ").uniqueResult();
            dep.setInstitute(in);
            s.save(dep);
            
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Department details added succesfully!"));
            
           
//                try {
//                    FacesContext context = FacesContext.getCurrentInstance();
//                    context.getExternalContext().getFlash().setKeepMessages(true);
//
//                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Department details added succesfully!"));
//                    FacesContext.getCurrentInstance().getExternalContext().redirect("AddProgram.xhtml");
//                    //FacesContext.getExternalContext).getFlash().setKeepMessages(true);
//
//                } 
//                catch (IOException ex) {
//                    
//                  Logger.getLogger(controller.InstituteController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                
             
            
            //System.out.println("???"+person.getGender()+account.getUsername());

           tx.commit();
            
        } catch (HibernateException ex) 
        {
            System.out.println("Department Details Error " + ex.getMessage());

        } finally
        {
            s.close();

        }
        //return url; 


    }

 Program p = new Program();
public void addprogdetail(ActionEvent actions)
{
Session s=null;
Transaction tx=null;
        //String url=null;
        try 
        {
            s = HibernateUtil.getSessionFactory().openSession();
            tx = s.beginTransaction();
            p.setProgramName(program.getProgramName());
            Department d = (Department) s.createQuery("from Department as de where de.departmentName='" +  department.getDepartmentName() + "' ").uniqueResult();
            p.setDepartment(d);
            s.save(p);
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Program details added succesfully!"));
//                   
           
//                try {
//                    FacesContext context = FacesContext.getCurrentInstance();
//                    context.getExternalContext().getFlash().setKeepMessages(true);
//
//                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Program details added succesfully!"));
//                    FacesContext.getCurrentInstance().getExternalContext().redirect("admin.xhtml");
//                    //FacesContext.getExternalContext).getFlash().setKeepMessages(true);
//
//                } 
//                catch (IOException ex) {
//                    
//                  Logger.getLogger(controller.InstituteController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                
             
            
            //System.out.println("???"+person.getGender()+account.getUsername());

           tx.commit();
            
        } catch (HibernateException ex) 
        {
            System.out.println("Program Details Error " + ex.getMessage());

        } finally
        {
            s.close();

        }
        //return url; 


    }

    public List<Institute> getInstList() {
        return instList;
    }

    public List<Department> getDepList() {
        try {

            s = HibernateUtil.getSessionFactory().openSession();
            tx = s.beginTransaction();
            Query q1 = s.createQuery("from Department as dpt");

            tx.commit();

            List<Department> prg = (List<Department>) q1.list();

            return prg;

        } catch (HibernateException ex) {
            System.out.println("Department Error " + ex.getMessage());

        } finally {
            s.close();

        }

        return null;
    }



 public List<Institute> getIns() {
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





}


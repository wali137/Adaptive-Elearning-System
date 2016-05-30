/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.StudentRegistration;
import org.hibernate.Query;
import org.hibernate.Session;
import repositery.DbConnection;

/**
 *
 * @author dell
 */
public class registercourseController {
int sid;
    private List<String> selectedcourseList;
    private Map<String, String> courseMap;
StudentRegistration sr=new StudentRegistration();
    public List<String> getSelectedcourseList() {
        return selectedcourseList;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public StudentRegistration getSr() {
        return sr;
    }

    public void setSr(StudentRegistration sr) {
        this.sr = sr;
    }

    public registercourseController() {
        
    }

    public void setSelectedcourseList(List<String> selectedcourseList) {
        this.selectedcourseList = selectedcourseList;
    }

    public Map<String, String> getCourseMap() {
        return courseMap;
    }

    public void setCourseMap(Map<String, String> courseMap) {
        this.courseMap = courseMap;
    }
    Session session;
    Query query;
    DbConnection connection;

    public void getcr() {
//        DbConnection con = new DbConnection();
//        for (String s : symtomIdList) {
//            Session session = con.OpenConnection();
//            int symtomId = Integer.parseInt(s);
//            BodyPartSymptoms bpsObj = (BodyPartSymptoms) fd.getid2obj(symtomId, BodyPartSymptoms.class);
//            //PatientSymptomsDetails obj = new PatientSymptomsDetails();
//            getPatientSymtoms().setBodyPartSymptoms(bpsObj);
//            getPatientSymtoms().setPcId(pc_id);
//            getPatientSymtoms().setDate(currentDate);
//            pSym_id = (Integer)session.save(getPatientSymtoms());
//            con.CloseConnection(session);
//        }
//        ec = FacesContext.getCurrentInstance().getExternalContext();
//
//        try {
//            ec.redirect("/hTemplate/faces/checkupAssignTest.xhtml");
//        } catch (IOException e) {
//            // log exception...
//        }
//    }



connection =  new DbConnection();
        session = connection.OpenConnection();

        query = session.createSQLQuery("SELECT course_id,course_name FROM course WHERE course_id NOT IN  ( SELECT course_id  FROM student_registration WHERE student_id = "+getSid()+")");



        List result = query.list();
        connection.CloseConnection(session);
        courseMap = new HashMap<String,String>();
        for (Object o : result) {
            Object[] obj = (Object[]) o;
            courseMap.put(obj[1].toString(), obj[0].toString());
        }

    }

public void saveCourse() {
     
        DbConnection con = new DbConnection();
        for (String s :selectedcourseList ) {
            Session session = con.OpenConnection();
           sr.setStudentId(sid);
           sr.setCourseId(s);
          session.save(sr);
           
            con.CloseConnection(session);
        }
       
        // log exception...
        
    }



}
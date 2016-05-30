/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import org.hibernate.Query;
import org.hibernate.Session;
import repositery.DbConnection;

/**
 *
 * @author dell
 */
public class StudentListController {
    List list;
    int teacherid;
    List lis;

    public List getLis() {
        return lis;
    }

    public void setLis(List lis) {
        this.lis = lis;
    }
    String coursename="Machine Learning";

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public int getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(int teacherid) {
        this.teacherid = teacherid;
    }
    

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
       
public StudentListController(){
        getcrlist();
        Student(teacherid, coursename);
}
public void Student(int tid,String cname){
    Session session;
        Query query;
        DbConnection connection = new DbConnection();
        session = connection.OpenConnection();

     query = session.createSQLQuery(
            "CALL  getmystudents(:tid,:cname)")
                .setParameter("tid", teacherid)
                 .setParameter("cname", coursename);
        list = query.list();
        connection.CloseConnection(session);

        





}
public void event(){

Student(teacherid, coursename);

}
public List<SelectItem> fillcombo(){
    
    List l = getcrlist();
    return ConvertToSelectItems((Object)l);
}
public List getcrlist(){

     Session session;
        Query query;
        DbConnection connection = new DbConnection();
        session = connection.OpenConnection();

     query = session.createSQLQuery("SELECT course.course_id,course_name  FROM teacher_course INNER  JOIN course ON teacher_course.course_id = course.`course_id` WHERE teacher_course.teacher_id="+teacherid);
          
        lis = query.list();
        connection.CloseConnection(session);
return lis;
        
    
    
}
  public List<SelectItem> ConvertToSelectItems(Object list){
    
    List<SelectItem> items = new ArrayList<SelectItem>();
    ArrayList<Object[]> objlist = (ArrayList<Object[]>) list;
    
    for(Object[] o : objlist){
        items.add(new SelectItem(o[0],o[1].toString()));
    }

        return items;
}
}
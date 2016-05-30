/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;
import repositery.DbConnection;

/**
 *
 * @author dell
 */
public class StudentQuizGraph implements Serializable {  
  
     private CartesianChartModel categoryModel;  
  
    private CartesianChartModel linearModel;  
    private CartesianChartModel linearModel2; 
    private CartesianChartModel linearModel3;
    
    private CartesianChartModel linearModel_Teacher;

    public CartesianChartModel getLinearModel_Teacher() {
        return linearModel_Teacher;
    }

    public void setLinearModel_Teacher(CartesianChartModel linearModel_Teacher) {
        this.linearModel_Teacher = linearModel_Teacher;
    }
    

    public CartesianChartModel getLinearModel3() {
        return linearModel3;
    }

    public void setLinearModel3(CartesianChartModel linearModel3) {
        this.linearModel3 = linearModel3;
    }
    

    public CartesianChartModel getLinearModel2() {
        return linearModel2;
    }

    public void setLinearModel2(CartesianChartModel linearModel2) {
        this.linearModel2 = linearModel2;
    }
    
  
  
  
    public CartesianChartModel getCategoryModel() {  
        return categoryModel;  
    }  
  
    public CartesianChartModel getLinearModel() {  
        return linearModel;  
    }  
  
   
    
    
    
       public void createLinearModel3() {  
        linearModel3 = new CartesianChartModel();  
        
         
  
//        LineChartSeries series2 = new LineChartSeries();  
//        series2.setLabel("Good");  
//        
//        LineChartSeries series3 = new LineChartSeries();  
//        series3.setLabel("poor"); 
        LineChartSeries s1 = null;
        LineChartSeries s2 = null;
        
         List l = c_ac2();
        
        for (Object o : l) {
            Object[] obj = (Object[]) o;
        
            s1 = new LineChartSeries();  
            s1.setLabel("Quiz"); 
 
            s1.set("Quiz", Double.parseDouble(obj[1].toString()));

            linearModel3.addSeries(s1);  
        }
 
        
         List ll = c_ac();
        
        for (Object o : ll) {
            Object[] obj = (Object[]) o;
        
            s2 = new LineChartSeries();  
            s2.setLabel("Assignment"); 
 
            s2.set("Assignment", Double.parseDouble(obj[1].toString()));

            linearModel3.addSeries(s2);  
        }
        
//        linearModel.addSeries(series2);  
//        linearModel.addSeries(series3); 
    
       
    }
    
    
    
    
        public void createLinearModel2() {  
        linearModel2 = new CartesianChartModel();  
        
         
  
//        LineChartSeries series2 = new LineChartSeries();  
//        series2.setLabel("Good");  
//        
//        LineChartSeries series3 = new LineChartSeries();  
//        series3.setLabel("poor"); 
        LineChartSeries s1 = null;
         List l = ac2();
        
        for (Object o : l) {
            Object[] obj = (Object[]) o;
        
            s1 = new LineChartSeries();  
            s1.setLabel(obj[0].toString()); 
           
            
            s1.set("Courses", Double.parseDouble(obj[1].toString()));
            
//            if (obj[1].toString().equals("excellent")) {
//                series2.set(obj[0].toString(), Double.parseDouble(obj[2].toString()));
//            }
//
//            if (obj[1].toString().equals("good")) {
//                series1.set(obj[0].toString(), Double.parseDouble(obj[2].toString()));
//            }
//             if (obj[1].toString().equals("bad")) {
//                series3.set(obj[0].toString(), Double.parseDouble(obj[2].toString()));
//            }
            linearModel2.addSeries(s1);  
        }
 
        
//        linearModel.addSeries(series2);  
//        linearModel.addSeries(series3); 
    
       
    }
    
        
        public void createLinearModel_Teacher() {  
        linearModel_Teacher = new CartesianChartModel();  
        
        LineChartSeries series1 = new LineChartSeries();  
        series1.setLabel("Excellent");  
  
        LineChartSeries series2 = new LineChartSeries();  
        series2.setLabel("Good");  
        
        LineChartSeries series3 = new LineChartSeries();  
        series3.setLabel("Average"); 
        
        LineChartSeries series4 = new LineChartSeries();  
        series3.setLabel("Poor"); 
        
        
        int st1 = 0;
        int st2 = 0;
        int st3 = 0;
        int st4 = 0;
        
         List l = ac_Teacher();
        
        for (Object o : l) {
            Object[] obj = (Object[]) o;
String test = obj[1].toString();
            if (obj[1].toString().equals("excellent")) {
                series2.set("Courses", Double.parseDouble(obj[2].toString()));
                st1++;
            }

            if (obj[1].toString().equals("above average")) {
                series1.set("Courses", Double.parseDouble(obj[2].toString()));
                st2++;
            }
             if (obj[1].toString().equals("average")) {
                series3.set("Courses", Double.parseDouble(obj[2].toString()));
                st3++;
             }
             if (obj[1].toString().equals("below average")) {
                series4.set("Courses", Double.parseDouble(obj[2].toString()));
                st4++;
             }
             
        }
 
        if(st1 > 0)
        {
            linearModel_Teacher.addSeries(series1);  
        }
        if(st2>0)
        {
            linearModel_Teacher.addSeries(series2);  
        }
        if(st3>0)
        {
            linearModel_Teacher.addSeries(series3); 
        }
        
        if(st4>0)
        {
            linearModel_Teacher.addSeries(series4); 
        }
    
       
    }
  
    public void createLinearModel() {  
        linearModel = new CartesianChartModel();  
        
         
  
//        LineChartSeries series2 = new LineChartSeries();  
//        series2.setLabel("Good");  
//        
//        LineChartSeries series3 = new LineChartSeries();  
//        series3.setLabel("poor"); 
        List l = ac();
        LineChartSeries[] s = new LineChartSeries[l.size()];
        int i = 0;
        for (Object o : l) {
            Object[] obj = (Object[]) o;

            s[i] = new LineChartSeries();  
            s[i].setLabel(obj[0].toString()); 
            
            s[i].set("Courses", Double.parseDouble(obj[1].toString()));
            
//            if (obj[1].toString().equals("excellent")) {
//                series2.set(obj[0].toString(), Double.parseDouble(obj[2].toString()));
//            }
//
//            if (obj[1].toString().equals("good")) {
//                series1.set(obj[0].toString(), Double.parseDouble(obj[2].toString()));
//            }
//             if (obj[1].toString().equals("bad")) {
//                series3.set(obj[0].toString(), Double.parseDouble(obj[2].toString()));
//            }
        linearModel.addSeries(s[i]);
        i++;
        }
 
        
        
//        linearModel.addSeries(series2);  
//        linearModel.addSeries(series3); 
    
       
    }
    
    int SID;
    String CID;

    public String getCID() {
        return CID;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }

    public int getSID() {
        return SID;
    }

    public void setSID(int SID) {
        this.SID = SID;
    }
    
    Helper help = new Helper();
    
    public List ac() {

        Session s;
        Query query;
        DbConnection connection = new DbConnection();
        s = connection.OpenConnection();

        query = s.createSQLQuery(
                "CALL quizmarks(:sid)")
                .setParameter("sid", SID);

        List result = query.list();
        connection.CloseConnection(s);

        return result;

   }
    
     public List ac2() {

        Session s;
        Query query;
        DbConnection connection = new DbConnection();
        s = connection.OpenConnection();

        query = s.createSQLQuery(
                "CALL assignMarks(:sid)")
                .setParameter("sid", SID);

        List result = query.list();
        connection.CloseConnection(s);

        return result;

   }
     
     public List c_ac2() {

        Session s;
        Query query;
        DbConnection connection = new DbConnection();
        s = connection.OpenConnection();

        query = s.createSQLQuery(
                "CALL AssignCourse(:sid,:cid)")
                .setParameter("sid", SID);
                query.setParameter("cid", CID);

        List result = query.list();
        connection.CloseConnection(s);

        return result;

   }
     
       public List c_ac() {

        Session s;
        Query query;
        DbConnection connection = new DbConnection();
        s = connection.OpenConnection();

        query = s.createSQLQuery(
                "CALL QuizCourse(:sid,:cid)")
                .setParameter("sid", SID);
                query.setParameter("cid", CID);

        List result = query.list();
        connection.CloseConnection(s);

        return result;

   }
       
       public List ac_Teacher() {

        Session s;
        Query query;
        DbConnection connection = new DbConnection();
        s = connection.OpenConnection();

        query = s.createSQLQuery(
                "CALL getStudentPerformanceSubjectWise(:tid)")
                .setParameter("tid", SID);

        List result = query.list();
        connection.CloseConnection(s);

        return result;

   }

}  
                      
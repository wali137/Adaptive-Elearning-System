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
import org.primefaces.model.chart.LineChartSeries;
import repositery.DbConnection;

/**
 *
 * @author dell
 */
public class subwiseQuiz implements Serializable {  
  
     private CartesianChartModel categoryModel;  
  
    private CartesianChartModel linearModel;  
  
    public subwiseQuiz() {  
          
        createLinearModel();  
    }  
  
    public CartesianChartModel getCategoryModel() {  
        return categoryModel;  
    }  
  
    public CartesianChartModel getLinearModel() {  
        return linearModel;  
    }  
  
   
  
    private void createLinearModel() {  
        linearModel = new CartesianChartModel();  
        
        LineChartSeries series1 = new LineChartSeries();  
        series1.setLabel("Excellent");  
  
//        LineChartSeries series2 = new LineChartSeries();  
//        series2.setLabel("Good");  
//        
//        LineChartSeries series3 = new LineChartSeries();  
//        series3.setLabel("poor"); 
        
         List l = ac();
        
        for (Object o : l) {
            Object[] obj = (Object[]) o;

            series1.set(obj[0].toString(), Double.parseDouble(obj[1].toString()));
            
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
        }
 
        linearModel.addSeries(series1);  
//        linearModel.addSeries(series2);  
//        linearModel.addSeries(series3); 
    
       
    }
    public List ac() {

        Session s;
        Query query;
        DbConnection connection = new DbConnection();
        s = connection.OpenConnection();

        query = s.createSQLQuery(
                "QuizMarksSubjectWise(:sid)")
                .setParameter("sid", 1)
                .setParameter("cid","CS105"); 
        List result = query.list();
        connection.CloseConnection(s);

        return result;

   }

}  
                      
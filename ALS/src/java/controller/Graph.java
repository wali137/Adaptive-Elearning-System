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
public class Graph implements Serializable {  
  
     private CartesianChartModel categoryModel;  
  
    private CartesianChartModel linearModel;  
  
  
  
    public CartesianChartModel getCategoryModel() {  
        return categoryModel;  
    }  
  
    public CartesianChartModel getLinearModel() {  
        return linearModel;  
    }  
  
   
  
    public void createLinearModel() {  
        linearModel = new CartesianChartModel();  
        
        LineChartSeries series1 = new LineChartSeries();  
        series1.setLabel("Excellent");  
  
        LineChartSeries series2 = new LineChartSeries();  
        series2.setLabel("Good");  
        
        LineChartSeries series3 = new LineChartSeries();  
        series3.setLabel("poor"); 
        
         List l = ac();
        
        for (Object o : l) {
            Object[] obj = (Object[]) o;

            if (obj[1].toString().equals("excellent")) {
                series2.set(obj[0].toString(), Double.parseDouble(obj[2].toString()));
            }

            if (obj[1].toString().equals("good")) {
                series1.set(obj[0].toString(), Double.parseDouble(obj[2].toString()));
            }
             if (obj[1].toString().equals("bad")) {
                series3.set(obj[0].toString(), Double.parseDouble(obj[2].toString()));
            }
        }
 
        linearModel.addSeries(series1);  
        linearModel.addSeries(series2);  
        linearModel.addSeries(series3); 
    
       
    }
    
    int TID;

    public int getTID() {
        return TID;
    }

    public void setTID(int TID) {
        this.TID = TID;
    }

    
    public List ac() {

        Session s;
        Query query;
        DbConnection connection = new DbConnection();
        s = connection.OpenConnection();

        query = s.createSQLQuery(
                "CALL getStudentPerformanceSubjectWise(:tid)")
                .setParameter("tid", 1);

        List result = query.list();
        connection.CloseConnection(s);

        return result;

   }

}  
                      

package controller;

import java.io.Serializable;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;

public class teacherController implements Serializable{
    
    
    private CartesianChartModel linearModel;  
    private CartesianChartModel categoryModel;  
    private int c=1;

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }
  
    public teacherController() {  
        createLinearModel();  
        createCategoryModel();  
    }  
  
    public CartesianChartModel getLinearModel() {  
        return linearModel;  
    }  
  
    public CartesianChartModel getCategoryModel() {  
        return categoryModel;  
    }  
  
    private void createLinearModel() {  
        linearModel = new CartesianChartModel();  
  
  
        LineChartSeries series1 = new LineChartSeries();  
        series1.setLabel("Web Engineering");  
  
        series1.set("2009", 20);  
        series1.set("2010", 10);  
        series1.set("2011", 30);  
        series1.set("2012", 60);  
        series1.set("2013", 80);  
  
        LineChartSeries series2 = new LineChartSeries();  
        series2.setLabel("Data Structures");  
  
        series2.set("2009", 60);  
        series2.set("2010", 30);  
        series2.set("2011", 20);  
        series2.set("2012", 70);  
        series2.set("2013", 90);  
        
        LineChartSeries series3 = new LineChartSeries();  
        series3.setLabel("Database");  
  
        series3.set("2009", 20);  
        series3.set("2010", 50);  
        series3.set("2011", 20);  
        series3.set("2012", 40);  
        series3.set("2013", 50);
        
        LineChartSeries series4 = new LineChartSeries();  
        series4.setLabel("Java");  
  
        series4.set("2009", 10);  
        series4.set("2010", 10);  
        series4.set("2011", 50);  
        series4.set("2012", 90);  
        series4.set("2013", 40);
  
        linearModel.addSeries(series1);  
        linearModel.addSeries(series2); 
        linearModel.addSeries(series3); 
        linearModel.addSeries(series4); 
        
    }  
    
    public void abc()
    {
        
    }
  
    public void createCategoryModel() {  
        categoryModel = new CartesianChartModel();  
  
        // getting c from combo
        int x = getC();
        
        
        
        if(x==1)
        {
        ChartSeries a = new ChartSeries();  
        a.setLabel("Assignments");  
  
        a.set("2004", 120);  
        a.set("2005", 100);  
        a.set("2006", 44);  
        a.set("2007", 150);  
        a.set("2008", 25);  
  
        ChartSeries b = new ChartSeries();  
        b.setLabel("Quizzes");  
  
        b.set("2004", 52);  
        b.set("2005", 60);  
        b.set("2006", 110);  
        b.set("2007", 135);  
        b.set("2008", 120);  
        
        ChartSeries c = new ChartSeries();  
        c.setLabel("Lessons");  
  
        c.set("2004", 12);  
        c.set("2005", 10);  
        c.set("2006", 10);  
        c.set("2007", 15);  
        c.set("2008", 10); 
        
        categoryModel.addSeries(a);  
        categoryModel.addSeries(b);  
        categoryModel.addSeries(c);   
        }
        
        
        if(x==2)
        {
        ChartSeries a = new ChartSeries();  
        a.setLabel("Assignments");  
  
        a.set("2004", 100);  
        a.set("2005", 10);  
        a.set("2006", 64);  
        a.set("2007", 100);  
        a.set("2008", 25);  
  
        ChartSeries b = new ChartSeries();  
        b.setLabel("Quizzes");  
  
        b.set("2004", 100);  
        b.set("2005", 10);  
        b.set("2006", 10);  
        b.set("2007", 15);  
        b.set("2008", 20);  
        
        ChartSeries c = new ChartSeries();  
        c.setLabel("Lessons");  
  
        c.set("2004", 11);  
        c.set("2005", 80);  
        c.set("2006", 60);  
        c.set("2007", 45);  
        c.set("2008", 80); 
        
        categoryModel.addSeries(a);  
        categoryModel.addSeries(b);  
        categoryModel.addSeries(c);   
        }
        
        
        if(x==3)
        {
        ChartSeries a = new ChartSeries();  
        a.setLabel("Assignments");  
  
        a.set("2004", 60);  
        a.set("2005", 30);  
        a.set("2006", 14);  
        a.set("2007", 90);  
        a.set("2008", 45);  
  
        ChartSeries b = new ChartSeries();  
        b.setLabel("Quizzes");  
  
        b.set("2004", 12);  
        b.set("2005", 10);  
        b.set("2006", 10);  
        b.set("2007", 35);  
        b.set("2008", 12);  
        
        ChartSeries c = new ChartSeries();  
        c.setLabel("Lessons");  
  
        c.set("2004", 42);  
        c.set("2005", 60);  
        c.set("2006", 70);  
        c.set("2007", 85);  
        c.set("2008", 90); 
        
        categoryModel.addSeries(a);  
        categoryModel.addSeries(b);  
        categoryModel.addSeries(c);   
        }
        
        
        if(x==4)
        {
        ChartSeries a = new ChartSeries();  
        a.setLabel("Assignments");  
  
        a.set("2004", 0);  
        a.set("2005", 0);  
        a.set("2006", 4);  
        a.set("2007", 15);  
        a.set("2008", 2);  
  
        ChartSeries b = new ChartSeries();  
        b.setLabel("Quizzes");  
  
        b.set("2004", 2);  
        b.set("2005", 0);  
        b.set("2006", 0);  
        b.set("2007", 35);  
        b.set("2008", 20);  
        
        ChartSeries c = new ChartSeries();  
        c.setLabel("Lessons");  
  
        c.set("2004", 2);  
        c.set("2005", 0);  
        c.set("2006", 0);  
        c.set("2007", 5);  
        c.set("2008", 0); 
        
        categoryModel.addSeries(a);  
        categoryModel.addSeries(b);  
        categoryModel.addSeries(c);   
        }
        
        
        
    }  
    
}

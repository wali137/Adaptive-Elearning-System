/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import repositery.Course_Rep;
import repositery.performance_Repository;

/**
 *
 * @author DemiXsoft
 */
public class performance_Controller {
    
    performance_Repository pr = new performance_Repository();
    Course_Rep cr = new Course_Rep();

    public Course_Rep getCr() {
        return cr;
    }

    public void setCr(Course_Rep cr) {
        this.cr = cr;
    }
    
    
    
    double percentage;

    public double getPercentage() {
        return calculate();
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
    
    public double calculate()
    {
        //int marks = pr.getStudent_Marks();
        int total = pr.getStudent_TotalMarks();
        //double per = (marks/total);
        //per= per*100;
        return total;
    }
    
    Helper help = new Helper();
    
    public String other_calculate()
    {
        String marks = pr.getOtherStudent_Marks();
        String total = pr.getOtherStudent_TotalMarks();
        String[] mark_arr = marks.split(",");
        String[] total_arr = total.split(",");
        String percens= "";
        for (int i = 0; i<mark_arr.length;i++)
        {
            int per = (help.toInt(mark_arr[i])/help.toInt(total_arr[i]))*100;
            if(i<mark_arr.length-1)
            {
                percens = percens + per + ",";
            }else{
                percens = percens + per;
            }
        }
        return percens;
    }
    
    public List<String> getStandings()
    {
        String other = other_calculate();
        String[] percens = other.split(",");
        double mainper = calculate();
        List<String> stnd = new ArrayList<String>();
        for(int i =0; i< percens.length;i++)
        {
            stnd.add(percens[i]);
        }
        String s = String.valueOf(mainper);
        stnd.add(s);
        Collections.sort(stnd,Collections.reverseOrder());
        return stnd;
    }
    
    String course_id;

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }
    
    
    int topicTotal;
    int topicID;
    int user_id;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    

    public int getTopicID() {
        return topicID;
    }

    public void setTopicID(int topicID) {
        this.topicID = topicID;
    }
    
    String avg;

    public String getAvg() {
        return avg;
    }

    public void setAvg(String avg) {
        this.avg = avg;
    }




    public Double getTopicTotal() {
        Double percen = 0.0;
        int x = topicID;
        int y = user_id;
        String z = course_id;
        BigDecimal obt;
        BigDecimal total;
        Object o = pr.getStudent_Quiz_Topic(topicID, user_id, course_id);
        ArrayList a = (ArrayList) o;
        Object[] kuch =(Object[]) a.get(0);
        if(kuch[0] != null)
        {
            avg = "Average";
            obt = (BigDecimal) kuch[0];
            total = (BigDecimal) kuch[1];
        
            Double ooo = obt.doubleValue();
            Double ttt = total.doubleValue();
            
            percen = ooo/ttt;
            percen = percen * 100;
     
        }else{
            avg = "Below Average";
        }
        return percen;
    }

    public void setTopicTotal(int topicTotal) {
        this.topicTotal = topicTotal;
    }
    
    
}

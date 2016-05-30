/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package learners.other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import learners.classifiers.Sets;
import learners.classifiers.bayes.NaiveBayes;
import learners.data.DataColumn;
import learners.data.DataRow;
import learners.data.DataRowCollection;
import learners.data.DataSet;

/**
 *
 * @author Dv6
 */
public class Accuracy {
    
    //1 positve
    //0 negtive
    public ArrayList<Double> NeigbourhoodRoughSetAccuracy(DataSet testSet, DataRowCollection instances, double threshold)
    {
        ArrayList<Double> val = new ArrayList<>();
        double p_rate = 0;
        double fp_rate = 0;
        
        double n_rate = 0;
        double fn_rate = 0;
        int i;
        Boolean flag = false;
        for(DataRow test_instance : testSet.Rows())
        {
          i=0;
          p_rate=0;
          n_rate=0;
          flag = false;
          for(DataRow instance : instances)
          {
              double d = TupleCompatibilty(test_instance, instance);
              if(d >= threshold)
              {
                  flag = true;
                  p_rate++; //+= threshold;
                  break;
              }
//              else
//              {
//                  n_rate++;
//              }
              i++;
              
          }
          
          if(!flag)
          {
              n_rate++;
          }
          
          fp_rate += p_rate;
          fn_rate += n_rate;
         //fp_rate += (p_rate/(double)i);
         //fn_rate += (n_rate/(double)i);
        }
        val.add(fn_rate);
        val.add(fp_rate);
        return val;
    }

    
    public DataSet FindFuzzyDecision(Sets s, DataSet d)
    {
        for(DataRow row : d.Rows())
        {
            if(row.get(0).equals(s.getId().toString()))
            {
                 DataSet temp = new DataSet();
                 temp.Rows().Add(row);
                 return temp;
            }
        }
        return null;
    }
    
    
    public ArrayList<Double> NaiveBayes_NeigbourhoodRoughSetAccuracy(DataSet test, DataSet train)
    {
        
         NaiveBayes nb = new NaiveBayes();
         nb.Train(train);
        
        HashMap<Double,Double> myprediction = new HashMap<>();
        HashMap<Object,Double> mypredictiontest = new HashMap<>();
   ArrayList<String> x = new ArrayList<>();
       // ArrayList<Double> prob = new ArrayList<>();
        for(DataRow r : test.Rows())
        {  
           mypredictiontest = nb.Classify(r);
            
             for(Map.Entry<Object, Double> entry :  mypredictiontest.entrySet())
           {
              // System.out.println(r.get(0).toString() +":= "+ r.get(4).toString() +"  ::"+entry.getKey()+" : " +entry.getValue());
                //myprediction.put(Double.parseDouble(r.get(4).toString()), Double.parseDouble(entry.getKey().toString()));
               x.add(r.get(4).toString()+":"+entry.getKey().toString());
           } 
        }
       
        
        
        double p = 0;
        double n = 0;
        ArrayList<Double> val = new ArrayList<>();
         for(String entry : x)
           {
               String[] v = entry.split(":");
               
               if(Double.parseDouble(v[0]) == Double.parseDouble(v[1]))
               {
                 p++;   
               }
               else
               {
                  n++;
               }
           }
         val.add(n);
         val.add(p);
        return val;
    }
    
    
    public DataSet makeCopy(DataSet d)
    {
         DataSet temp = new DataSet("temp");
        
       
           for(DataRow r : d.Rows())
           {              
                  temp.Rows().Add(r);
           }
       
       for(DataColumn dc : d.Columns())
       temp.Columns().Add(dc);
       temp.setClassIndex(d.getClassIndex());
       
       return temp;
    }
    
    
    
    public DataSet Filter(DataSet d, DataSet filter)
    {
        try
        {
        DataSet temp;
        temp = (DataSet) d.clone();
        int i=0;
        for(DataRow r : d.Rows())
        {
            for(DataRow rr : filter.Rows())
            {
                if(rr.equals(r))
                {
                    temp.Rows().RemoveAt(i);
                }
            }
            i++;
        }
        
        return temp;
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    public double TupleCompatibilty(DataRow r1,DataRow r2)
    {
        double p = 0;
        for(int i=1;i<r1.getAlData().size()-1;i++)    
           if(r1.get(i).toString().equals(r2.get(i).toString()))
               p++;
        
         double t = r1.getAlData().size()-2;
        return Math.abs(p/t);
    }
    
}

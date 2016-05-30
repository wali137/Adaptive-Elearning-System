/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package learners.classifiers.bayes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import learners.classifiers.Classifer;
import learners.data.DataRow;
import learners.data.DataRowCollection;
import learners.data.DataSet;

/**
 *
 * @author Dv6
 */
public class NaiveBayes implements Classifer,Serializable
{
    HashMap<Object, Double> ClassProbablity;
    List<Object> AttributesProbablities;

   
    BayesFunctions bayesFunctions;
    double prediction;
    HashMap<Object, Double> ClassifiedProbablities;
    HashMap<Object, Double> finalProbablity;

    
    {
        bayesFunctions = new BayesFunctions();
        ClassifiedProbablities = new HashMap<>();
    }
    
    
     public HashMap<Object, Double> getClassProbablity() {
        return ClassProbablity;
    }

    public List<Object> getAttributesProbablities() {
        return AttributesProbablities;
    }
    

    @Override
    public HashMap<Object, Double> Classify(DataRow instance) {
  
     for(Map.Entry<Object, Integer> entry : bayesFunctions.getClassificationClass().entrySet())
            {
              prediction = 1.0;
              for(Object instance_object : instance.getItemArray())
              {
                   for(Object attribute_probablity : AttributesProbablities)
                {
                    ArrayList<Object> attribute_object = (ArrayList<Object>) attribute_probablity;
                    for(Object o : attribute_object)
                    {    
                         ArrayList<Object> attributeList = (ArrayList<Object>)o;
                     if(attributeList.get(0).equals(instance_object) && attributeList.get(2).equals(entry.getKey())) 
                        {
                            prediction *= (Double)attributeList.get(1);
                        }
                    }
                }  
                   
              }
               ClassifiedProbablities.put(entry.getKey(), prediction*ClassProbablity.get(entry.getKey()));
            }
     
        finalProbablity =  (HashMap<Object, Double>) bayesFunctions.findMax(ClassifiedProbablities);      
     
        return finalProbablity;
    }

    @Override
    public Double[] distributionForInstance(DataRowCollection instances) {
        return null;
    }

    @Override
    public Classifer Train(DataSet dataset) {

      ClassProbablity = bayesFunctions.GetClassProbablity(dataset);  
      AttributesProbablities = bayesFunctions.GetAttributeProbablity(dataset);
      return this;
    }

    @Override
    public String StringSummary() {
        String summary;
        
        
        
        summary = ("\n\nClass Probablites\n===========================\n");
      
      HashMap<Object,Double> ll = getClassProbablity();
      
     
       for(Map.Entry<Object, Double> entry : ll.entrySet())
        {
               summary += ("Class:"+entry.getKey()+"   Probablity:"+entry.getValue()+"\n");
        }
  
  
  
    summary += ("\n\nAttruibute Probablites\n=========================\n");
  
 List<Object> l = getAttributesProbablities();
  
  for(Object o : l)
  {
      List<Object> x = (ArrayList<Object>) o;
      for(Object cx : x)
      {
          List<Object> y = (ArrayList<Object>) cx;
          summary += ("("+y.get(0)+" = "+y.get(1)+")   Class: "+y.get(2)+"\n");
      }
         summary += "\n";
  }
     summary += "\n";
     
   return summary;
   
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package learners.classifiers.bayes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import learners.data.DataRow;
import learners.data.DataSet;

/**
 *
 * @author Dv6
 */
public class BayesFunctions {
    
    HashMap<Object, Integer> Class;

   
    HashMap<Object, Double> ClassProbablity;

    public HashMap<Object, Double> getClassProb() {
        return ClassProbablity;
    }
    List<Object> class_column;
   
     public HashMap<Object, Integer> getClassificationClass() {
        return Class;
    }
    
    
    public HashMap<Object, Double> GetClassProbablity(DataSet dataset)
    {
        class_column = dataset.getColumnList(dataset.getClassIndex());  
        Class = CategorialStats(class_column);
        ClassProbablity = new HashMap<>();
        
        for(Entry<Object, Integer> entry : Class.entrySet())
        {
          Object key = entry.getKey();
          double val =  Double.parseDouble(entry.getValue().toString());
          double total =  dataset.Rows().getCount();
          Double probablity = (val/total);
          ClassProbablity.put(key, probablity);          
        }       
        
        return ClassProbablity;
    }
    
    
    
    public List<Object> GetAttributeProbablity(DataSet dataset)
    {
        List<Object> attributeProbablityList = new ArrayList<>();


        for(int c=0;c<dataset.Columns().getCount();c++)
        {
            if(c != dataset.getClassIndex())
            {
               List<Object> col = dataset.getColumnList(c);  
               List<Object> column =  DistinctFilter(col); //dataset.getColumnList(c);
               attributeProbablityList.add(GetAttributeClassProbablity(column,c,dataset));
            }
        }      
      
        return attributeProbablityList;
    }
    
     
        
        
    public List<Object> GetAttributeClassProbablity(Collection attribute,Integer ColumnNumber,DataSet d){
      
        
 double frequency = 0;
 Boolean flag = false;
 
 List<Object> obj;
 List<Object> attributeProbList = new ArrayList<>();
 
       for(Object val : attribute)
       {
           for(Entry<Object, Integer> entry : Class.entrySet())
           {

                frequency = 0;
                flag = false;
                obj = new ArrayList<>();
                for(DataRow r : d.Rows())
                {
                    if(r.getItemArray()[ColumnNumber].equals(val) && r.getItemArray()[d.getClassIndex()].equals(entry.getKey()))
                       {
                         frequency++;  
                         flag = true;
                       }
                }
                
                if(flag == true)
                {
                obj.add(val);
                obj.add(frequency/entry.getValue().doubleValue());
                obj.add(entry.getKey());
                attributeProbList.add(obj);
                //flag = false;
                }
                else
                {

                obj.add(val);
                obj.add(frequency/entry.getValue().doubleValue());
                obj.add(entry.getKey());
                attributeProbList.add(obj);
                }
           }
       
       }    
        return attributeProbList;
    }
    
    
    
    public HashMap<Object, Integer> CategorialStats(Collection attribute){
      
        
        
       HashMap<Object, Integer> obj = new HashMap<>();

       for(Object word : attribute)
       {
        int countWord = 0;
        if(!obj.containsKey(word)){
            obj.put(word, 1); 
        }else{
            countWord = obj.get(word) + 1; 
            obj.remove(word); 
            obj.put(word, countWord);
        }
       }
        return obj;
    }

     
    public List<Object> DistinctFilter(Collection attribute){
      
        
        List<Object> obj =  new ArrayList<>();

       for(Object word : attribute)
       {
        if(!obj.contains(word)){ 
            obj.add(word);
        }
       }
        return obj; 
    }
    
    public Map<Object, Double> findMax(Map<Object, Double> map)
    {
        Double max = Double.MIN_VALUE;
        HashMap<Object, Double> temp = null;
    for(Object key: map.keySet()) {
        Double tmp = map.get(key);
        if(tmp.compareTo(max) > 0) {
            temp = new HashMap<>();
            max = tmp;
            temp.put(key, max);
        }
//        else
//        {
//            temp = new HashMap<>();
//            temp.put(key, 0.0);
//        }
    }
    
    if(temp == null)
    {
        temp = new HashMap<>();
        temp.put("Cannot Classify", 0.0);
    }
   
    return temp;
    }
    
}

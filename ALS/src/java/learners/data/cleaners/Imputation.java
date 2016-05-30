/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package learners.data.cleaners;


import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import learners.data.DataSet;
import learners.functions.StatisticalFunctions;

/**
 *
 * @author Dv6
 */
public class Imputation {
  
    
    
    public DataSet IgnoreTuples(DataSet instances, int threshold)
    {
        Integer missing_threshold_count = 0;
       
        for(int i=0;i<instances.Rows().getCount();i++)
        {
            List<Object> row = instances.getRowList(i);
            
            for(Object obj : row)
            {
                if(obj == null)
                {
                    missing_threshold_count++;
                }
            }
            
            
            if(missing_threshold_count > threshold)
            {
                instances.Rows().RemoveAt(i);
            }
            
        }
        
        
        return instances;
    }
    
    
    
    public DataSet Impute(DataSet instances)
    {
        
        for(int i=0;i<instances.Rows().getCount();i++)
        {
            List<Object> row = instances.getRowList(i);
            
            for(int j=0;j<row.size();j++)
            {
                if(row.get(j) == null)
                {
                     if (instances.Columns().get(j).getDataType() == String.class)
                     {
                         row.set(j,MostOccured(instances.getColumnList(j)));
                     }
                     else
                     {
                      
                        List<Object> ff = instances.getColumnList(j);
                        Double[] x = ConvertToArray(ff);
                         
                         Double m = StatisticalFunctions.mean(x);
                         
                         row.set(j,m); 
                     }
                }
            }
              
        }
        
        return instances;
    }
    
    
    public Double[] ConvertToArray(List<Object> list)
    {
         Double[] d = new Double[list.size()];
        for(int i=0;i<list.size();i++)
        {
            if(!"".equals(list.get(i).toString()))
            {
           d[i] = (double)list.get(i); 
            }
            else
            {
                d[i] = null;
            }
                    
        }
        return d;
    }
    
    public String MostOccured(List<?> attribute)
    {
      Set<Object> unique = new HashSet<>(attribute);
      Map map = null;
      
     for(Object o : unique)
     {
         map = new HashMap<>();
         map.put(o,Collections.frequency(attribute, o));
         map = findMax(map);
     }
     
       for(Object key: map.keySet())
       {
           return key.toString();
       }
       
       return "";
    }
    
    
     public Map<Object, Integer> findMax(Map<Object, Integer> map)
    {
        Integer max = Integer.MIN_VALUE;
        HashMap<Object, Integer> temp = null;
    for(Object key: map.keySet()) {
        Integer tmp = map.get(key);
        if(tmp.compareTo(max) > 0) {
            temp = new HashMap<>();
            max = tmp;
            temp.put(key, max);
        }
    }
    
    return temp;
    }
}

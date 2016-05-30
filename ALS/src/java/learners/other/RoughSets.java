/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package learners.classifiers;

import learners.data.DataColumn;
import learners.data.DataRow;
import learners.data.DataSet;

/**
 *
 * @author dell
 */
public class RoughSets {
   
    
    public DataSet ApplyRoughSets(DataSet dataset){
        
        ReduceRedundantData(dataset);
        return null;
    }
    
    private DataSet ReduceRedundantData(DataSet dataset){
        
        DataSet ReducedSet = new DataSet("ReducedSet"); 
        int c=0,h=0;
        int superCount=0;
        int count=0;
             for(DataRow datarow : dataset.Rows()){
               
               if(ReducedSet.Rows().getCount() < 1 ){
                   
                   ReducedSet.Rows().Add(datarow);h++;
               }
               else{
                   
               for(DataRow dr : ReducedSet.Rows()){
                
                   for(int i=1;i<dataset.Columns().getCount();i++){
            
                        if(datarow.get(i).equals(dr.get(i))){
                           
                            count+=1;
                        
                        }
                    
                    if(count < dataset.Columns().getCount()-1){
                            superCount +=1;
                    }
                     count = 0 ;
                }
               }//dr loop break
               
                if(superCount < 1){
                    c++;    
                    ReducedSet.Rows().Add(datarow);
                }
                superCount=0;
               
           
           
           
               }
        }
             
             
             for(DataColumn dc:dataset.Columns()){
                  
                        ReducedSet.Columns().Add(dc);
                }
                 
             ReducedSet.setClassIndex(dataset.getClassIndex());
             System.out.println(c+"-"+h);
        return ReducedSet;
    }
    
}

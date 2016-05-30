/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package learners.other;

import java.util.ArrayList;
import java.util.List;
import learners.classifiers.Sets;
import learners.data.*;
/**
 *
 * @author dell
 */
public class FuzzyLogic {

    private List<List<Sets>> FuzzySets;
    
    public FuzzyLogic(){
        
        FuzzySets = new ArrayList<>();
    }
    
    public Sets FuzzyLogic(DataSet dataset){
      
        ArrayList<String> RowId = new ArrayList<>();
        ArrayList<Sets> Set = new ArrayList<>();
        int flag = 0;
        int id=0;
        for(Object column : dataset.getColumnList(0)){
            RowId.add(column.toString());
        }
        
        for(DataColumn col : dataset.Columns()){
            
            if(flag == 0 ){//|| dataset.Columns().get(4).getColumnName().equals(col.toString())){
                flag = 1;
                continue;
                
            }
            
            for(Object column : dataset.getColumnList(col.getColumnName())){
             
                Set.add(new Sets( RowId.get(id),GhausianFunction(Double.parseDouble(column.toString()))));
                System.out.print( " " + RowId.get(id++) + ":" + GhausianFunction(Double.parseDouble(column.toString())));
            
            }
            id = 0;
            FuzzySets.add(Set);
            Set = new ArrayList<>();
//            for(int i=0;i<Set.size();i++){
//            
//                Set.remove(i);
//            }
            System.out.println();
          
        }
        System.out.println();
        
       // System.out.println("Decision is " + TakeDecision(FuzzySets).getId());
        return TakeDecision(FuzzySets);
    }
     
    private Sets TakeDecision(List<List<Sets>> FuzzySet){
        
        ArrayList<Sets> setarray = new ArrayList<>();
        Sets minset = null;
//        FuzzySet.
        double min =2.0;
        Sets set;
        for(int i=0;i<FuzzySet.get(0).size();i++){
         
            for(int j=0;j<FuzzySet.size();j++){
                
                set = FuzzySet.get(j).get(i);
                if(set.getValue() < min){
                    
                    min = set.getValue();
                    minset = set; 
                }
            }
        //    System.out.println("ID : " + minset.getId() + " val " + min);
            setarray.add(minset);
            min = 2.0;
        }
        //for(Sets set : FuzzySet.get(0).get(0)){
            
            
            
        //}
        
        
        return GetMinimumFunction(setarray);
    }
    
    private Sets GetMinimumFunction(ArrayList<Sets> SelectedSets){
        
        double max;
        Sets Set = null;
        max = 0.0;
        
        for(Sets objset : SelectedSets){
            
            System.out.println("{"+objset.getId()+ ","+objset.getValue()+"}");
        
        }
        
        for(Sets objset : SelectedSets){
            
            if(objset.getValue() > max){
                
                Set = objset;
                max = objset.getValue();
            }
        }
        return Set;
    }
    
    private double GhausianFunction(double val){
        
        return 1/(1 + Math.exp(val));
    }
}

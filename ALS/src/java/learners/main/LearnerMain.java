/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package learners.main;

import java.io.Serializable;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import learners.classifiers.bayes.BayesFunctions;
import learners.classifiers.Classifer;
import learners.classifiers.bayes.NaiveBayes;
import learners.classifiers.svm.EvalMeasures;
import learners.classifiers.svm.FeatureNode;
import learners.classifiers.svm.KernelParams;
import learners.classifiers.svm.Parser;
import learners.classifiers.svm.SVM;
import learners.data.DataColumn;
import learners.data.DataRow;
import learners.data.DataRowCollection;
import learners.data.DataSet;
import learners.data.DataSetLoader;
import learners.data.cleaners.Imputation;
import learners.functions.StatisticalFunctions;
/**
 *
 * @author Dv6
 */
public class LearnerMain {


    public static double eulerDistance(double x1,double x2,double x3,double y1,double y2,double y3)
    {
       return Math.sqrt(StatisticalFunctions.sqr(x1-y1)+StatisticalFunctions.sqr(x2-y2)+StatisticalFunctions.sqr(x3-y3));
    }
    
    
    
     public static double eulerDistance2(double x1,double x2,double y1,double y2)
    {
       return Math.sqrt(StatisticalFunctions.sqr(x1-y1)+StatisticalFunctions.sqr(x2-y2));
    }
    public void NeighbourRoughSet(DataSet d,double threshold)
    {
        
        ArrayList<String> Dy = new ArrayList<>();
        ArrayList<String> Dn = new ArrayList<>();
        
        ArrayList<String> yLower = new ArrayList<>();
        ArrayList<String> nLower = new ArrayList<>();
       
         ArrayList<String> yUpper = new ArrayList<>();
         ArrayList<String> nUpper = new ArrayList<>();
        
        boolean flag = false;   
        HashMap<String,ArrayList<String>> neigbour = new HashMap<>();
        ArrayList<String> temp;
        
       // double threshold = 0.1;
        
        
        
        
        
        
        
        
     
        for(int i=0;i<d.Rows().getCount();i++)
        {
          List<Object> l = d.getRowList(i);
          
          
          if(Double.parseDouble(l.get(4).toString()) == 0.2)
          {
              Dy.add(l.get(0).toString());
          }
          else
          {
              Dn.add(l.get(0).toString());
          }
          
            temp = new ArrayList<>();
                
                ArrayList<Object> obj = (ArrayList<Object>) l;
                System.out.print(obj.get(0)+" = {");
               for(int j=0;j<d.Rows().getCount();j++)
                {   
                   
                    Double x1 = Double.parseDouble(obj.get(1).toString());
                    Double x2 = Double.parseDouble(obj.get(2).toString());
                    Double x3 = Double.parseDouble(obj.get(3).toString());
                    
                     Double y1 = Double.parseDouble(d.getRowList(j).get(1).toString());
                     Double y2 = Double.parseDouble(d.getRowList(j).get(2).toString());
                     Double y3 = Double.parseDouble(d.getRowList(j).get(3).toString());
                    double dx = eulerDistance((double)x1, (double)x2,(double)x3, (double)y1, (double)y2, (double)y3);
                   if(dx <= threshold) 
                   {
                       System.out.print( d.getRowList(j).get(0).toString()+",");
                       temp.add(d.getRowList(j).get(0).toString());
                   }
                    
                } 
               System.out.print("\b");
               System.out.print("}");
               System.out.println();
               
               neigbour.put(l.get(0).toString(),temp);
               
            }
        
         
            System.out.println();
            
            
            
            
        Boolean f1 = false;
      for(Map.Entry<String, ArrayList<String>> entry : neigbour.entrySet())
      {
          ArrayList<String> list = entry.getValue();
      
          //lower bound No
              if(isSubsetClause(list, Dn))
              {
                nLower.add(entry.getKey());
              }
              
              
           //lower bound Yes  
              if(isSubsetClause(list, Dy))
              {
                 yLower.add(entry.getKey());
              }

           //upper bound Yes
              if(IntersectionClause(list, Dy))
              {
                  yUpper.add(entry.getKey());
              }
            
           //upper bound No
              if(IntersectionClause(list, Dn))
              {
                  nUpper.add(entry.getKey());
              }

      }
      
      
      flag = false;
      System.out.print("\n\nNegative Lower Bound: {");
      for(String c1 : nLower)
      {     
          flag = true;
          System.out.print(c1+",");
      }
      if(flag)
      System.out.print("\b");
      System.out.print("}");
      System.out.println();
      
      
      
      flag = false;
      System.out.print("\n\nNegative Upper Bound: {");
      for(String c1 : nUpper)
      {
          flag = true;
          System.out.print(c1+",");
      }
      if(flag)
      System.out.print("\b");
      System.out.print("}");
      System.out.println();
      
      
      
      flag = false;
      System.out.print("\n\nPositove Lower Bound: {");
      for(String c1 : yLower)
      {
          flag = true;
          System.out.print(c1+",");
      }
      if(flag)
      System.out.print("\b");
      System.out.print("}");
      System.out.println();
      
      
      flag = false;
      System.out.print("\n\nPositive Upper Bound: {");
      for(String c1 : yUpper)
      {
          flag = true;
          System.out.print(c1+",");
      }
      if(flag)
      System.out.print("\b");
      System.out.print("}");
      System.out.println();
        
        
    }
    
    public void NeighbourRoughSet2(DataSet d,double threshold)
    {
        
        ArrayList<String> Dy = new ArrayList<>();
        ArrayList<String> Dn = new ArrayList<>();
        
        ArrayList<String> yLower = new ArrayList<>();
        ArrayList<String> nLower = new ArrayList<>();
       
         ArrayList<String> yUpper = new ArrayList<>();
         ArrayList<String> nUpper = new ArrayList<>();
        
        boolean flag = false;   
        HashMap<String,ArrayList<String>> neigbour = new HashMap<>();
        
       // ArrayList<ArrayList<String>> neigbour = new ArrayList<>();
        ArrayList<String> temp;
        //double threshold = 0.15;
        
        for(int i=0;i<d.Rows().getCount();i++)
        {
          List<Object> l = d.getRowList(i);
          
          if(l.get(3).equals("Y"))
          {
              Dy.add(l.get(0).toString());
          }
          else
          {
              Dn.add(l.get(0).toString());
          }
          
            
                
                
                
                temp = new ArrayList<>();
                ArrayList<Object> obj = (ArrayList<Object>) l;
                System.out.print(obj.get(0)+" = {");
               for(int j=0;j<d.Rows().getCount();j++)
                {   
                   
                    Double x1 = Double.parseDouble(obj.get(1).toString());
                    Double x2 = Double.parseDouble(obj.get(2).toString());
                   // Double x3 = Double.parseDouble(obj.get(3).toString());
                    
                     Double y1 = Double.parseDouble(d.getRowList(j).get(1).toString());
                     Double y2 = Double.parseDouble(d.getRowList(j).get(2).toString());
                    // Double y3 = Double.parseDouble(d.getRowList(j).get(3).toString());
                    double dx = eulerDistance2((double)x1, (double)x2,(double)y1, (double)y2);
                 
                  if(dx <= threshold) 
                   {
                       System.out.print( d.getRowList(j).get(0).toString()+",");
                       temp.add(d.getRowList(j).get(0).toString());
                   }
                    
                } 
               System.out.print("\b");
               System.out.print("}");
               System.out.println();
               
               neigbour.put(l.get(0).toString(),temp);
            
            System.out.println();
        }
        
        
        
        
        
       
        
        Boolean f1 = false;
      for(Map.Entry<String, ArrayList<String>> entry : neigbour.entrySet())
      {
          ArrayList<String> list = entry.getValue();
      
          //lower bound No
              if(isSubsetClause(list, Dn))
              {
                nLower.add(entry.getKey());
              }
              
              
           //lower bound Yes  
              if(isSubsetClause(list, Dy))
              {
                 yLower.add(entry.getKey());
              }

           //upper bound Yes
              if(IntersectionClause(list, Dy))
              {
                  yUpper.add(entry.getKey());
              }
            
           //upper bound No
              if(IntersectionClause(list, Dn))
              {
                  nUpper.add(entry.getKey());
              }

      }
      
      
      flag = false;
      System.out.print("\n\nNegative Lower Bound: {");
      for(String c1 : nLower)
      {     
          flag = true;
          System.out.print(c1+",");
      }
      if(flag)
      System.out.print("\b");
      System.out.print("}");
      System.out.println();
      
      
      
      flag = false;
      System.out.print("\n\nNegative Upper Bound: {");
      for(String c1 : nUpper)
      {
          flag = true;
          System.out.print(c1+",");
      }
      if(flag)
      System.out.print("\b");
      System.out.print("}");
      System.out.println();
      
      
      
      flag = false;
      System.out.print("\n\nPositove Lower Bound: {");
      for(String c1 : yLower)
      {
          flag = true;
          System.out.print(c1+",");
      }
      if(flag)
      System.out.print("\b");
      System.out.print("}");
      System.out.println();
      
      
      flag = false;
      System.out.print("\n\nPositive Upper Bound: {");
      for(String c1 : yUpper)
      {
          flag = true;
          System.out.print(c1+",");
      }
      if(flag)
      System.out.print("\b");
      System.out.print("}");
      System.out.println();
    }
    
    
    public Boolean IntersectionClause(ArrayList<String> sub,ArrayList<String> subS)
    {
   
          ArrayList<String> sx = (ArrayList<String>) sub.clone();
   
       
        for(String s : sub)
        {
        
            {
              if(subS.contains(s))
              {
                 
              }
              else
              {
                  sx.remove(s);
              }
              
            }
          
        }

        if(sx.size() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
        
    }
    
    
    public Boolean isSubsetClause(ArrayList<String> sub,ArrayList<String> subS)
    {
        Boolean flag = false;
       
        for(String s : sub)
        {
           
                if (subS.contains(s))
                {
                    flag = true;
                }
                else
                {
                    flag = false;
                    break;
                }
          
        }
        return flag;
    }
    
    
    public static DataSet extract(ArrayList<String> list, DataSet d)
    {
     
        DataSet temp = new DataSet("temp");
      
       
        
       for(String s : list)
       {
           for(DataRow r : d.Rows())
           {              
               if(r.get(0).toString().equals(s))
                {
                  System.out.println();
                  temp.Rows().Add(r);
                
             
                          
                }   
           }
       }
       
       for(DataColumn dc : d.Columns())
       temp.Columns().Add(dc);
       temp.setClassIndex(d.getClassIndex());
       
       return temp;
    }
    
    public static void main(String[] args) {
                
       // jade.Boot.main(new String[] {"-platform win"});
       // jade.Boot3.main(new String[] {"-gui","-platform win"});
        StringBuffer x;
        StringBuilder y;        //Loading
        DataSet d= new DataSet("hello");
        DataSetLoader dl = new DataSetLoader();
        DataSetLoader.setDATABASE_URL("jdbc:mysql://localhost:3306/dbhkms");
        //List<Object> o = new ArrayList<>();
        //o.add(2);
        //o.add("pakistan");
    //    d = dl.DatasetLoader("select Mutation_type,Tumer_genotype,Mutation,CDS_Mutation,Gene_Name,Cancer_type from geneSequencing where id > 33 and id < 44",d);
        
        
        DataSet test = new DataSet("hello");
       //  d = dl.DatasetLoader("select Chromosome,Chr_start,chr_end,Mutation_type,Tumer_genotype,Mutation,CDS_Mutation,Gene_Name,Cancer_type from geneSequencing WHERE id <35 OR id >45",d);
         // test = dl.DatasetLoader("select Chromosome,Chr_start,chr_end,Mutation_type,Tumer_genotype,Mutation,CDS_Mutation,Gene_Name,Cancer_type from geneSequencing WHERE id >=35 AND id <=45",test);
    
         d = dl.DatasetLoader("SELECT `Cellular dyshesion`, `Intracytoplasmic_lumina`, `Three_dimensional_epithelial_cell_clusters`, `Bipolar_naked_nuclei`, `Foamy_macrophages`, `Nucleoli`, `Nuclear_pleiomorphism`, `Nuclear_Size`, `Necrotic_epithelial_cells`, `Apocrine_change`, `Outcome` FROM biopsy", d);
         test = dl.DatasetLoader("SELECT `Cellular dyshesion`, `Intracytoplasmic_lumina`, `Three_dimensional_epithelial_cell_clusters`, `Bipolar_naked_nuclei`, `Foamy_macrophages`, `Nucleoli`, `Nuclear_pleiomorphism`, `Nuclear_Size`, `Necrotic_epithelial_cells`, `Apocrine_change`, `Outcome` FROM biopsy", test);
          
          
          // d = dl.DatasetLoader("select * from weatherdata",d);
        
//        DataSet dd = null;// = d;
//        try {
//           dd = (DataSet) d.clone();
//        } catch (Exception ex) 
//        {
//            Logger.getLogger(LearnerMain.class.getName()).log(Level.SEVERE, null, ex);
//        }      
// //  dd= null;
//   dd.Rows().RemoveAt(0);
        //ArrayList<String> s = new ArrayList<>();
        //s.add("P1");
        
        //extract(s,d);
       
     //    d.Columns().RemoveAt(0);
   //     d.Columns().RemoveAt(d.getClassIndex()-2);
     
        
    //  d = dl.StoredProcedure_DatasetLoader("{call CONT(?,?)}",o,d);
    //  System.out.println();
    //  d = dl.DatasetLoader("Select * from naivetest",d);
        
//////       LearnerMain lm = new LearnerMain();
//////       double threshold = 0.1;
//////      // System.out.println("Using Euclidean Measure"+"\n");
//////     //  System.out.println("Threshold: "+threshold+"\n");
//////       
//////      SVM sx = new SVM();
//////      sx.setKernel(new KernelParams(3,1,1,1));
//////      Classifer c = sx.Train(d);
//////      
//////      for(String[] s : sx.testInstances())
//////      {
//////         HashMap<Object,Double> ans = sx.Classify(new DataRow(s));
//////        
//////    for(Map.Entry<Object, Double> entry : ans.entrySet())
//////    {
//////        DataRow rr = (DataRow)entry.getKey();
//////        for(Object o : rr.getItemArray())
//////        {
//////            String[] x = o.toString().split(":");
//////            System.out.print(x[0]+","); 
//////        }
//////        System.out.print("\b");
//////        System.out.print(" := " +(entry.getValue()));
//////        System.out.println();
//////    } 
//////
//////      }
      
      //Problem p = new Parser();
     // p.loadBinaryProblem(d);  
     // p.loadBinaryProblem("D:\\data.txt");
     // sx.svmTrain(p,new KernelParams(3,1,1,1));
      
      
     // FeatureNode[] xx = p.parseRow(new String[] {"?","1:1","1:1"} );
     //FeatureNode[] xx = p.parseRow(p.test.get(3));
   //   System.out.println("Result0: "+p.catmap.getOldCategoryOf((int)sx.svmTestOne(xx)));
   //System.out.println("Result: "+Math.ceil(sx.svmTestOne(xx)));
      
//   for(String[] ar : c.test)
//   {
//     System.out.println("Result: "+(sx.svmTestOne(p.parseRow(ar))));
//   }
// 
   
//   EvalMeasures em = new EvalMeasures(p,p.y, p.y.length);
//   System.out.println("Accuracy: "+em.Accuracy());
//   
     
      
   //    lm.NeighbourRoughSet2(d,threshold);
     // lm.NeighbourRoughSet(d,threshold);
      
      
            
    
       
        
        

//Imputation im = new Imputation();
        
    
  //   d = im.IgnoreTuples(d, 2);
                
        
  //   d = im.Impute(d); 
        
        
        
        
        
       // Object[] inst = new Object[] {"9","normal","low","high","low","low","yes","yes","no","yes","yes","no","yes","no","no","yes"};
        
        
   NaiveBayes nb = new NaiveBayes();
   Classifer c = nb.Train(d);
  
   System.out.println(c.StringSummary());


   
   
   
//   Object[] inst = new Object[] {"single base substitution ","A/T","A>T","c.1992A>T","p.Q664H","AKAP8"};
//   Object[] inst2 = new Object[] {"single base substitution","G/C","G>C","c.1309G>C","p.D437H","MIA2"};
 //  Object[] inst3 = new Object[] {"single base substitution","C/A","C>A","c.1898C>A","p.P633H","AMPD1"};
  // Object[] inst4 = new Object[] {"single base substitution","C/T","C>T","c.355C>T","p.Q119X","GUCY1A2"};
   
   ArrayList<Object[]> ar = new ArrayList<>();
 
//   ar.add(inst);
//   ar.add(inst2);
//   ar.add(inst3);
//   ar.add(inst4);
    
   for(int i=0;i<=10;i++) {
            ar.add(test.Rows().get(i).getItemArray());
        }
   //ar.add(new Object[] {"rain","hot","high","false"});
  System.out.println("\n\nFinal Prediction Probablites\n=========================\n");
  
  
  for(Object[] o : ar)
  {
   HashMap<Object,Double> prediction = nb.Classify(new DataRow(o));
    System.out.print("Instance: ");
   for(Object oo : o)
   {
        System.out.print(oo.toString()+",");
   }
    System.out.print("\b");
    System.out.println();
     System.out.println("\nPrediction:  ");
   for(Map.Entry<Object, Double> entry : prediction.entrySet())
   {
       System.out.println(entry.getKey()+" : " +entry.getValue()+"\n\n");
   }
  }
   
   
 
   
   
   
   
   
   
   
   
   
   
   
   
   //DEPRECEATE
   
//  
//  
//  
//  
//        System.out.println("\nData To Train\n===========================\n");
//
//  
//        for(DataRow r : d.Rows())
//        {
//           for(Object s : r.getItemArray())
//      {
//          System.out.print(s.toString()+",");
//      } 
//           System.out.println();
//        }
//  
//  
//      System.out.println("\nInstance To Classify\n===========================\n");
//      for(Object s : inst)
//      {
//          System.out.print(s.toString()+",");
//      }
//  
//  
//      System.out.println("\n\nClass Probablites\n===========================\n");
//      
//      HashMap<Object,Double> ll = nb.getClassProbablity();
//      
//     
//       for(Map.Entry<Object, Double> entry : ll.entrySet())
//        {
//               System.out.println("Class:"+entry.getKey()+"   Probablity:"+entry.getValue());
//        }
//  
//  
//  
//    System.out.println("\n\nAttruibute Probablites\n=========================\n");
//  
// List<Object> l = nb.getAttributesProbablities();
//  
//  for(Object o : l)
//  {
//      List<Object> x = (ArrayList<Object>) o;
//      for(Object cx : x)
//      {
//          List<Object> y = (ArrayList<Object>) cx;
//          System.out.println("("+y.get(0)+" = "+y.get(1)+")   Class: "+y.get(2));
//      }
//         System.out.println();
//  }
//     System.out.println();

 // Serializable i = (Serializable) c;
  
  
   
  
  
//   System.out.println("\n\nFinal Prediction Probablites\n=========================\n");
//  
//   HashMap<Object,Double> prediction = nb.Classify(new DataRow(inst));
//     
//   for(Map.Entry<Object, Double> entry : prediction.entrySet())
//   {
//       System.out.println(entry.getKey()+" : " +entry.getValue());
//   }
      
   
       
      
   
   
    }

   
    

    
    
    
}

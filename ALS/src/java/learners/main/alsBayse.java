/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package learners.main;

/**
 *
 * @author DemiXsoft
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import learners.classifiers.Classifer;
import learners.classifiers.bayes.NaiveBayes;
import learners.classifiers.neural.NeuralNetwork;
import learners.classifiers.neural.Neuron;
import learners.data.DataRow;
import learners.data.DataSet;
import learners.data.DataSetLoader;
import learners.data.cleaners.Imputation;
import learners.functions.StatisticalFunctions;
import learners.other.RNF;
/**
 *
 * @author Dv6
 */
public class alsBayse {

    public static Classifer classifer;

    public static Classifer getClassifer() {
        return classifer;
    }

    public static void setClassifer(Classifer classifer) {
        alsBayse.classifer = classifer;
    }

    public static double eulerDistance(double x1,double x2,double x3,double y1,double y2,double y3)
    {
       return Math.sqrt(StatisticalFunctions.sqr(x1-y1)+StatisticalFunctions.sqr(x2-y2)+StatisticalFunctions.sqr(x3-y3));
    }
    
    
    
     public static double eulerDistance2(double x1,double x2,double y1,double y2)
    {
       return Math.sqrt(StatisticalFunctions.sqr(x1-y1)+StatisticalFunctions.sqr(x2-y2));
    }
    public void NeighbourRoughSet(DataSet d)
    {
        
        double threshold = 0.1;
        
        for(int i=0;i<d.Rows().getCount();i++)
        {
          List<Object> l = d.getRowList(i);
            {
                
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
                   }
                    
                } 
               System.out.print("\b");
               System.out.print("}");
               System.out.println();
            }
            System.out.println();
        }
        
    }
    
    public void NeighbourRoughSet2(DataSet d,double threshold)
    {
        
        ArrayList<String> Dy = new ArrayList<>();
        ArrayList<String> Dn = new ArrayList<>();
        
        ArrayList<String> yLower = new ArrayList<>();
        ArrayList<String> nLower = new ArrayList<>();
        
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
        
        //upper bound Yes
        
        //upper bound No
        
        //lower bound Yes  
        //lower bound No
        Boolean f1 = false;
      for(Map.Entry<String, ArrayList<String>> entry : neigbour.entrySet())
      {
          ArrayList<String> list = entry.getValue();
      
              if(isSubset(list, Dy))
              {
                yLower.add(entry.getKey());
              }
        
      }
        
        
        
        System.out.println();
    }
    
    
    public Boolean isSubset(ArrayList<String> sub,ArrayList<String> subS)
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
    
    public static void main(String[] args) {
                
       // jade.Boot.main(new String[] {"-platform win"});
       // jade.Boot3.main(new String[] {"-gui","-platform win"});
        
        //Loading
//        DataSet d= new DataSet("hello");
//        DataSetLoader dl = new DataSetLoader();
//        DataSetLoader.setDATABASE_URL("jdbc:mysql://localhost:3306/wekatest");
//        //List<Object> o = new ArrayList<>();
//        //o.add(2);
//        //o.add("pakistan");
//        d = dl.DatasetLoader("Select * from rough2",d);
   //    NeuralNetwork nn = new NeuralNetwork();
      //  nn.createFeedForwordNetwork();
        
   
     //    d.Columns().RemoveAt(0);
   //     d.Columns().RemoveAt(d.getClassIndex()-2);
       
        
    //  d = dl.StoredProcedure_DatasetLoader("{call CONT(?,?)}",o,d);
    //  System.out.println();
    //  d = dl.DatasetLoader("Select * from naivetest",d);
        
//       LearnerMain lm = new LearnerMain();
//       double threshold = 0.15;
//       System.out.println("Using Euclidean Measure"+"\n");
//       System.out.println("Threshold: "+threshold+"\n");
//       
//       
//      
//       lm.NeighbourRoughSet2(d,threshold);
        
        
        
        
        
        
        

//Imputation im = new Imputation();
//        
//    
//  //   d = im.IgnoreTuples(d, 2);
//                
//        
//     d = im.Impute(d); 
//        
//        
        
        
        
//        Object[] inst = new Object[] {"9","normal","low","high","low","low","yes","yes","no","yes","yes","no","yes","no","no","yes"};
//        
//        
//   NaiveBayes nb = new NaiveBayes();
//   Classifer c = nb.Train(d);
//  
//   System.out.println(c.StringSummary());
//
////  
//  nb.Classify(new DataRow(inst));  
//  
//  System.out.println("\n\nFinal Prediction Probablites\n=========================\n");
//  
//   HashMap<Object,Double> prediction = nb.Classify(new DataRow(inst));
//     
//   for(Map.Entry<Object, Double> entry : prediction.entrySet())
//   {
//       System.out.println(entry.getKey()+" : " +entry.getValue());
//   }
       
//       
//  
//   DataSet d = new DataSet("mydataset");
//   DataSetLoader dl = new DataSetLoader();
//   DataSetLoader.setDATABASE_URL("jdbc:mysql://localhost:3306/als"); 
//   d = dl.DatasetLoader("SELECT CASE WHEN t2.assignmentPerc > 90 THEN 'Excellent' WHEN t2.assignmentPerc < 90 AND assignmentPerc > 75 THEN 'Good' WHEN t2.assignmentPerc < 75 AND assignmentPerc > 65 THEN 'Average' WHEN t2.assignmentPerc < 60 THEN 'Below Average' END AssignmentPerformance,CASE WHEN t1.quizPerc > 90 THEN 'Excellent' WHEN t1.quizPerc < 90 AND quizPerc > 75 THEN 'Good' WHEN t1.quizPerc < 75 AND quizPerc > 60 THEN 'Average' WHEN t1.quizPerc < 60 THEN 'Below Average' END QuizPerformance,student.ranking FROM (SELECT student.student_id ,COUNT(*) AS total_quizes,(SUM(marks_obtained)/SUM(totak_marks)) * 100 AS quizPerc FROM student INNER JOIN quiz_submission ON quiz_submission.student_id = student.student_id INNER JOIN quiz_result ON quiz_result.quiz_submission_id = quiz_submission.quiz_submission_id GROUP BY student.student_id ) t1 INNER JOIN (SELECT student_registration.student_id,COUNT(*) AS total_assignmnt,(SUM(Marks)/SUM(Total_marks))*100 AS assignmentPerc FROM student_registration INNER JOIN assignment ON student_registration.course_id = assignment.course_id INNER JOIN assignment_result ON assignment_result.assignment_id = assignment.id GROUP BY student_registration.student_id ) t2 ON t1.student_id = t2.student_id LEFT OUTER JOIN (SELECT student_id,COUNT(*) AS total_courses FROM student_registration GROUP BY student_id) t3 ON t2.student_id = t3.student_id LEFT OUTER JOIN student ON student.`student_id` = t1.student_id", d);
//   //d.Rows().RemoveAt(4);
//        
//   Imputation imp = new Imputation();
//   DataSet imputedDataSet = imp.Impute(d);
//   DataRow dr = new DataRow();
//   dr = imputedDataSet.getRow(imputedDataSet.Rows().getCount()-2);
//   imputedDataSet.Rows().RemoveAt(imputedDataSet.Rows().getCount()-2);
//  //imputedDataSet.Rows().RemoveAt(4);
//   for(DataRow r : imputedDataSet.Rows())
//   {
//        Object[] arr =  r.getItemArray();
//        
//        for(int i=0;i<arr.length;i++){
//       
//            System.out.print(arr[i].toString() + ",");
//            
//       }
//        System.out.println();
//   }
//   
//   
//   NaiveBayes nb= new NaiveBayes();
//   nb.Train(imputedDataSet);
//   nb.Classify(dr);
//   nb.StringSummary();
//  System.out.println(nb.StringSummary());
//   
//   System.out.println("\n\nFinal Prediction Probablites\n=========================\n");
//  
//   HashMap<Object,Double> prediction = nb.Classify(dr);
//     
//   for(Map.Entry<Object, Double> entry : prediction.entrySet())
//   {
//       String s = "";
//       Object[] o = dr.getItemArray();
//       for(Object ox : o)
//       {
//           s += ox.toString()+",";
//       }
//       
//       System.out.println(s);
//       System.out.print("\b");
//       System.out.println(entry.getKey()+" : " +entry.getValue());
//   }
//      
//   
//   

   
   
   
   
   
   
   
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
//  for(int i = 0;i<imputedDataSet.Rows().getCount();i++)
//      
//  {
//   HashMap<Object,Double> prediction = nb.Classify(imputedDataSet.getRow(i));
//     
//   for(Map.Entry<Object, Double> entry : prediction.entrySet())
//   {
//       System.out.println(entry.getKey()+" : " +entry.getValue());
//   }
//  }   
   
       
    alsBayse ab = new alsBayse();  
   String x = ab.test2(classifer,2);
   
    }

   // neighbourhood
  public String test2(Classifer c, int id)
   {
       
       RNF main=new RNF();
       
          String pre = "";
   DataSet d = new DataSet("mydataset");
   DataSetLoader dl = new DataSetLoader();
   DataSetLoader.setDATABASE_URL("jdbc:mysql://localhost:3306/als"); 
   d = dl.DatasetLoader("SELECT CASE WHEN t2.assignmentPerc > 90 THEN 'Excellent' WHEN t2.assignmentPerc < 90 AND assignmentPerc > 75 THEN 'Good' WHEN t2.assignmentPerc < 75 AND assignmentPerc > 65 THEN 'Average' WHEN t2.assignmentPerc < 60 THEN 'Below Average' END AssignmentPerformance,CASE WHEN t1.quizPerc > 90 THEN 'Excellent' WHEN t1.quizPerc < 90 AND quizPerc > 75 THEN 'Good' WHEN t1.quizPerc < 75 AND quizPerc > 60 THEN 'Average' WHEN t1.quizPerc < 60 THEN 'Below Average' END QuizPerformance FROM (SELECT student.student_id ,COUNT(*) AS total_quizes,(SUM(marks_obtained)/SUM(totak_marks)) * 100 AS quizPerc FROM student INNER JOIN quiz_submission ON quiz_submission.student_id = student.student_id INNER JOIN quiz_result ON quiz_result.quiz_submission_id = quiz_submission.quiz_submission_id GROUP BY student.student_id ) t1 INNER JOIN (SELECT student_registration.student_id,COUNT(*) AS total_assignmnt,(SUM(Marks)/SUM(Total_marks))*100 AS assignmentPerc FROM student_registration INNER JOIN assignment ON student_registration.course_id = assignment.course_id INNER JOIN assignment_result ON assignment_result.assignment_id = assignment.id GROUP BY student_registration.student_id ) t2 ON t1.student_id = t2.student_id LEFT OUTER JOIN (SELECT student_id,COUNT(*) AS total_courses FROM student_registration GROUP BY student_id) t3 ON t2.student_id = t3.student_id LEFT OUTER JOIN student ON student.`student_id` = t1.student_id WHERE student.`student_id`="+id, d);
   
   Imputation imp=new Imputation();
   
    DataSet imputSet=imp.Impute(d);
    
    
    DataSet NRS=main.NeighbourhoodRoughSets(imputSet, 5,"Excellent");
    
    NaiveBayes nb=new NaiveBayes();
    nb.Train(NRS);
   
    //System.out.println("\n\nFinal Prediction Probablites\n=========================\n");
//    
//    HashMap<Object,Double> prediction = c.Classify(d.Rows().get(5));
//     
//   for(Map.Entry<Object, Double> entry : prediction.entrySet())
//   {
////    
//      System.out.println(entry.getKey()+" : " +entry.getValue());
////       pre = entry.getKey().toString();
////       
//   }
    System.out.println("\n\nFinal Prediction Probablites\n=========================\n");
  for(int i = 0;i<imputSet.Rows().getCount();i++)
      
  {
   HashMap<Object,Double> prediction = nb.Classify(imputSet.getRow(i));
     
   for(Map.Entry<Object, Double> entry : prediction.entrySet())
   {
       System.out.println(entry.getKey()+" : " +entry.getValue());
       pre=entry.getKey().toString();
   }
  } 
  System.out.print("/" + pre);
    return pre;
   
   }  

    // bayse
   public String test(Classifer c, int id)
   {
       
          String pre = "";
   DataSet d = new DataSet("mydataset");
   DataSetLoader dl = new DataSetLoader();
   DataSetLoader.setDATABASE_URL("jdbc:mysql://localhost:3306/als"); 
   d = dl.DatasetLoader("SELECT CASE WHEN t2.assignmentPerc > 90 THEN 'Excellent' WHEN t2.assignmentPerc < 90 AND assignmentPerc > 75 THEN 'Good' WHEN t2.assignmentPerc < 75 AND assignmentPerc > 65 THEN 'Average' WHEN t2.assignmentPerc < 60 THEN 'Below Average' END AssignmentPerformance,CASE WHEN t1.quizPerc > 90 THEN 'Excellent' WHEN t1.quizPerc < 90 AND quizPerc > 75 THEN 'Good' WHEN t1.quizPerc < 75 AND quizPerc > 60 THEN 'Average' WHEN t1.quizPerc < 60 THEN 'Below Average' END QuizPerformance FROM (SELECT student.student_id ,COUNT(*) AS total_quizes,(SUM(marks_obtained)/SUM(totak_marks)) * 100 AS quizPerc FROM student INNER JOIN quiz_submission ON quiz_submission.student_id = student.student_id INNER JOIN quiz_result ON quiz_result.quiz_submission_id = quiz_submission.quiz_submission_id GROUP BY student.student_id ) t1 INNER JOIN (SELECT student_registration.student_id,COUNT(*) AS total_assignmnt,(SUM(Marks)/SUM(Total_marks))*100 AS assignmentPerc FROM student_registration INNER JOIN assignment ON student_registration.course_id = assignment.course_id INNER JOIN assignment_result ON assignment_result.assignment_id = assignment.id GROUP BY student_registration.student_id ) t2 ON t1.student_id = t2.student_id LEFT OUTER JOIN (SELECT student_id,COUNT(*) AS total_courses FROM student_registration GROUP BY student_id) t3 ON t2.student_id = t3.student_id LEFT OUTER JOIN student ON student.`student_id` = t1.student_id WHERE student.`student_id`="+id, d);
   
   HashMap<Object,Double> prediction = c.Classify(d.Rows().get(0));
     
   for(Map.Entry<Object, Double> entry : prediction.entrySet())
   {
    
      // System.out.println(entry.getKey()+" : " +entry.getValue());
       pre = entry.getKey().toString();
       
   }
   return pre;
   
   }
   
public void trainData()
{
     DataSet d = new DataSet("mydataset");
   DataSetLoader dl = new DataSetLoader();
   DataSetLoader.setDATABASE_URL("jdbc:mysql://localhost:3306/als"); 
   d = dl.DatasetLoader("SELECT CASE WHEN t2.assignmentPerc > 90 THEN 'Excellent' WHEN t2.assignmentPerc < 90 AND assignmentPerc > 75 THEN 'Good' WHEN t2.assignmentPerc < 75 AND assignmentPerc > 65 THEN 'Average' WHEN t2.assignmentPerc < 60 THEN 'Below Average' END AssignmentPerformance,CASE WHEN t1.quizPerc > 90 THEN 'Excellent' WHEN t1.quizPerc < 90 AND quizPerc > 75 THEN 'Good' WHEN t1.quizPerc < 75 AND quizPerc > 60 THEN 'Average' WHEN t1.quizPerc < 60 THEN 'Below Average' END QuizPerformance,student.ranking FROM (SELECT student.student_id ,COUNT(*) AS total_quizes,(SUM(marks_obtained)/SUM(totak_marks)) * 100 AS quizPerc FROM student INNER JOIN quiz_submission ON quiz_submission.student_id = student.student_id INNER JOIN quiz_result ON quiz_result.quiz_submission_id = quiz_submission.quiz_submission_id GROUP BY student.student_id ) t1 INNER JOIN (SELECT student_registration.student_id,COUNT(*) AS total_assignmnt,(SUM(Marks)/SUM(Total_marks))*100 AS assignmentPerc FROM student_registration INNER JOIN assignment ON student_registration.course_id = assignment.course_id INNER JOIN assignment_result ON assignment_result.assignment_id = assignment.id GROUP BY student_registration.student_id ) t2 ON t1.student_id = t2.student_id LEFT OUTER JOIN (SELECT student_id,COUNT(*) AS total_courses FROM student_registration GROUP BY student_id) t3 ON t2.student_id = t3.student_id LEFT OUTER JOIN student ON student.`student_id` = t1.student_id", d);
   //d.Rows().RemoveAt(4);
        
   Imputation imp = new Imputation();
   DataSet imputedDataSet = imp.Impute(d);
 //  DataRow dr = new DataRow();
  // dr = imputedDataSet.getRow(imputedDataSet.Rows().getCount()-2);
 //  imputedDataSet.Rows().RemoveAt(imputedDataSet.Rows().getCount()-2);
  //imputedDataSet.Rows().RemoveAt(4);
//   for(DataRow r : imputedDataSet.Rows())
//   {
//        Object[] arr =  r.getItemArray();
//        
//        for(int i=0;i<arr.length;i++){
//       
//            System.out.print(arr[i].toString() + ",");
//            
//       }
//        System.out.println();
//   }
   
   
   NaiveBayes nb= new NaiveBayes();
    classifer = nb.Train(imputedDataSet);
}
   
   
    
    
}

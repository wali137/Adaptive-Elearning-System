/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package learners.other;

import jade.proto.SSResponderDispatcher;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;
import learners.classifiers.Classifer;
import learners.classifiers.RoughSets;
import learners.classifiers.Sets;
import learners.classifiers.bayes.NaiveBayes;
import learners.data.DataRow;
import learners.other.FuzzyLogic;
import learners.data.DataSet;
import learners.data.DataSetLoader;
import learners.data.cleaners.Imputation;
import learners.other.NRoughSets;

/**
 *
 * @auth dell n Dv6
 */
public class RNF {

    DataSet dataset;
    DataSet Datatable;
    private DataSetLoader dl;

    public static void main(String[] args) {

        //String Query = "select * from rough where COL_1 = 'P1' or COL_1 = 'P5' or COL_1 = 'P19'";
        String TrainQuery = "SELECT * FROM gene2 WHERE id > 100 AND id < 151 UNION SELECT * FROM gene2 WHERE id > 550 AND id < 600 UNION SELECT * FROM gene2 WHERE id > 949";
        String TestQuery = "SELECT * FROM gene2 WHERE id > 10 AND id < 21 UNION SELECT * FROM gene2 WHERE id > 500 AND id < 511 UNION SELECT * FROM gene2 WHERE id > 910 and id < 921";
        //String TrainQuery = "Select * from weatherdata limit 10";
        //String TestQuery = "Select * from weatherdata where id >= 10";
        

        DataSet Train;
        DataSet Test;

        RNF main = new RNF();
        Imputation imp = new Imputation();

        DataSetLoader.setDATABASE_URL("jdbc:mysql://localhost:3306/wekatest");
        
        Train = main.LoadData(TrainQuery);
        Train = imp.Impute(Train);
        
        Test = main.LoadData(TestQuery);
        Test = imp.Impute(Test);
        
        
//        
// //============================================NRS       
//        
//        DataSet NRS = main.NeighbourhoodRoughSets(Train, 0.1, "Breast Cancer (JHU, US)"); //Neibourhood Rough Sets   
//       // DataSet NRS = main.NeighbourhoodRoughSets(Train, 5, "P");
//      Accuracy ac = new Accuracy();
//      
//              ArrayList<Object> n = new ArrayList();// = (ArrayList) instance.getItemArray();
//             
//      for(DataRow instance : Test.Rows())
//      {
//         
//           double x = 0;
//          for(DataRow model : NRS.Rows())
//      {
//       x = ac.TupleCompatibilty(instance, model);
//       if (x == 1)
//       {
//              for (int i=0; i< instance.getItemArray().length; i++) {
//                  n.add(i);
//              }
//           
//                   
//           Object[] ins = instance.getItemArray();
//           Object del = null;
//           
//           for(Object o : ins)
//           {    
//               System.out.print(o+", ");
//              
//           }
//           
//            Object[] ob = model.getItemArray();
//            System.out.println("\nPredicion: "+ob[ob.length-1]+"  " + x);
//            //n.remove(del);
//            break;
//       }
//       else
//       {
////         if(x<1 && x>0){
////               System.out.println("Not classify");
//////           Object[] ins = instance.getItemArray();
//////           Object del= null;
//////           for(Object o : ins)
//////           {
//////               System.out.print(o+", ");
//////                del = o;
//////           }
//////           
//////            Object[] ob = model.getItemArray();
//////            System.out.println("\nPredicion: Cannot Classify");
//////            //n.remove(del);
//////            break;
////           }
//           //break;
//       }
//       
//        // NRS.setClassIndex(NRS.Columns().getCount()-1);
//      }
//           if(x<1 && x>=0)
//           {
//                Object[] ins = instance.getItemArray();
//           Object del = null;
//           
//           for(Object o : ins)
//           {    
//               System.out.print(o+", ");
//              
//           }
//               System.out.println("\nPredicion: Not Classified");
//               //System.out.println("\nPredicion: "+ins[ins.length-1]);
//           }
//     
//      }
//        
        
        
        
        
        NaiveBayes nvb = new NaiveBayes();

        Classifer cls = nvb.Train(Train);

        System.out.println(cls.StringSummary());

        for (int i = 0; i < Test.Rows().getCount(); i++) {
            HashMap<Object, Double> prediction = cls.Classify(Test.getRow(i));

           Object[] ins = Test.getRow(i).getItemArray();
           Object del = null;
           
           for(Object o : ins)
           {    
               System.out.print(o+",");
              
           }
            
           System.out.print("\n");
            for (Map.Entry<Object, Double> entry : prediction.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue() + "\n\n");
            }
        }
        System.out.println();




        //my region=================================
        //==========================================



//        //Accuray Thingi
//        Accuracy ac = new Accuracy();
//        
//        
//        //NRS Shit
//        DataSet d = main.NeighbourhoodRoughSets(main.dataset);
//        DataSet filterd = ac.Filter(ac.makeCopy(main.dataset), d);
//        ArrayList<Double> pro = ac.NeigbourhoodRoughSetAccuracy(main.dataset, d.Rows(), 1);
//        
//        
//        
//        
//        //Naive Bayes Shit
//        DataSet train15 = ac.makeCopy(main.dataset);
//        DataSet test5 = new DataSet();
//        
//        for(int i=15;i<=18;i++)
//        train15.Rows().RemoveAt(15);
//        train15.Rows().RemoveAt(15);
//        
//        for(int j=15;j<20;j++)
//        test5.Rows().Add(main.dataset.Rows().get(j));
//        
//        ArrayList<Double> pro2 = ac.NaiveBayes_NeigbourhoodRoughSetAccuracy(main.dataset,train15);
//        
//        
//        //Fuzzy Logic Shit
//        
//        FuzzyLogic f = new FuzzyLogic();
//        Sets ans = f.FuzzyLogic(d);
//        DataSet fuzzyset = ac.FindFuzzyDecision(ans, main.dataset);
//        DataSet filterd2 = ac.Filter(ac.makeCopy(main.dataset), fuzzyset);
//        ArrayList<Double> pro3 = ac.NeigbourhoodRoughSetAccuracy(main.dataset, fuzzyset.Rows(), 1);
//        
//        
//        
//        //Final Graph ArrayLists
//        ArrayList<Object> fobj = new ArrayList<>();
//        fobj.add(pro);
//        fobj.add(pro2);
//        fobj.add(pro3);
//       
//        Graphs g = new Graphs("Graphs",fobj,20);



////        NaiveBayes nb = new NaiveBayes();
////        nb.Train(d);
////        
////        HashMap<Double,Double> myprediction = new HashMap<>();
////        HashMap<Object,Double> mypredictiontest = new HashMap<>();
////   ArrayList<String> x = new ArrayList<>();
////       // ArrayList<Double> prob = new ArrayList<>();
////        for(DataRow r : filterd.Rows())
////        {  
////           mypredictiontest = nb.Classify(r);
////            
////             for(Map.Entry<Object, Double> entry :  mypredictiontest.entrySet())
////           {
////              // System.out.println(r.get(0).toString() +":= "+ r.get(4).toString() +"  ::"+entry.getKey()+" : " +entry.getValue());
////                //myprediction.put(Double.parseDouble(r.get(4).toString()), Double.parseDouble(entry.getKey().toString()));
////               x.add(r.get(4).toString()+":"+entry.getKey().toString());
////           } 
////        }

        //  ArrayList<Double> d2 = ac.NaiveBayes_NeigbourhoodRoughSetAccuracy(x);


        //my region=================================
        //==========================================

//        Scanner scan = new Scanner(System.in);
//        boolean flag = true;
//        while(flag){
//            
//               // System.out.println("1.Rough Sets");
//             //   System.out.println("2.Rough Sets and Fuzzy Sets 2gather");
//                System.out.println("3.Neighbourhood Rough Sets");
//                System.out.println("4.Fuzzy Sets");
//                System.out.println("5.Neighbourhood Rough Sets and Fuzzy Sets 2gather");
//                System.out.println("6.Exit");
//                
//        switch(scan.nextInt())
//                {
//        
//                     case 1:
//                         main.RoughSets(main.dataset);
//                         break;
//                     case 2:
//                         break;
//                    case 3:
//                        main.NeighbourhoodRoughSets(main.dataset); //Neibourhood Rough Sets
//                    break;
//        
//                    case 4:
//                         main.FuzzySets(main.dataset);
//                    break;
//                        
//                    case 5:
//                        main.FuzzySets(main.NeighbourhoodRoughSets(main.dataset));
//                    break;
//                      
//                    case 6:
//                            flag = false;
//                           break; 
//        
//        }//End Switch
//        }

        //===============region end


        // main.NeighbourhoodRoughSets(main.dataset); //Neibourhood Rough Sets

        //  main.FuzzySets(main.dataset); //Fuzzy Sets

        // main.FuzzySets(main.NeighbourhoodRoughSets(main.dataset)); //Neibourhood Rough Sets + Fuzzy Sets


    }

    public void RoughSets(DataSet dataset) {

        RoughSets rs = new RoughSets();
        rs.ApplyRoughSets(dataset);
    }

    public void FuzzySets(DataSet Fset) {

        FuzzyLogic fl = new FuzzyLogic();
        fl.FuzzyLogic(Fset);
    }

    public DataSet NeighbourhoodRoughSets(DataSet NHRset, double threshold, String strClass) {


        DataSet dset = new DataSet("NBHResults");
        NRoughSets lm = new NRoughSets();
        //double threshold = 150;
        //    System.out.println("Using Euclidean Measure"+"\n");
        System.out.println("Threshold: " + threshold + "\n");



        // lm.NeighbourRoughSet2(d,threshold);
        dset = lm.STRNeighbourRoughSet(NHRset, threshold, strClass);
        return dset;
    }

    public DataSet LoadData(String Query) {

        Datatable = new DataSet("Decision_Tree");
        dl = new DataSetLoader();
        Datatable = dl.DatasetLoader(Query, Datatable);

        //Datatable = dl.DatasetLoader("select * from rough",Datatable);
        return Datatable;

    }
}

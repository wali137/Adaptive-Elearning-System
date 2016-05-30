/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package learners.main;

import agents.Agent;
import agents.AgentManager;
import agents.StudentTeacherAgent;
import jade.lang.acl.ACLMessage;
import jade.wrapper.ControllerException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import learners.classifiers.Classifer;
import learners.classifiers.bayes.NaiveBayes;
import learners.data.DataRow;
import learners.data.DataSet;
import learners.data.DataSetLoader;

/**
 *
 * @author Dv6
 */
public class WebTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       
        AgentManager am = new AgentManager();
        am.StartAgentManager();  
     //   am.InitAgent(Agent.DOCTOR.name());

        
        
        System.out.printf("gona kill");
//        try {
//           am.killAll();
//            
          
    //        
    //        //Declare
    //        DataSetLoader dl = new DataSetLoader();
    //        DataSet dataset = new DataSet("TreatmentDataSet");
    //        DataSet instanceDataset = new DataSet("TreatmentInstance");
    //        Classifer classifer = new NaiveBayes();
    //        
    //        //Initate
    //        DataSetLoader.setDATABASE_URL("jdbc:mysql://localhost:3306/dbhkms");
    //        
    //        //FILL
    //        dataset = dl.StoredProcedure_DatasetLoader("{CALL treatmentdataset()}", null, dataset);
    //        instanceDataset  = dl.StoredProcedure_DatasetLoader("{CALL treatmentdataset()}", null, instanceDataset);
    //        
    //        //Train
    //        classifer.Train(dataset);
    //        
    //        //Set
    //        DataRow instance = instanceDataset.getRow(0);
    //        
    //        //Classify
        //        HashMap<Object,Double> classification = classifer.Classify(instance);
    //        
//        } catch (ControllerException ex) {
//            Logger.getLogger(WebTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        
        
        System.out.println("k");
    }
    
}

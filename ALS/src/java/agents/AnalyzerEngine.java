package agents;

import controller.Course_Controller;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;
import jade.wrapper.PlatformController;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import learners.classifiers.Classifer;
import learners.classifiers.bayes.NaiveBayes;
import learners.data.DataRow;
import learners.data.DataSet;
import learners.data.DataSetLoader;

public class AnalyzerEngine extends Agent {

    AgentController NewAgent = null;
    PlatformController container;
    String agent;
    static AnalyzerEngine aeAgent;
    AgentManager am = new AgentManager();
    static DataSetLoader dl = new DataSetLoader();
    static DataSet dataset = new DataSet("DataSet");
    static DataSet instanceDataset = new DataSet("Instance");
    Classifer classifer = new NaiveBayes();

    @Override
    protected void setup() {

        aeAgent = this;

        container = getContainerController();
        addBehaviour(new SimpleBehaviour() {
            @Override
            public void action() {
                try {


                    if (NewAgent == null)
                    {
                    NewAgent = container.createNewAgent(agents.Agent.DBWRAPPER.name(), "agents.DBWrapperAgent", null);
                    NewAgent.start();
                    }

                 Thread.sleep(1000);   
                 //   while(dataset.getClassIndex() > 0)
                    {
                        if(dataset.getClassIndex() >= 0)
                        {
                            
                  
                    //Train
                            
                    classifer.Train(dataset);

                    //Set
                    DataRow instance = instanceDataset.getRow(0);

                    //Classify
                    HashMap<Object, Double> classification = classifer.Classify(instance);

                    for (Map.Entry<Object, Double> entry : classification.entrySet()) {
                        //System.out.println(entry.getKey() + "   " + entry.getValue());
                        Course_Controller.finalprediction = entry.getKey().toString(); // Final_prediction(entry.getKey().toString());
                    }
                    
                    block();
                    am.killAll();
                    
                    
                          }
                    }
                

                } catch (InterruptedException ex) {
                    Logger.getLogger(AnalyzerEngine.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ControllerException ex) {
                    Logger.getLogger(AnalyzerEngine.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public boolean done() {
                return false;
            }
        });


    }

    public void sendMessage(ACLMessage msg, String agentName) {

        try {

            //   if(msg != null){

            msg.clearAllReceiver();
            AID r = new AID(agentName + "@" + getHap(), AID.ISGUID);
            msg.addReceiver(r);
            this.send(msg);
            //}
        } catch (Exception ex) {
            System.out.println("Error Patient :" + ex.getMessage());
        }

    }
}

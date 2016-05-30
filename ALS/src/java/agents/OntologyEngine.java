package agents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;
import jade.wrapper.PlatformController;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OntologyEngine extends Agent{
        AgentController NewAgent;
        PlatformController container;
        String agent;
    @Override
    protected void setup()
    {
    
                container = getContainerController();
                addBehaviour(new SimpleBehaviour() {

                    @Override
                    public void action() {
                        
                        MessageTemplate PatientMT = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
                        ACLMessage aclM = myAgent.receive(PatientMT);
                        if(aclM != null)
                        {
//                    try {
//                            
//                            NewAgent = container.createNewAgent("DBWrapperAgent","Agents.DBWrapperAgent",null);
//                            NewAgent.start();
//                            
//                            agent = "DBWrapperAgent";        
//                    } catch (ControllerException ex) {
//                        Logger.getLogger(OntologyEngine.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                         sendMessage(aclM,agent);
                        }
                        else
                        {
                        block();
                        }
                       
                     //   doDelete();
                    }

                    @Override
                    public boolean done() {
                       return false;
                    }
                });
          
    
    }
    
    public void sendMessage(ACLMessage msg,String agentName)
    {
        
try{

 //   if(msg != null){
        
        msg.clearAllReceiver();
        AID r = new AID(agentName+"@"+getHap(),AID.ISGUID);
        msg.addReceiver(r);
        this.send(msg);
    //}
}catch(Exception ex){
    System.out.println("Error Patient :" + ex.getMessage());
}    

    }
}

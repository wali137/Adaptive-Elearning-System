package agents;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;
import jade.wrapper.PlatformController;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dell
 */
public class StudentTeacherAgent extends Agent {

    public static StudentTeacherAgent docAgent;
    private static Object recieved;
    PlatformController container;
    AgentController NewAgent;

    /**
     * @return the recieved
     */
    public static Object getRecieved() {
        return recieved;
    }

    /**
     * @param aRecieved the recieved to set
     */
    public static void setRecieved(Object aRecieved) {
        recieved = aRecieved;
    }

    @Override
    protected void setup() {

        docAgent = this;
        container = getContainerController();
        addBehaviour(new SimpleBehaviour() {
            @Override
            public void action() {
                try {
                    AID[] aid = new AID[1];
                    aid[0] = new AID("DoctorAgent@" + getHap(), AID.ISGUID);



                    NewAgent = container.createNewAgent(agents.Agent.ANALYZER.name(), "agents.AnalyzerEngine", null);
                    NewAgent.start();


                    // MessageTemplate PatientMT = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);

                    //  MessageTemplate PatientMT = MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.REQUEST),MessageTemplate.MatchReceiver(aid));
                    // ACLMessage aclM = myAgent.receive(PatientMT);
                    //                        if(aclM != null)
                    //                        {
                    //   try {
                    //  Object o = aclM.getContentObject();
                    //  setRecieved(o);
                    //System.out.println("Value at Doctor Agent : " + aclM.getOntology());
                    //     Logger.getLogger(DoctorAgent.class.getName()).log(Level.SEVERE, null, ex);
                    //     Logger.getLogger(DoctorAgent.class.getName()).log(Level.SEVERE, null, ex);
                    // }
                    // }
                    //else
                    // {
                    block();
                    // }
                } catch (ControllerException ex) {
                    Logger.getLogger(StudentTeacherAgent.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            @Override
            public boolean done() {
                return false;
            }
        });


    }
}

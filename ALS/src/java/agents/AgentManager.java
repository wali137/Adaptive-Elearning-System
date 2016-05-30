package agents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.lang.acl.ACLMessage;
import jade.util.leap.Properties;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;
import java.io.Serializable;

public class AgentManager {

    jade.core.Runtime rt;
    Object reference;
    Object[] argx;
    Profile p = null;
    jade.wrapper.AgentContainer ac;
    AgentController cont;
    private ACLMessage msg;
    Properties pro;

    public AgentManager() {
        pro = new Properties();
    }

    public Boolean StartAgentManager() {

        try {

            String[] args = {"-gui"};
            jade.Boot.main(args);

            return true;
        } catch (Exception x) {
            System.out.println(x.getMessage());
            return false;
        }

    }

    public Boolean InitAgent(String AgentName) {
        try {

            rt = jade.core.Runtime.instance();





            switch (AgentName) {

                case "DBWRAPPER": {
                   
                    if(p== null)
                    {
            p = new ProfileImpl(false);
            ac = rt.createAgentContainer(p);


            reference = new Object();
            argx = new Object[1];
            argx[0] = reference;
                    }
                    ac.createNewAgent(agents.Agent.DBWRAPPER.name(), "agents.DBWrapperAgent", argx);
                    cont = ac.getAgent(agents.Agent.DBWRAPPER.name());
                    cont.start();
                    return true;
                }


                case "STUDENTTEACHER": {
                    
                    
                    if(p== null)
                    {                    
            p = new ProfileImpl(false);
            ac = rt.createAgentContainer(p);


            reference = new Object();
            argx = new Object[1];
            argx[0] = reference;
                    }
                    
                    ac.createNewAgent(agents.Agent.STUDENTTEACHER.name(), "agents.StudentTeacherAgent", argx);
                    cont = ac.getAgent(agents.Agent.STUDENTTEACHER.name());
                    cont.start();
                    return true;
                }

                case "ANALYZER": {
                    
                    
                    
                     if(p== null)
                    {                   
            p = new ProfileImpl(false);
            ac = rt.createAgentContainer(p);


            reference = new Object();
            argx = new Object[1];
            argx[0] = reference;
                    }
                    ac.createNewAgent(agents.Agent.ANALYZER.name(), "agents.AnalyzerEngine", argx);
                    cont = ac.getAgent(agents.Agent.ANALYZER.name());
                    cont.start();
                    return true;
                }

                case "ONTOLOGY": {
                    
                    
                    
                     if(p== null)
                    {                   
            p = new ProfileImpl(false);
            ac = rt.createAgentContainer(p);


            reference = new Object();
            argx = new Object[1];
            argx[0] = reference;
                    }
                    
                    ac.createNewAgent(agents.Agent.ONTOLOGY.name(), "agents.OntologyEngine", argx);
                    cont = ac.getAgent(agents.Agent.ONTOLOGY.name());
                    cont.start();
                    return true;
                }


                default: {
                    return false;
                    //break;
                }
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }

    }
    
    public void killAll() throws ControllerException
    {
        //cont = ac.getAgent(agents.Agent.DBWRAPPER.name());
       // cont.kill();
       // cont = ac.getAgent(agents.Agent.DOCTOR.name());
       // cont.kill();
       // cont = ac.getAgent(agents.Agent.ANALYZER.name());
       // cont.kill();
       // cont = ac.getAgent(agents.Agent.ONTOLOGY.name());
       // cont.kill();
        
        if(DBWrapperAgent.dbAgent != null)
            DBWrapperAgent.dbAgent.doDelete();
        if(StudentTeacherAgent.docAgent != null)
            StudentTeacherAgent.docAgent.doDelete();
        if(AnalyzerEngine.aeAgent != null)
            AnalyzerEngine.aeAgent.doDelete();
        
    }

    public Boolean SendMessage(int Type, Serializable Content, String AgentName, Agent obj) // Type is ACLMessage.ENUM
    {
        try {

            msg = new ACLMessage();
            msg.clearAllReceiver();
            msg.addReceiver(new AID(AgentName + "@" + obj.getHap()));
            //msg.setContent(ACLMessage.REQUEST);
            msg.setContentObject(Content);

            obj.send(msg);
            return true;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    //More Functions can be added per need
}

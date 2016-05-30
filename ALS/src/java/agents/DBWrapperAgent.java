package agents;

//import Models.Hospital;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.util.leap.Properties;
import java.io.Serializable;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
//import Repositories.HospitalRepository;
import jade.wrapper.AgentController;
import jade.wrapper.PlatformController;
import java.util.List;
import javassist.bytecode.analysis.Analyzer;
import learners.data.cleaners.Imputation;

public class DBWrapperAgent extends Agent {

    AgentController NewAgent;
   static DBWrapperAgent dbAgent;
    PlatformController container;

    @Override
    protected void setup() {
 
        dbAgent = this;
        container = getContainerController();
        addBehaviour(new SimpleBehaviour() {
            @Override
            public void action() {

                AnalyzerEngine.dl.setDATABASE_URL("jdbc:mysql://localhost:3306/als");
                 Imputation imp = new Imputation();
                   AnalyzerEngine.dataset = AnalyzerEngine.dl.StoredProcedure_DatasetLoader("{CALL bayse()}", null, AnalyzerEngine.dataset);
                   AnalyzerEngine.instanceDataset = AnalyzerEngine.dl.DatasetLoader("SELECT CASE WHEN t2.assignmentPerc > 90 THEN 'Excellent' WHEN t2.assignmentPerc < 90 AND assignmentPerc > 75 THEN 'Good' WHEN t2.assignmentPerc < 75 AND assignmentPerc > 65 THEN 'Average' WHEN t2.assignmentPerc < 60 THEN 'Below Average' END AssignmentPerformance,CASE WHEN t1.quizPerc > 90 THEN 'Excellent' WHEN t1.quizPerc < 90 AND quizPerc > 75 THEN 'Good' WHEN t1.quizPerc < 75 AND quizPerc > 60 THEN 'Average' WHEN t1.quizPerc < 60 THEN 'Below Average' END QuizPerformance FROM (SELECT student.student_id ,COUNT(*) AS total_quizes,(SUM(marks_obtained)/SUM(totak_marks)) * 100 AS quizPerc FROM student INNER JOIN quiz_submission ON quiz_submission.student_id = student.student_id INNER JOIN quiz_result ON quiz_result.quiz_submission_id = quiz_submission.quiz_submission_id GROUP BY student.student_id ) t1 INNER JOIN (SELECT student_registration.student_id,COUNT(*) AS total_assignmnt,(SUM(Marks)/SUM(Total_marks))*100 AS assignmentPerc FROM student_registration INNER JOIN assignment ON student_registration.course_id = assignment.course_id INNER JOIN assignment_result ON assignment_result.assignment_id = assignment.id GROUP BY student_registration.student_id ) t2 ON t1.student_id = t2.student_id LEFT OUTER JOIN (SELECT student_id,COUNT(*) AS total_courses FROM student_registration GROUP BY student_id) t3 ON t2.student_id = t3.student_id LEFT OUTER JOIN student ON student.`student_id` = t1.student_id WHERE student.`student_id`=2", AnalyzerEngine.instanceDataset);
                   AnalyzerEngine.dataset = imp.Impute(AnalyzerEngine.dataset);
                   AnalyzerEngine.instanceDataset = imp.Impute(AnalyzerEngine.instanceDataset);

                block();

            }

            @Override
            public boolean done() {
                return false;
            }
        });
    }

   
}

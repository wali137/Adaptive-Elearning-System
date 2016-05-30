/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import model.Assignment;
import model.AssignmentResult;
import model.AssignmentSubmission;
import repositery.AssignmentRepositery;

/**
 *
 * @author DemiXsoft
 */
public class AssignmentServices {
    
    AssignmentRepositery ar = new AssignmentRepositery();
    
    public void CreateAssignment(Assignment a)
    {
        ar.CreateAssignment(a);
    }
    
    public String getTitle(int ID)
    {
        return ar.getTitle(ID);
    }
    
    public String getAssignStatement(int ID)
    {
        return ar.getAssignStatement(ID);
    }
    
    public String getDueDate(int ID)
    {
        return ar.getDueDate(ID);
    }
    
    public String getMarks(int ID)
    {
        return ar.getMarks(ID);
    }
    
    public List<Assignment> getList(String CID)
    {
        return ar.getAssignment(CID);
    }
        
    public void UpdateAssignment(Assignment a)
    {
        ar.update(a);
    }
    
    public void DeleteAssignment(Assignment a)
    {
        ar.Delete(a);
    }
    
    public String getCreatedBy(int ID)
    {
        return ar.getCreatedBy(ID);
    }
    
    public String getAttachment(int ID)
    {
        return ar.getAttachment(ID);
    }
    
    public int submitAssignment(AssignmentSubmission sub)
    {
        int id = ar.SubmitAssignment(sub);
        ar.IncrementSubmission(sub.getAssignmentId());
        return id;
    }
    
    public int submitResult(AssignmentResult res)
    {
        return ar.SubmitResult(res);
    }
    
    public int isSubmitted(int UID, int AID)
    {
        return ar.isSubmitted(UID, AID);
    }
    
    public List<Assignment> submissions(int ID,String cid)
    {
        return ar.submissions(ID,cid);
    }
    
    public List<AssignmentResult> results(int ID)
    {
        return ar.results(ID);
    }
    
}

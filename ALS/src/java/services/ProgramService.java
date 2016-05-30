/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


//import Model.Program;

import model.Department;
import model.Program;
import repositery.DepartmentRepositery;
import repositery.ProgramRepositery;
import java.util.List;
/**
 *
 * @author dell
 */
public class ProgramService {
     DepartmentRepositery _repDepartmentRepositery;
  ProgramRepositery _repProgramRepositery;

    public ProgramService() {

        _repProgramRepositery = new ProgramRepositery();
    _repDepartmentRepositery=new DepartmentRepositery();
    }

    public boolean Save(Program _modProgram) {

        return _repProgramRepositery.Save(_modProgram);

    }

    public boolean Delete(Program _modProgram) {

        return _repProgramRepositery.Delete(_modProgram);



    }

    public boolean Update(Program _modProgram) {
        return _repProgramRepositery.Update(_modProgram);
    }

    public List<Program> getProgramList() {

        return _repProgramRepositery.getProgramsList();
    }

 public List<Department> getDepartmentList() {

        return _repDepartmentRepositery.getDepartmentsList();
    }

    
}

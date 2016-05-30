/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Program;
import model.Department;
import repositery.frequentRepo;
import services.ProgramService;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author mushii
 */
public class ProgramController {
Program program;
frequentRepo fr = new frequentRepo();

    public Program getProgram() {
        if (program == null) {
            program = new Program();
            program.setDepartment(new Department());
        }
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }
    Program _modProgram;
    ProgramService _serProgramService;

    public Program getModProgram() {
        if (_modProgram == null) {

            _modProgram = new Program();
       _modProgram.setDepartment(new Department());
        }
        return _modProgram;
    }

    public void setModProgram(Program _modProgram) {
        this._modProgram = _modProgram;
    }

    public ProgramService getSerProgramService() {
        if (_serProgramService == null) {
            _serProgramService = new ProgramService();
        }
        return _serProgramService;
    }

    public void setSerProgramService(ProgramService _serProgramService) {
        this._serProgramService = _serProgramService;
    }

    public void Save() {
        getSerProgramService().Save(getModProgram());
    }

    public List<Program> getProgramesList() {

        return getSerProgramService().getProgramList();
    }

    public void onCreate() {

        if (program != null) {
            _serProgramService.Save(program);
            
        }
        
        program=null;

    }

    public void onUpdate() {
        _serProgramService.Update(_modProgram);
    }

    public void onDelete() {
        _serProgramService.Delete(_modProgram);
    }

    public void myUnedit(SelectEvent event) {
        //selected = null;
    }
    
    
    public String depName(int id){
      Department dp =  (Department) fr.getid2obj(id, Department.class);
      return dp.getDepartmentName();
    }

    public void myEdit(SelectEvent event) {

        Object o = event.getObject();
        Object[] ob = (Object[]) o;


        _modProgram.setProgramId((Integer) ob[0])    ;
        _modProgram.setProgramName(ob[1].toString());
        
        Department dp =  (Department) fr.getid2obj((Integer)ob[2], Department.class);
       _modProgram.setDepartment(dp);
               //setdepartmentId((Integer) ob[2])  ;

    }



 public List<SelectItem> getlist(){
        
    List<Department> list =  getSerProgramService().getDepartmentList();
    List<SelectItem> items = new ArrayList<SelectItem>();
    
    ArrayList<Object[]> objlist = (ArrayList<Object[]>) (Object)list;
    
    for(Object[] o : objlist){
   
        items.add(new SelectItem(o[0],o[1].toString()));
        
    }

        return items;
}



}

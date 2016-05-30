/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import model.Department;
import model.Institute;
import repositery.DepartmentRepositery;
import repositery.InstituteRepositery;
import java.util.List;

/**
 *
 * @author mushii
 */
public class DepartmentService {

  InstituteRepositery _repInstituteRepositery;
  DepartmentRepositery _repDepartmentRepositery;

    public DepartmentService() {

        _repDepartmentRepositery = new DepartmentRepositery();
    _repInstituteRepositery=new InstituteRepositery();
    }

    public boolean Save(Department _modDepartment) {

        return _repDepartmentRepositery.Save(_modDepartment);

    }

    public boolean Delete(Department _modDepartment) {

        return _repDepartmentRepositery.Delete(_modDepartment);



    }

    public boolean Update(Department _modDepartment) {
        return _repDepartmentRepositery.Update(_modDepartment);
    }

    public List<Department> getDepartmentList() {

        return _repDepartmentRepositery.getDepartmentsList();
    }

 public List<Institute> getInstituteList() {

        return _repInstituteRepositery.getInstitutesList();
    }

}

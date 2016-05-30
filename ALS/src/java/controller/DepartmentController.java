/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import model.Department;

/**
 *
 * @author mushii
 */
public class DepartmentController {
Department department;

    public Department getDepartment() {
        if (department == null) {

            department = new Department();
        }
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
    
    



 

}

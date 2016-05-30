/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import model.Institute;
import repositery.InstituteRepositery;
import java.util.List;

/**
 *
 * @author mushii
 */
public class InstituteService {

    InstituteRepositery _repInstituteRepositery;

    public InstituteService() {

        _repInstituteRepositery = new InstituteRepositery();
    }

    public boolean Save(Institute _modInstitute) {

        return _repInstituteRepositery.Save(_modInstitute);

    }

    public boolean Delete(Institute _modInstitute) {

        return _repInstituteRepositery.Delete(_modInstitute);



    }

    public boolean Update(Institute _modInstitute) {
        return _repInstituteRepositery.Update(_modInstitute);
    }

    public List<Institute> getInstituteList() {

        return _repInstituteRepositery.getInstitutesList();
    }

 


}


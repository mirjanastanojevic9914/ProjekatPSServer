/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic.so.doctorType;

import businesslogic.so.GeneralSO;
import database.DatabaseRepository;
import domain.Doctor;
import domain.DoctorType;
import domain.GenericDomainObject;
import java.util.List;

/**
 *
 * @author Mira
 */
public class GetListOfDoctorTypesSO extends GeneralSO{

    private List<GenericDomainObject> list;

    @Override
    protected void checkCondition(Object obj) throws Exception {
        //there is no condition
    }

    @Override
    protected void executeOperation(Object obj) throws Exception {
        DatabaseRepository.getInstance().returnList((DoctorType) obj);
    }
     
    public List<GenericDomainObject> getListDoctorTypes() {
        return list;
    }
}

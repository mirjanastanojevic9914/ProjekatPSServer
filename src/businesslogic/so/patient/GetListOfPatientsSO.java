/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic.so.patient;

import businesslogic.so.GeneralSO;
import database.DatabaseRepository;
import domain.DoctorType;
import domain.GenericDomainObject;
import domain.Patient;
import java.util.List;

/**
 *
 * @author Mira
 */
public class GetListOfPatientsSO extends GeneralSO{

    private List<GenericDomainObject> list;

    @Override
    protected void checkCondition(Object obj) throws Exception {
        //there is no condition
    }

    @Override
    protected void executeOperation(Object obj) throws Exception {
        DatabaseRepository.getInstance().returnList((Patient) obj);
    }
     
    public List<GenericDomainObject> getListPatients() {
        return list;
    }
}

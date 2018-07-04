/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic.so.doctor;

import businesslogic.so.GeneralSO;
import database.DatabaseRepository;
import domain.Doctor;
import domain.GenericDomainObject;
import java.util.List;

/**
 *
 * @author Mira
 */
public class GetListOfDoctorsSO extends GeneralSO{
    
    private List<GenericDomainObject> list;

    @Override
    protected void checkCondition(Object obj) throws Exception {
        //there is no condition
    }

    @Override
    protected void executeOperation(Object obj) throws Exception {
        DatabaseRepository.getInstance().returnList((Doctor) obj);
    }
     
    public List<GenericDomainObject> getListDoctors() {
        return list;
    }
    
}

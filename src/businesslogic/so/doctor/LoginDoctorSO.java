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
import java.util.ArrayList;

/**
 *
 * @author Mira
 */
public class LoginDoctorSO extends GeneralSO{
      ArrayList<GenericDomainObject> odoList;

    @Override
    protected void checkCondition(Object obj) throws Exception {
        //there is no conditions
    }

    @Override
    protected void executeOperation(Object obj) throws Exception {
        Doctor doctor = (Doctor) obj;
        String criteria = doctor.getName()+ "/" + doctor.getSurname() + "/" + doctor.getPassword();
        odoList = (ArrayList<GenericDomainObject>) DatabaseRepository.getInstance().getObjectWithCriteriaForLogin(criteria, doctor);
    }
    
    public GenericDomainObject getDoctor() {
        if (odoList.isEmpty()) {
            return null;
        }
        GenericDomainObject odo  = odoList.get(0);
        return odo;
    }
}

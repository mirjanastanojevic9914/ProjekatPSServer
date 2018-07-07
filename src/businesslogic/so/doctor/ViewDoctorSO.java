/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic.so.doctor;

import businesslogic.so.GeneralSO;
import database.DatabaseRepository;
import domain.Doctor;
import domain.Patient;

/**
 *
 * @author Mira
 */
public class ViewDoctorSO extends GeneralSO{
      private Doctor doctor = null;
    
    @Override
    protected void checkCondition(Object obj) throws Exception {
        //there is no conditions
    }

    @Override
    protected void executeOperation(Object obj) throws Exception {
        doctor = (Doctor) DatabaseRepository.getInstance().find((Doctor) obj);
    }
    
    public Doctor getDoctor(){
        return doctor;
    }
}

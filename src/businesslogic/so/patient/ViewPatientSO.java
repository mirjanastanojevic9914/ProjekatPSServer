/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic.so.patient;

import businesslogic.so.GeneralSO;
import database.DatabaseRepository;
import domain.Patient;

/**
 *
 * @author Mira
 */
public class ViewPatientSO extends GeneralSO{

     private Patient patient = null;
    
    @Override
    protected void checkCondition(Object obj) throws Exception {
        //there is no conditions
    }

    @Override
    protected void executeOperation(Object obj) throws Exception {
        patient = (Patient) DatabaseRepository.getInstance().find((Patient) obj);
    }
    
    public Patient getPatient(){
        return patient;
    }
    
}

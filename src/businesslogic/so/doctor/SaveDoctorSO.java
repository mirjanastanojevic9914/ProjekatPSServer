/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic.so.doctor;

import businesslogic.so.GeneralSO;
import database.DatabaseRepository;
import domain.Doctor;

/**
 *
 * @author Mira
 */
public class SaveDoctorSO extends GeneralSO{
     @Override
    protected void checkCondition(Object obj) throws Exception {
        //conditions checked
    }

    @Override
    protected void executeOperation(Object obj) throws Exception {
        DatabaseRepository.getInstance().save((Doctor) obj);
    }
}

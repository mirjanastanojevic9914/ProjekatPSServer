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
import java.util.List;

/**
 *
 * @author Mira
 */
public class FindDoctorsSO extends GeneralSO{
     private List<GenericDomainObject> lista;
    
    @Override
    protected void checkCondition(Object obj) throws Exception {
        //there is no conditions
    }

    @Override
    protected void executeOperation(Object obj) throws Exception {
        List<Object> parametars = (List<Object>) obj;
        lista = DatabaseRepository.getInstance().returnSearchList((String)parametars.get(0),(GenericDomainObject)parametars.get(1));
    }
    
    public List<GenericDomainObject> getListDoctors(){
        return lista;
    }
}

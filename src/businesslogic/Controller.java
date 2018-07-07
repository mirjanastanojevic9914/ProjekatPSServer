/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic;

import businesslogic.so.GeneralSO;
import businesslogic.so.doctor.FindDoctorsSO;
import businesslogic.so.doctor.GetListOfDoctorsSO;
import businesslogic.so.doctor.LoginDoctorSO;
import businesslogic.so.doctor.SaveDoctorSO;
import businesslogic.so.doctor.UpdateDoctorSO;
import businesslogic.so.doctor.ViewDoctorSO;
import businesslogic.so.doctorType.GetListOfDoctorTypesSO;
import businesslogic.so.patient.FindPatientsSO;
import businesslogic.so.patient.GetListOfPatientsSO;
import businesslogic.so.patient.SavePatientSO;
import businesslogic.so.patient.UpdatePatientSO;
import businesslogic.so.patient.ViewPatientSO;
import domain.Doctor;
import domain.DoctorType;
import domain.GenericDomainObject;
import domain.Patient;
import java.util.List;

/**
 *
 * @author Mira
 */
public class Controller {

    private static Controller instanca;

    private Controller() {
    }

    public static Controller getInstanca() {
        if (instanca == null) {
            instanca = new Controller();
        }

        return instanca;
    }

    public GenericDomainObject getDoctor(Doctor criteriaForSearch) throws Exception {

        LoginDoctorSO so = new LoginDoctorSO();
        so.executionSO(criteriaForSearch);
        return so.getDoctor();
    }

    public List<GenericDomainObject> getAllDoctors() throws Exception {
        GetListOfDoctorsSO so = new GetListOfDoctorsSO();
        so.executionSO(new Doctor());
        return so.getListDoctors();
    }

    public List<GenericDomainObject> getAllPatients() throws Exception {
        GetListOfPatientsSO so = new GetListOfPatientsSO();
        so.executionSO(new Patient());
        return so.getListPatients();
    }

    public List<GenericDomainObject> getAllDoctorTypes() throws Exception {
        GetListOfDoctorTypesSO so = new GetListOfDoctorTypesSO();
        so.executionSO(new DoctorType());
        return so.getListDoctorTypes();
    }

    public void savePatient(Patient patient) throws Exception {
        SavePatientSO so = new SavePatientSO();
        so.executionSO(patient);
    }

    public List<GenericDomainObject> getListPatientsWithCriteria(List<Object> parametars) throws Exception {
        FindPatientsSO so = new FindPatientsSO();
        so.executionSO(parametars);
        return so.getListPatients();
    }

    public Patient findPatientForDetails(Patient patientForFind) throws Exception {
        ViewPatientSO so = new ViewPatientSO();
        so.executionSO(patientForFind);
        return so.getPatient();
    }

    public void updatePatient(Patient patientUpdate) throws Exception {
        UpdatePatientSO so = new UpdatePatientSO();
        so.executionSO(patientUpdate);
    }

    public List<GenericDomainObject> loadListOfDoctorTypes() throws Exception {
        GetListOfDoctorTypesSO so = new GetListOfDoctorTypesSO();
        so.executionSO(new DoctorType());
        return so.getListDoctorTypes();
    }

    public void saveDoctor(Doctor doc)  throws Exception{
        SaveDoctorSO so = new SaveDoctorSO();
        so.executionSO(doc);
    }

    public List<GenericDomainObject> getListDoctorsWithCriteria(List<Object> param) throws Exception{
        FindDoctorsSO so = new FindDoctorsSO();
        so.executionSO(param);
        return so.getListDoctors();
    }

    public Doctor findDoctorForDetails(Doctor doctorForFind) throws Exception{
        ViewDoctorSO so = new ViewDoctorSO();
        so.executionSO(doctorForFind);
        return so.getDoctor();
    }

    public void updateDoctor(Doctor doctorUpdate)  throws Exception{
        UpdateDoctorSO so = new UpdateDoctorSO();
        so.executionSO(doctorUpdate);
    }

}

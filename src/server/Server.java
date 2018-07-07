/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import businesslogic.Controller;
import domain.Doctor;
import domain.GenericDomainObject;
import domain.Patient;
import server.Server;
import start.Start;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.thread.ThreadClient;
import server.thread.ThreadServer;
import transfer.request.RequestObject;
import transfer.response.ResponseObject;
import transfer.util.EnumResponseStatus;
import transfer.util.IOperation;

/**
 *
 * @author Stefana
 */
public class Server {

    ServerSocket serverSocket;
    private static Server instance;
    private ThreadServer threadServer;
    private static List<ThreadClient> clients = new ArrayList<>();

    public static Server getInstance() {
        if (instance == null) {
            instance = new Server();
        }
        return instance;
    }

    public void startServer(int port) throws Exception {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server is started!\nWaiting for clients...");

            threadServer = new ThreadServer(serverSocket);
            threadServer.start();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Server is not started.");
        }
    }

    public void stopServer() throws Exception {
        try {
            threadServer.closeThreadServer();
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Server socket is not closed.");
        }
    }

    public void processClient(Socket socket) throws IOException, ClassNotFoundException {
//        while (true) {
        System.out.println("Waiting for client's request.");
        ObjectInputStream inSocket = new ObjectInputStream(socket.getInputStream());
        Object object = inSocket.readObject();
        RequestObject requestObject = (RequestObject) object;

        ResponseObject responseObject = processRequest(requestObject);
        ObjectOutputStream outSocket = new ObjectOutputStream(socket.getOutputStream());
        outSocket.writeObject(responseObject);
        outSocket.flush();
//        }
    }

    public static List<ThreadClient> getClients() {
        return clients;
    }

    private ResponseObject processRequest(RequestObject requestObject) throws IOException {
        ResponseObject responseObject = new ResponseObject();

        int action = requestObject.getAction();
        try {
            switch (action) {

                case IOperation.LOGIN_DOCTOR:
                    Doctor doctorWithCriteria = (Doctor) requestObject.getRequest();
                    Doctor doctor = (Doctor) Controller.getInstanca().getDoctor(doctorWithCriteria);

                    if (doctor != null) {
                        responseObject.setResponse(doctor);
                        responseObject.setResponseStatus(EnumResponseStatus.OK);
                    } else {
                        responseObject.setResponseStatus(EnumResponseStatus.ERROR);
                    }
                    break;
                case IOperation.SAVE_PATIENT:
                    Patient patient = (Patient) requestObject.getRequest();
                    Controller.getInstanca().savePatient(patient);
                    responseObject.setResponseStatus(EnumResponseStatus.OK);
                    responseObject.setMessage("Succesfully saved patient!");
                    break;
                case IOperation.GET_PATIENTS_WITH_CRITERIA:
                    List<Object> parametars = (List<Object>) requestObject.getRequest();
                    List<GenericDomainObject> listPatientWithCriteria = Controller.getInstanca().getListPatientsWithCriteria(parametars);
                    responseObject.setResponse(listPatientWithCriteria);
                    responseObject.setResponseStatus(EnumResponseStatus.OK);
                    break;
                case IOperation.FIND_PATIENT_FOR_DETAILS:
                    Patient patientForFind = (Patient) requestObject.getRequest();
                    Patient foundPatient = Controller.getInstanca().findPatientForDetails(patientForFind);
                    responseObject.setResponse(foundPatient);
                    responseObject.setResponseStatus(EnumResponseStatus.OK);
                    break;
                case IOperation.UPDATE_PATIENT:
                    Patient patientUpdate = (Patient) requestObject.getRequest();
                    Controller.getInstanca().updatePatient(patientUpdate);
                    responseObject.setResponseStatus(EnumResponseStatus.OK);
                    responseObject.setMessage("Succesfully updated patient!");
                    break;
                case IOperation.GET_LIST_DOCTOR_TYPES_CB:
                    List<GenericDomainObject> listDoctorTypes = Controller.getInstanca().loadListOfDoctorTypes();
                    responseObject.setResponse(listDoctorTypes);
                    responseObject.setResponseStatus(EnumResponseStatus.OK);
                    responseObject.setMessage("Succesfully loaded doctor types!");
                    break;
                case IOperation.SAVE_DOCTOR:
                    Doctor doc = (Doctor) requestObject.getRequest();
                    Controller.getInstanca().saveDoctor(doc);
                    responseObject.setResponseStatus(EnumResponseStatus.OK);
                    responseObject.setMessage("Succesfully saved doctor!");
                    break;
                case IOperation.GET_DOCTORS_WITH_CRITERIA:
                    List<Object> param = (List<Object>) requestObject.getRequest();
                    List<GenericDomainObject> listDoctorsWithCriteria = Controller.getInstanca().getListDoctorsWithCriteria(param);
                    responseObject.setResponse(listDoctorsWithCriteria);
                    responseObject.setResponseStatus(EnumResponseStatus.OK);
                    break;
                case IOperation.FIND_DOCTOR_FOR_DETAILS:
                    Doctor doctorForFind = (Doctor) requestObject.getRequest();
                    Doctor foundDoctor = Controller.getInstanca().findDoctorForDetails(doctorForFind);
                    responseObject.setResponse(foundDoctor);
                    responseObject.setResponseStatus(EnumResponseStatus.OK);
                    break;
                case IOperation.UPDATE_DOCTOR:
                    Doctor doctortUpdate = (Doctor) requestObject.getRequest();
                    Controller.getInstanca().updateDoctor(doctortUpdate);
                    responseObject.setResponseStatus(EnumResponseStatus.OK);
                    responseObject.setMessage("Succesfully updated patient!");
                    break;
                default:
                    responseObject.setResponseStatus(EnumResponseStatus.ERROR);
            }
        } catch (Exception ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            responseObject.setResponseStatus(EnumResponseStatus.ERROR);
        }
        return responseObject;
    }
}

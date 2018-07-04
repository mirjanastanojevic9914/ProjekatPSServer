/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic.so;

import database.DatabaseRepository;

/**
 *
 * @author Mira
 */
public abstract class GeneralSO {
    
     public final synchronized void executionSO(Object obj) throws Exception {
        try {
            loadDriver();
            openConnection();
            checkCondition(obj);
            executeOperation(obj);
            commitTransaction();
        } catch (Exception ex) {
            rollbackTransaction();
            throw ex;
        } finally {
            closeConnection();
        }
        
    }

    private void loadDriver() throws Exception {
        DatabaseRepository.getInstance().loadDriver();
    }

    private void openConnection() throws Exception {
        DatabaseRepository.getInstance().openConnection();
    }

    
    protected abstract void checkCondition(Object obj) throws Exception;

    
    protected abstract void executeOperation(Object obj) throws Exception;

    private void commitTransaction() throws Exception {
        DatabaseRepository.getInstance().commitTransaction();
    }

    private void rollbackTransaction() throws Exception {
        DatabaseRepository.getInstance().rollbackTransaction();
    }

    private void closeConnection() throws Exception {
        DatabaseRepository.getInstance().closeConnection();
    }
}

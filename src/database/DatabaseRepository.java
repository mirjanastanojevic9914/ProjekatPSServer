/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import domain.GenericDomainObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mira
 */
public class DatabaseRepository {

    
    
    Connection connection;
    private static DatabaseRepository instance;
     
    public DatabaseRepository() {
    }
    
    
    public static DatabaseRepository getInstance() {
        if (instance == null) {
            instance = new DatabaseRepository();
        }
        return instance;
    }
    
    

    public void loadDriver() throws Exception {
        try {
            //mozemo da prosledimo url kao parametar da obezbedimo podrsku razlicitim drajverima?
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new Exception("Driver wasn't loaded successfully!", ex);
        }
    }

    public void openConnection() throws Exception {
        try {
             DatabaseResources dbUtil = new DatabaseResources();

            String url = dbUtil.getUrl();
            String username = dbUtil.getUsername();
            String password = dbUtil.getPassword();

            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Connection can't be established!", ex);
        }
    }

    public void closeConnection() throws Exception {
        try {
            connection.close();
        } catch (SQLException ex) {
            throw new Exception("Connection was't closed successfully!", ex);
        }
    }

    public void commitTransaction() throws Exception {
        try {
            connection.commit();
        } catch (SQLException ex) {
            throw new Exception("Transaction wasn't committed successfully!", ex);
        }
    }

    public void rollbackTransaction() throws Exception {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            throw new Exception("Transaction wasn't rollback successfully", ex);
        }
    }

    public List<GenericDomainObject> returnList(GenericDomainObject gdo) throws Exception {
        try {
            List<GenericDomainObject> list;
            String sql = "SELECT * FROM " + gdo.returnTableName()+ "" + gdo.returnTableWithJoinCondition();
            System.out.println(sql);

            Statement sqlStatement = connection.createStatement();
            ResultSet rs = sqlStatement.executeQuery(sql);

            list = gdo.fill(rs);

            rs.close();
            sqlStatement.close();

            return list;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Loading objects was unsuccessful!", ex);
        }
    }

    public void save(GenericDomainObject gdo) throws Exception {
        try {
            String sql = "INSERT INTO " + gdo.returnTableName()+ "(name, surname, gender, dateBirth, mobileNumber) VALUES (" + gdo.returnInsertValues()+ ")";
            System.out.println(sql);

            Statement sqlStatement = connection.createStatement();
            sqlStatement.executeUpdate(sql);

            sqlStatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Saving objects was unsuccessful!", ex);
        }
    }

    public GenericDomainObject find(GenericDomainObject gdo) throws Exception {
        try {
            String sql = "SELECT * FROM " + gdo.returnTableName() + " " + gdo.returnTableWithJoinCondition() + " WHERE " + gdo.returnConditionWithID();
            System.out.println(sql);

            Statement sqlStatement = connection.createStatement();
            ResultSet rs = sqlStatement.executeQuery(sql);

            GenericDomainObject gdo2 = gdo.convert(rs);

            sqlStatement.close();

            return gdo2;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Loading objects was unsuccessful!", ex);
        }
    }

    public void update(GenericDomainObject gdo) throws Exception {
        try {
            
            
            
            String sql = "UPDATE " + gdo.returnTableName() + " SET " + gdo.returnUpdateValues() + " WHERE " + gdo.returnConditionWithID();
            System.out.println(sql);

            Statement sqlStatement = connection.createStatement();
            sqlStatement.executeUpdate(sql);

            sqlStatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Saving objects was unsuccessful!", ex);
        }
    }

    public void delete(GenericDomainObject gdo) throws Exception {
        try {
            String sql = "DELETE FROM " + gdo.returnTableName() + " WHERE " + gdo.returnConditionWithID();
            System.out.println(sql);

            Statement sqlStatement = connection.createStatement();
            sqlStatement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Deleting objects was unsuccessful!", ex);
        }
    }

    public List<GenericDomainObject> returnSearchList(String criteria, GenericDomainObject gdo) throws Exception {
        try {
            List<GenericDomainObject> list;
            String sql = "SELECT * FROM " + gdo.returnTableName() + "" + gdo.returnTableWithJoinCondition() + " " + gdo.returnSearchCriteria(criteria);
            System.out.println(sql);

            Statement sqlStatement = connection.createStatement();
            ResultSet rs = sqlStatement.executeQuery(sql);

            list = gdo.fill(rs);

            rs.close();
            sqlStatement.close();

            return list;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Loading objects was unsuccessful!", ex);
        }
    }
     public List<GenericDomainObject> getListWithCriteria(String criteria, GenericDomainObject gdo) throws Exception{
         try {
            List<GenericDomainObject> list;
            String sql = "SELECT * FROM " + gdo.returnTableName()+" "+gdo.returnTableWithJoinCondition()+" "+gdo.returnSearchCriteria(criteria);
            System.out.println(sql);
            
            Statement sqlStatement = connection.createStatement();
            ResultSet rs = sqlStatement.executeQuery(sql);
            
            list = gdo.fill(rs);
            
            rs.close();
            sqlStatement.close();
            
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Loading objects was unsuccessful!", ex);
        }
    }
    
     
}

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
public class DatabaseConnection {
    private final Connection connection;
    private static DatabaseConnection instance;
    
    private DatabaseConnection() throws Exception{

        DatabaseResources dbr = new DatabaseResources();
        connection = DriverManager.getConnection(dbr.getUrl(), dbr.getUsername(), dbr.getPassword());
        System.out.println("Connection has been established!");
    }

    public Connection getConnection() {
        return connection;
    }
    
    public static DatabaseConnection getInstance() throws Exception{
        if(instance == null){
            instance = new DatabaseConnection();
        }
        return instance;
    }

   
}

package com.dcdigital.testproject.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Deni
 */

public abstract class AbstractDao {
    
    private static final String DB_DRIVER = "org.postgresql.Driver";
    private static final String DB_CONNECTION = "jdbc:postgresql://myserver:3434/template1";
    private static final String DB_USER = "user";
    private static final String DB_PASSWORD = "password";
    
    
    public static void main(String[] argv) {

        try {
            System.out.println("You did it!!!!");
            System.out.println(selectRecordsFromDbUserTable( "SELECT * FROM COMPANY"));

            } catch (SQLException e) {

                System.out.println(e.getMessage());

            }

	}
    
    private static Connection getDBConnection() {
        Connection dbConnection = null;

        try {
            Class.forName(DB_DRIVER);
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;
        } catch (SQLException|ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        
        return dbConnection;
    }
    
    public static ResultSet selectRecordsFromDbUserTable(String selectTableSQL) throws SQLException {
        try(Connection dbConnection = getDBConnection();
            Statement statement = dbConnection.createStatement();) {
            
            System.out.println(selectTableSQL);

            return statement.executeQuery(selectTableSQL);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } 
    }         
    
    protected abstract Object processResultSet(ResultSet rs);
}

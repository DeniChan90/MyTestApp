package com.dcdigital.testproject.dao;

import com.dcdigital.testproject.dto.DTO;
import com.dcdigital.testproject.dto.UserDMO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Deni
 * @param <T>
 */
public abstract class AbstractDao<T extends DTO> {

    private static final String DB_DRIVER = "org.postgresql.Driver";
    private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/myDB";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "root";

    public static void main(String[] argv) {

        try {
            System.out.println("You did it!!!!");
            System.out.println(selectRecordsFromDbUserTable("SELECT * FROM public.\"USER\""));

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
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return dbConnection;
    }

    public static UserDMO selectRecordsFromDbUserTable(String selectTableSQL) throws SQLException {
        try (Connection dbConnection = getDBConnection();
                Statement statement = dbConnection.createStatement();) {

            System.out.println(selectTableSQL);

            UserDMO user = new UserDMO();
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
            }
            return user;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}

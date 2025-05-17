/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author uj265
 */
public class Database {

    private static final String DB_USERNAME = "pdc";
    private static final String DB_PASSWORD = "pdc";
    private static final String URL = "jdbc:derby:HotelDB;create=true";

    private static Database dbInstance = null;
    private static Connection conn;

    private Database() {
        try {
            conn = DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println("DB connection failed: " + e.getMessage());
        }
    }

    public static synchronized Database getInstance() {
        if (dbInstance == null) {
            dbInstance = new Database();
        }
        dbsetup();
        return dbInstance;
    }

    public Connection getConnection() {
        return conn;
    }

    public void close() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Error closing DB connection.");
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public static void dbsetup() {
        try {
            Statement statement = conn.createStatement();

            if (!checkTableExisting("ROOMS")) {
                statement.executeUpdate("CREATE TABLE ROOMS (ROOM_NUMBER INT PRIMARY KEY, ROOM_TYPE VARCHAR(20), "
                        + "BASE_RATE DOUBLE, RATE_PER_PERSON DOUBLE, MAX_GUESTS INT)");
            }
            if (!checkTableExisting("BOOKINGS")) {
                statement.executeUpdate("CREATE TABLE BOOKINGS (BOOKING_NUMBER INT PRIMARY KEY, CHECK_IN DATE, "
                        + "CHECK_OUT DATE, ROOM_NUMBER INT, NUMBER_OF_GUESTS INT, FIRST_NAME VARCHAR(40), "
                        + "LAST_NAME VARCHAR(50), PHONE_NUMBER VARCHAR(11), PASSWORD INT)");
            }
            statement.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    private static boolean checkTableExisting(String newTableName) {
        boolean flag = false;
        try {
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rsDBMeta = dbmd.getTables(null, null, newTableName, new String[]{"TABLE"});
            flag = rsDBMeta.next();
            rsDBMeta.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return flag;
    }
}

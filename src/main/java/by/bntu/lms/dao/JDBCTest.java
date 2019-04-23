package by.bntu.lms.dao;

import java.sql.*;

public class JDBCTest {
    /*public static void main(String[] args) {
        String connectionUrl =
                "jdbc:sqlserver://localhost;databaseName=TestDB;integratedSecurity=true";

        try (Connection con = DriverManager.getConnection(connectionUrl);
             Statement stmt = con.createStatement();) {
            String SQL = "SELECT TOP 10 * FROM Person;";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                System.out.println(rs.getString("Name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    public static void main(String[] args) {

        Connection conn = null;

        try {
            String dbURL = "jdbc:sqlserver://172.16.11.72\\LMSPlatform_Test_2018";
            String user = "lmsuser2";
            String pass = "Hawk107229";
            conn = DriverManager.getConnection(dbURL, user, pass);
            if (conn != null) {
                DatabaseMetaData dm =  conn.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
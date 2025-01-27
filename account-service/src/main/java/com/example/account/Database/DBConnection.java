package com.example.account.Database;

import java.sql.*;

public class DBConnection {


    final String url = "jdbc:sqlserver://demo-bank.database.windows.net:1433;database=demoBank;user=rootBank@demo-bank;password={r00tBank123!};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30";

    public void testConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(url);

        Statement statement = conn.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM tipo_cuenta");

        /*while (resultSet.next())
        {
            String columnValue = resultSet.getString("id_tipo_cuenta");
            System.out.println("id: " + columnValue);
        }*/
        conn.close();
    }
}
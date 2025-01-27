package com.example.transactions.database;

import java.sql.*;

public class DBConnection {

    final String url = "DB URL";
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
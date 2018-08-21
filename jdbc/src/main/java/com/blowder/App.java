package com.blowder;

import java.sql.*;

public class App {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        System.out.println("Username = " + args[0]);
        System.out.println("Password = " + args[1]);

        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5439/my_db";
        try (Connection conn = DriverManager.getConnection(url, args[0], args[1])) {
            try (Statement stmt = conn.createStatement()) {
                conn.setAutoCommit(false);
                try (ResultSet result = stmt.executeQuery("select * from products where id < 10")) {
                    while (result.next()) {
                        System.out.println("Simple query:");
                        print(result);
                    }
                    conn.commit();
                }finally{
                    conn.rollback();
                }
            }
            conn.setAutoCommit(true);
            try (PreparedStatement stmt = conn.prepareStatement("select * from products where id < ?")) {
                stmt.setInt(1, 10);
                try (ResultSet result = stmt.executeQuery()) {
                    while (result.next()) {
                        System.out.println("Parameterized query:");
                        print(result);
                    }
                }
            }
        }
    }

    private static void print(ResultSet product) throws SQLException {
        System.out.println("id=" + product.getString("id") + "|number=" + product.getString("number") + "|sku="
                + product.getString("sku"));
    }
}

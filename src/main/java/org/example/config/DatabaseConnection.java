package org.example.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String URL = "jdbc:h2:./BDuniversidad";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void CrearTablas() throws SQLException {
        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = conexion.createStatement();
             BufferedReader br = new BufferedReader(new FileReader("src/main/resources/script.sql"))) {

            StringBuilder sql = new StringBuilder();
            String linea;
            while ((linea = br.readLine()) != null) {
                sql.append(linea).append("\n");
            }

            statement.execute(sql.toString());
            System.out.println("Tablas creadas exitosamente.");

        } catch (Exception e) {
            System.err.println("Error al crear las tablas: " + e.getMessage());
        }
    }
}

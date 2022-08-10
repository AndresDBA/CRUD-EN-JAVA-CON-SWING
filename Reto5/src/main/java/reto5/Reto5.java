package reto5;

import java.sql.Connection;
import java.sql.DriverManager;

public class Reto5 {

    public static Connection conexion;

    public static Connection crearConexion() {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/reto5", "root", "1234");
            System.out.println("Conectado");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Problema de conexión");
        }
        return conexion;
    }
    
    public static void main(String[] args) {
        crearConexion();
    }
}


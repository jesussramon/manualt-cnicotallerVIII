package py.edu.facitec.orionf.app;

import java.sql.Connection;

import py.edu.facitec.orionf.utilidades.Conexion;

public class PruebaConexion {

    public static void main(String[] args) {

        try (Connection conexion = Conexion.conectar()) {

            System.out.println("=================================");
            System.out.println("CONEXIÓN EXITOSA");
            System.out.println("Base de datos: bd_orionf");
            System.out.println("=================================");

        } catch (Exception e) {

            System.out.println("ERROR AL CONECTAR CON LA BASE DE DATOS");
            e.printStackTrace();
        }
    }
}
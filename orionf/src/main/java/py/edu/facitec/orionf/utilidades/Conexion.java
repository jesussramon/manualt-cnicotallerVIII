package py.edu.facitec.orionf.utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL = "jdbc:postgresql://localhost:5433/bd_orionf";
    private static final String USUARIO = "postgres";
    private static final String PASSWORD = System.getenv("ORIONF_DB_PASSWORD");

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }
}
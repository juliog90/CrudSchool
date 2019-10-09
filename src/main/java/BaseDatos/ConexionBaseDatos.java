package BaseDatos;

import java.sql.*;

// Clase de conexion
public class ConexionBaseDatos {

    // Creamos y regresamos conexion
    public static Connection ObtenerConexion() {
        // Aqui preparamos tanto los datos del servidor como del usuario de
        // Mysql
        String url = "jdbc:mysql://localhost:3306/escuela";
        String usuario = "root";
        String clave = "";
        // Intentamos conectarnos y regresar la conexion
        try{
           Connection conexion = DriverManager.getConnection(
                    url, usuario, clave);
           return conexion;
        }catch(SQLException e){
            // Imprimimos excepcion en consola
            System.out.println(e.getMessage());
        }
        return null;
    }
}

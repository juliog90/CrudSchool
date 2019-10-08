package BaseDatos;

import java.sql.*;

public class ConexionBaseDatos {

    public static Connection ObtenerConexion() {
        String url = "jdbc:mysql://localhost:3306/escuela";
        String usuario = "root";
        String clave = "";
        Connection conexion = null;
        try{
            conexion = DriverManager.getConnection(
                    url, usuario, clave);
        }catch(Exception e){ System.out.println(e.getMessage());}

        return conexion;
    }
}

package Models;

import BaseDatos.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Alumno {
    private Integer id;

    public Alumno() {
    }

    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Grupo grupo;

    public Alumno(Integer id, String nombre, String apellidoPaterno, String apellidoMaterno, Grupo grupo) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.grupo = grupo;
    }

    public Alumno(Integer id) {
        Connection conexion = ConexionBaseDatos.ObtenerConexion();
        if(conexion != null) {
            try {
                String sql = "call consultaAlumno(?)";
                PreparedStatement comando = conexion.prepareStatement(sql);
                comando.setInt(1, id);
                ResultSet tabla = comando.executeQuery();
                this.id = id;
                this.nombre = tabla.getString("nombre");
                this.apellidoPaterno= tabla.getString("apellido_paterno");
                this.apellidoMaterno = tabla.getString("apellido_materno");
                this.nombre = tabla.getString("nombre");
                // falta sp
//                Carrera cursoCarrera  = new Carrera();
//                Curso grupoCurso = new Curso();
//                Grupo alumnoGrupo = new Grupo();
                this.grupo = null;

                conexion.close();
            } catch(SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Problema en Base de Datos");
        }
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public Boolean agregarAlumno() {
        Connection conexion = ConexionBaseDatos.ObtenerConexion();
        if(conexion != null) {
            try {
                String sql = "call agregar(?, ?, ?, ?)";
                PreparedStatement comando = conexion.prepareStatement(sql);
                comando.setString(1, this.nombre);
                comando.setString(2, this.apellidoPaterno);
                comando.setString(3, this.apellidoPaterno);
                comando.setInt(4, this.grupo.getId());
                Boolean resultado = comando.execute();
                conexion.close();
                return resultado;
            } catch(SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Problema en Base de Datos");
        }
        return false;
    }

    public Boolean borrarAlumno() {
        Connection conexion = ConexionBaseDatos.ObtenerConexion();
        if(conexion != null) {
            try {
                String sql = "call borrar(?)";
                PreparedStatement comando = conexion.prepareStatement(sql);
                comando.setInt(1, this.id);
                Boolean resultado = comando.execute();
                conexion.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }

        return false;
    }

    public Boolean actualizarAlumno() {
        Connection conexion = ConexionBaseDatos.ObtenerConexion();
        if(conexion != null) {
            try {
                String sql = "call actualizar(?, ?, ?, ?, ?)";
                PreparedStatement comando = conexion.prepareStatement(sql);
                comando.setString(1, this.nombre);
                comando.setString(2, this.apellidoPaterno);
                comando.setString(3, this.apellidoPaterno);
                comando.setInt(4, this.grupo.getId());
                comando.setInt(5, this.id);
                Boolean resultado = comando.execute();
                conexion.close();
                return resultado;
            } catch(SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Problema en Base de Datos");
        }
        return false;
    }

    public static ArrayList<Alumno> getAll() {
        ArrayList<Alumno> alumnos = new ArrayList<>();
        Connection conexion = ConexionBaseDatos.ObtenerConexion();
        if(conexion != null) {
            try {
                String sql = "call todosAlumnos(?)";
                PreparedStatement comando = conexion.prepareStatement(sql);
                ResultSet tabla = comando.executeQuery();
                while(tabla.next()) {
                    Alumno tempAlumno = new Alumno();
                    tempAlumno.id = tabla.getInt("id");
                    tempAlumno.nombre = tabla.getString("nombre");
                    tempAlumno.apellidoPaterno= tabla.getString("apellido_paterno");
                    tempAlumno.apellidoMaterno = tabla.getString("apellido_materno");
                    tempAlumno.nombre = tabla.getString("nombre");
                    // falta sp
//                Carrera cursoCarrera  = new Carrera();
//                Curso grupoCurso = new Curso();
//                Grupo alumnoGrupo = new Grupo();
                    tempAlumno.grupo = null;
                    alumnos.add(tempAlumno);
                }
                conexion.close();
            } catch(SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Problema en Base de Datos");
        }

        return alumnos;

    }
}

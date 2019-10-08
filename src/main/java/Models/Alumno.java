package Models;

import BaseDatos.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Alumno {
    private Integer id;
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

    public Alumno(String nombre, String apellidoPaterno, String apellidoMaterno, Grupo grupo) {
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

                if (tabla.next()) {
                    this.id = tabla.getInt("id");
                    this.nombre = tabla.getString("nombre");
                    this.apellidoPaterno= tabla.getString("apellido_paterno");
                    this.apellidoMaterno = tabla.getString("apellido_materno");
                    Carrera cursoCarrera  = new Carrera(tabla.getInt("carrera_id"), tabla.getString("carrera"));
                    Curso grupoCurso = new Curso(tabla.getInt("curso_id"), tabla.getString("curso"), cursoCarrera);
                    this.grupo = new Grupo(tabla.getInt("grupo_id"), tabla.getString("grupo"), grupoCurso);
                }
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Boolean agregar() {
        Connection conexion = ConexionBaseDatos.ObtenerConexion();
        if(conexion != null) {
            try {
                String sql = "call agregar(?, ?, ?, ?)";
                PreparedStatement comando = conexion.prepareStatement(sql);
                comando.setString(1, this.nombre);
                comando.setString(2, this.apellidoPaterno);
                comando.setString(3, this.apellidoMaterno);
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

    public Boolean borrar() {
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

    public Boolean actualizar() {
        Connection conexion = ConexionBaseDatos.ObtenerConexion();
        if(conexion != null) {
            try {
                String sql = "call actualizar(?, ?, ?, ?, ?)";
                PreparedStatement comando = conexion.prepareStatement(sql);
                comando.setString(1, this.nombre);
                comando.setString(2, this.apellidoPaterno);
                comando.setString(3, this.apellidoMaterno);
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

    public static ArrayList<Alumno> obtenerTodos() {
        ArrayList<Alumno> alumnos = new ArrayList<>();
        Connection conexion = ConexionBaseDatos.ObtenerConexion();
        if(conexion != null) {
            try {
                String sql = "call todosAlumnos()";
                PreparedStatement comando = conexion.prepareStatement(sql);
                ResultSet tabla = comando.executeQuery();

                while(tabla.next()) {
                    Carrera cursoCarrera  = new Carrera(tabla.getInt("carrera_id"), tabla.getString("carrera"));
                    Curso grupoCurso = new Curso(tabla.getInt("curso_id"), tabla.getString("curso"), cursoCarrera);
                    Grupo grupoAlumno = new Grupo(tabla.getInt("grupo_id"), tabla.getString("grupo"), grupoCurso);
                    Alumno tempAlumno = new Alumno(tabla.getInt("id"), tabla.getString("nombre"),
                            tabla.getString("apellido_paterno"), tabla.getString("apellido_materno"), grupoAlumno);
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

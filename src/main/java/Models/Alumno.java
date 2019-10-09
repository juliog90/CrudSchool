package Models;

import BaseDatos.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// Clase Crud
public class Alumno {
    // Miembros de clase
    private Integer id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Grupo grupo;

    // Contructor parametrizado con id
    public Alumno(Integer id, String nombre, String apellidoPaterno, String apellidoMaterno, Grupo grupo) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.grupo = grupo;
    }

    // Constructor sin id
    public Alumno(String nombre, String apellidoPaterno, String apellidoMaterno, Grupo grupo) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.grupo = grupo;
    }

    // Constructor con id para base de datos
    // Se mapea el registro que tenga ese id
    // en un nuevo alumno
    public Alumno(Integer id) {
        // Tomamos conexion
        Connection conexion = ConexionBaseDatos.ObtenerConexion();
        // Si la conexion se establecio
        if(conexion != null) {
            // Intentamos obtener datos de la bd
            try {
                // Codigo sql que llama una stored procedure
                // ? significa el parametro en este caso la id
                String sql = "call consultaAlumno(?)";
                // Comando preparado
                PreparedStatement comando = conexion.prepareStatement(sql);
                // Mandamos la id que es el argumento recibido en este constructor
                comando.setInt(1, id);
                // Guardamos el resultado en un objeto de resultados
                ResultSet tabla = comando.executeQuery();

                // Es necesario este codigo, porque  debido a como funciona JDBC
                // este resultado se empieza a leer antes de la primera fila
                // entonces necesitamos con el next() moverlo a la primero
                // es un metodo booleano que verifica que la sigueinte fila
                // exista como se puede ver aqu
                if (tabla.next()) {
                    // Mapeo de este objeto
                    this.id = tabla.getInt("id");
                    this.nombre = tabla.getString("nombre");
                    this.apellidoPaterno= tabla.getString("apellido_paterno");
                    this.apellidoMaterno = tabla.getString("apellido_materno");
                    // Objetos intermedios
                    Carrera cursoCarrera  = new Carrera(tabla.getInt("carrera_id"), tabla.getString("carrera"));
                    Curso grupoCurso = new Curso(tabla.getInt("curso_id"), tabla.getString("curso"), cursoCarrera);
                    this.grupo = new Grupo(tabla.getInt("grupo_id"), tabla.getString("grupo"), grupoCurso);
                }
                // Cerramos conexion
                conexion.close();
            } catch(SQLException e) {
                // Error en la consulta imprimimos mensaje
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Problema en Base de Datos");
        }
    }

    // Getters
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

    // Setters
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

    // Agregar otro alumno a la tabla alumnos
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
                // Como no es una consulta este regresa un valor booleano
                // entonces este metodo regresa ese resultado si fue exitoso
                // el agregar este registro/alumno es un verdadero si no
                // falso
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

    // Similar al de agreagar, aqui mandamos borrar un alumno/registro
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

    // Actualizar alumno
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

    // Este metodo regresa una lista de todos los alumnos en la
    // base de datos
    public static ArrayList<Alumno> obtenerTodos() {
        // Preparamos lista de alumnos vacia
        ArrayList<Alumno> alumnos = new ArrayList<>();
        Connection conexion = ConexionBaseDatos.ObtenerConexion();
        if(conexion != null) {
            try {
                String sql = "call todosAlumnos()";
                PreparedStatement comando = conexion.prepareStatement(sql);
                ResultSet tabla = comando.executeQuery();

                // Similar al anterior pero aqui es un ciclo porque
                // se va moviendo por las filas del resultado y para hasta que
                // next regrese falso o en otras palabras hasta que no ecuentre
                // registros
                while(tabla.next()) {
                    // Mapeo de objetos
                    Carrera cursoCarrera  = new Carrera(tabla.getInt("carrera_id"), tabla.getString("carrera"));
                    Curso grupoCurso = new Curso(tabla.getInt("curso_id"), tabla.getString("curso"), cursoCarrera);
                    Grupo grupoAlumno = new Grupo(tabla.getInt("grupo_id"), tabla.getString("grupo"), grupoCurso);
                    Alumno tempAlumno = new Alumno(tabla.getInt("id"), tabla.getString("nombre"),
                            tabla.getString("apellido_paterno"), tabla.getString("apellido_materno"), grupoAlumno);

                    // Agregamos alumno generado a la lista antes creada
                    alumnos.add(tempAlumno);
                }
                conexion.close();
            } catch(SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Problema en Base de Datos");
        }
        // Regresamos la lista
        return alumnos;
    }
}

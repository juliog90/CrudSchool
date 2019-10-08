package Models;

public class Grupo {
    private Integer id;
    private String nombre;
    private Curso curso;

    public Grupo(Integer id, String nombre, Curso curso) {
        this.id = id;
        this.nombre = nombre;
        this.curso = curso;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Curso getCurso() {
        return curso;
    }
}

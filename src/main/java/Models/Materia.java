package Models;

public class Materia {
    private Integer id;
    private String nombre;

    public Materia(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}

package Models;

public class Carrera {
    private Integer id;
    private String nombre;

    public Carrera(Integer id, String nombre) {
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

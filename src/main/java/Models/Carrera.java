package Models;

public class Carrera {
    private Integer id;
    private Integer nombre;

    public Carrera(Integer id, Integer nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public Integer getNombre() {
        return nombre;
    }
}

package Models;

public class Curso {
    private Integer id;
    private String nombre;
    private Carrera carrera;

    public Curso(Integer id, String nombre, Carrera carrera) {
        this.id = id;
        this.nombre = nombre;
        this.carrera = carrera;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Carrera getCarrera() {
        return carrera;
    }
}

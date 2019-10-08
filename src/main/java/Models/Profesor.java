package Models;

public class Profesor {
    private Integer id;
    private String nombre;
    private String grado;

    public Profesor(Integer id, String nombre, String grado) {
        this.id = id;
        this.nombre = nombre;
        this.grado = grado;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getGrado() {
        return grado;
    }
}

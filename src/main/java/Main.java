import Models.Alumno;

public class Main {
    public static void main(String[] args) {
//        Alumno anto = new Alumno(1);
//        Alumno javi = new Alumno("Javier", "Valencia", "Melchor", anto.getGrupo());
//        javi.agregar();
//        Alumno.obtenerTodos().forEach(alumno -> System.out.println(alumno.getNombre()));
        Alumno javiBD = new Alumno(7);
        System.out.println(javiBD.getNombre());
        System.out.println(javiBD.getApellidoPaterno());
        System.out.println(javiBD.getApellidoMaterno());
        System.out.println(javiBD.getGrupo().getNombre());
    }
}

import Models.Alumno;
import Models.Grupo;

import java.util.ArrayList;
import java.util.Scanner;

// Programa para probar el crud de Alumnos, falto la validacion :(
public class Main {
    public static void main(String[] args) {
        ArrayList<Alumno> alumnos = Alumno.obtenerTodos();
        Scanner entrada = new Scanner(System.in);

        // Mostramos alumnos
        System.out.println("*****************************");
        System.out.println("***Manejador de Alumnos******");
        System.out.println("*****************************");
        System.out.println("*********Alumnos*************");

        alumnos.forEach(alumno -> System.out.println(alumno.getId() + ") " + alumno));

        // Preguntamos que quiere hacer
        System.out.println("\nQue quieres hacer?");
        System.out.println("1) Ver Detalle de Alumno");
        System.out.println("2) Agregar Alumno");
        System.out.println("3) Actualizar Alumno");
        System.out.println("4) Borrar Alumno");

        System.out.println("Escribe la opcion: ");
        int opcion = entrada.nextInt();
        int seleccion;

        // Dependiendo la opcion elegida se ejecuta al accion
        switch (opcion) {
            case 1:
                // Se el muestra los atributos del alumno seleccionado
                System.out.println("Escribe el numero del alumno que quieras ver a detalle?");
                seleccion = entrada.nextInt();
                Alumno alumno = filtrarAlumno(seleccion, alumnos);
                if (alumno != null) {
                    System.out.println("Nombre: " + alumno.getNombre());
                    System.out.println("Apellido Paterno: " + alumno.getApellidoPaterno());
                    System.out.println("Apellido Materno: " + alumno.getApellidoMaterno());
                    System.out.println("Grupo: " + alumno.getGrupo().getNombre());
                } else {
                    System.out.println("Alumno no encontrado");
                }
                break;
            case 2:
                // Agregamos nuevo alumno
                System.out.println("Introduce la informacion del nuevo alumno");
                System.out.print("Nombre: ");
                String nombre = entrada.next();
                System.out.println("Apellido Paterno: ");
                String paterno = entrada.next();
                System.out.println("Apellido Materno: ");
                String materno = entrada.next();
                // Temporal
                Grupo grupo = new Alumno(1).getGrupo();

                Alumno nuevoAlumno = new Alumno(nombre, paterno, materno, grupo);

                System.out.println("Seguro quieres agregar a " + nuevoAlumno + " S/N");
                if(entrada.next().equals("S")) {
                    nuevoAlumno.agregar();
                } else {
                    System.out.println("No se agrego al nuevo alumno");
                }
                break;
            case 3:
                System.out.println("Escribe el numero del alumno que quieras actualizar");
                seleccion = entrada.nextInt();
                Alumno alumnoActualizar = filtrarAlumno(seleccion, alumnos);
                if (alumnoActualizar != null) {
                    String nombreAnterior = alumnoActualizar.toString();
                    System.out.println("Nombre: " + alumnoActualizar.getNombre());
                    System.out.println("Nuevo Nombre: ");
                    alumnoActualizar.setNombre(entrada.next());
                    System.out.println("Apellido Paterno: " + alumnoActualizar.getApellidoPaterno());
                    System.out.println("Nuevo Apellido Paterno: ");
                    alumnoActualizar.setApellidoPaterno(entrada.next());
                    System.out.println("Apellido Materno: " + alumnoActualizar.getApellidoMaterno());
                    System.out.println("Nuevo Apellido Materno: ");
                    alumnoActualizar.setApellidoMaterno(entrada.next());

                    System.out.println("Seguro quieres actualizar a " + nombreAnterior + " S/N");
                   if(entrada.next().equals("S")) {
                       alumnoActualizar.actualizar();
                   } else {
                       System.out.println("No se actualizo a " + nombreAnterior);
                   }
                }
                break;
            case 4:
                System.out.println("Escribe el numero del alumno que quieras borrar");
                seleccion = entrada.nextInt();
                Alumno alumnoBorrar = filtrarAlumno(seleccion, alumnos);
                if (alumnoBorrar != null) {
                    System.out.println("Seguro quieres borrar a " + alumnoBorrar + "S/N");
                    if(entrada.next().equals("S")) {
                        alumnoBorrar.borrar();
                    } else {
                        System.out.println("No se borro a: " + alumnoBorrar);
                    }
                }
                break;
            default:
                System.out.println("Escribe una opcion valida");
                break;
        }
    }

    // Filtramos alumno por id escogida
    private static Alumno filtrarAlumno(Integer alumnoEscogido, ArrayList<Alumno> alumnos) {
        for (Alumno alumno : alumnos) {
            if (alumnoEscogido.equals(alumno.getId())) {
                return alumno;
            }
        }
        return null;
    }
}

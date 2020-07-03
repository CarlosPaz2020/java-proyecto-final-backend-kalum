package edu.carlospaz.kalum;

import java.util.List;
import java.util.UUID;

import javax.persistence.Query;

import edu.carlospaz.kalum.db.Conexion;
import edu.carlospaz.kalum.models.Alumno;
import edu.carlospaz.kalum.models.CarreraTecnica;
import edu.carlospaz.kalum.models.Clase;
import edu.carlospaz.kalum.models.Horario;
import edu.carlospaz.kalum.models.Instructor;
import edu.carlospaz.kalum.models.Modulo;
import edu.carlospaz.kalum.models.Salon;

/**
 * Hello world!
 */
public class AppConsola {
    //public static void main(String[] args) {
        //Conexion conexion = new Conexion();
        // for (Object registro : conexion.findAll("Modulo.findAll")) {
        //     Modulo carrera = (Modulo) registro;
        //     //System.out.println(carrera.getCodigoCarrera() + " " + carrera.getNombre());
        //     System.out.println(registro);
        // }

        // ****Agregar registro
        //Clase nuevo = new Clase();
        
        // nuevo.setClaseId(UUID.randomUUID().toString());
        // nuevo.setDescripcion("Prueba JPA");
        // nuevo.setCiclo(2021);
        // nuevo.setCupoMinimo(50);
        // nuevo.setCupoMaximo(100);
        
        // Query query = conexion.getInstancia().getEntityManager()
        //     .createQuery("select s.salonId from Salon s where s.nombreSalon =:nombreSalon");
        // query.setParameter("nombreSalon", "C28");
        // List<Salon> resultado = (Salon) query.getResultList();
        //nuevo.setSalon(resultado);
        //System.out.println(resultado);

        // Query query1 = conexion.getInstancia().getEntityManager()
        //     .createQuery("select h.horarioId from Horario h where h.horarioInicio =:horarioInicio");
        // query1.setParameter("horarioInicio", "08:00:00");
        // List<Horario> resultado1 = query.getResultList();
        // nuevo.setHorario((Horario) resultado1);
        // System.out.println(resultado1);

        // Query query2 = conexion.getInstancia().getEntityManager()
        //     .createQuery("select i.instructorId from Instructor i where i.apellidos =:apellidos");
        // query2.setParameter("apellidos", "Tumax");
        // List<Instructor> resultado2 = query.getResultList();
        // nuevo.setInstructor((Instructor) resultado2);
        // System.out.println(resultado2);

        // Query query3 = conexion.getInstancia().getEntityManager()
        //     .createQuery("select ct.codigoCarrera from CarreraTecnica ct where ct.nombre =:nombre");
        // query3.setParameter("nombre", "DESARROLLO DE APLICACIONES FULL STACK CON JAVA");
        // List<CarreraTecnica> resultado3 = query.getResultList();
        // nuevo.setCarrera((CarreraTecnica) resultado2);
        // System.out.println(resultado3);
        
        // Conexion.getInstancia().agregar(nuevo);

        // for (Object registro : conexion.findAll("Clase.findAll")) {
        //     Clase clase = (Clase) registro;
        //     System.out.println(clase.getDescripcion() + " " + clase.getInstructor().getApellidos() + " " + clase.getCantidadAsignaciones());

            // }
            //System.out.println(Conexion.getInstancia().buscar(Salon.class, "c2a0ac87-06f4-4678-86d2-cddd42bfc3e2"));

            // ****Modificar registro
            // Salon update = (Salon) Conexion.getInstancia().buscar(Salon.class, "7d456f4d-ec78-4e86-9fbe-721c6ef3e5a5");
            // System.out.println("Salón encontrado: " + update);
            // update.setDescripcion("Prueba de UPDATE");
            // update.setNombreSalon("C33-P");
            // update.setCapacidad(50);
            // Conexion.getInstancia().modificar(update);
            // Salon actualizado = (Salon) Conexion.getInstancia().buscar(Salon.class, "7d456f4d-ec78-4e86-9fbe-721c6ef3e5a5");
            // System.out.println("Salón actualizado: " + actualizado);

            // ****Eliminar registro
            // Salon encontrado = (Salon) Conexion.getInstancia().buscar(Salon.class, "7d456f4d-ec78-4e86-9fbe-721c6ef3e5a5");
            // Conexion.getInstancia().eliminar(encontrado);
            // System.out.println("Objeto eliminado");

            // Query personalizado
            // Query query = Conexion.getInstancia().getEntityManager()
            //         .createQuery("select s.nombreSalon from Salon s where s.capacidad =:capacidad");
            // query.setParameter("capacidad", 35);
            // List<String> resultado = query.getResultList();
            // for (String salida : resultado) {
            //     System.out.println(salida);
            // }

        
    //}
}
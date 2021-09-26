package com.fjmg.modelos;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * Usado para modelar los datos del usuario.
 * Implementa la interfaz serializable para poder usarse con la serializacion binaria de java
 */
public class Usuario implements Serializable {
    public String Uid;
    public String correo;
    public String nombre;
    public String apellido;

    public Usuario() throws IOException {
        Random generador = new Random();
        BufferedReader Lector = new BufferedReader( new InputStreamReader(System.in));
        Uid = LocalDate.now().getYear() + "-" + LocalDate.now().getDayOfMonth() + "-" + LocalDateTime.now().getDayOfYear();
        Uid += "-" +  LocalDateTime.now().getHour() +"-" + generador.nextLong(1,100000000) + generador.nextLong(1,100000000);;
        System.out.println("Nombre del usuario:");
        nombre = Lector.readLine();
        System.out.println("Apellidos del usuario:");
        apellido = Lector.readLine();
        boolean repetir = true;
        while(repetir)
        {
            System.out.println("Correo del usuario:");
            correo = Lector.readLine();
            if(correo.contains("@") && correo.contains("."))
            {
                    repetir  = false;
            }else
            {
                System.out.println("Correo del usuario invalido debe tener @ y .");
            };

        }

        System.out.println("Su uid es:" + Uid);
    }
    public void MostrarDatos()
    {
        System.out.println("------------------------------");
        System.out.println("Nombre del usuario:");
        System.out.println(nombre);
        System.out.println("Apellidos del usuario:");
        System.out.println(apellido);
        System.out.println("Correo del usuario:");
        System.out.println(correo);
        System.out.println("Su uid es:" + Uid);
        System.out.println("------------------------------");
    }
    public void MostrarDatosSinUid()
    {
        System.out.println("------------------------------");
        System.out.println("Nombre del usuario:");
        System.out.println(nombre);
        System.out.println("Apellidos del usuario:");
        System.out.println(apellido);
        System.out.println("Correo del usuario:");
        System.out.println(correo);
        System.out.println("------------------------------");
    }
}

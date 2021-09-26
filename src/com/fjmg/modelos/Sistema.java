package com.fjmg.modelos;


import com.fjmg.tratamientos.Crud;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.EventListener;

/**
 * Sistema es una clase utilizada para configurar de que forma trabaja el programa
 * Si su sistema de guardado esta activo entonces su crud se efectuara en archivo
 * sino solo en array
 */
public class Sistema
{
    /**
     * true modo archivo en este modo toda modificacion es llevado al archivo y el crud trata siempre con el archivo
     * false modo array con su Array de Usuarios codificado
     */
    public boolean modoGurdado;
    /**
     * La lista de los usuarios cargados en memoria en este momento
     */
    public  ArrayList<Usuario> usuarios;
    /**
     * Crud que usa el sistema para tratar con los usuarios
     */
    public Crud mycrud;

    /**
     * El Sistema carga en modo array ,crea un nuevo crud y inicia su lista de usuarios.
     * Ademas dibuja el menu principal del sistema
     * @throws IOException
     */
    public Sistema() throws IOException {
        modoGurdado = false;
        usuarios =  new ArrayList<>(0);
        mycrud = new Crud(this);
        DibujarMenu();
    }
    //Menu Principal del sistema
    public void DibujarMenu() throws IOException {
        boolean bucle = true;
        String Modo = "array";
        BufferedReader lector = new BufferedReader( new InputStreamReader(System.in));
        while (bucle)
        {
            if (!modoGurdado)
            {
                Modo = "array";
            }
            else
            {
                Modo = "archivo";
            }
            System.out.println("Sistema de gestion de usuarios (Java Crud)");
            System.out.println("Modo:"+Modo );
            System.out.println("--------------------------------------------");
            System.out.println("1.Agregar usuario");
            System.out.println("2.Listar usuarios");
            System.out.println("3.Modificar usuarios");
            System.out.println("4.Eliminar usuarios");
            System.out.println("5.Cambiar modo");
            System.out.println("6.Salir");
            System.out.println("--------------------------------------------");
            System.out.println("Elige una opcion:");
            switch (lector.readLine())
            {
                case "1":
                    mycrud.AgregarUsuario();
                    break;
                case "2":
                    mycrud.LeerUsuarios();
                    break;
                case "3":
                    mycrud.ModificarUsuario();
                    break;
                case "4":
                    mycrud.EliminarUsuario();
                    break;
                case "5":
                    modoGurdado = !modoGurdado;
                    if (modoGurdado) {
                        mycrud.OnfileMode();
                    }
                    break;
                case "6":
                    bucle = false;
                    break;
            }

        }
    }

    /**
     * EventsSystem es una interfaz creada para que la clase crud
     * Escuche el sistema y avisarle en caso e que se cargue el modo archivo
     */
    public interface EventsSystem extends EventListener
    {
      void OnfileMode();
    }
}

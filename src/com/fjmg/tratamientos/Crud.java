package com.fjmg.tratamientos;

import com.fjmg.modelos.Archivo;
import com.fjmg.modelos.Sistema;
import com.fjmg.modelos.Usuario;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Crud implements Sistema.EventsSystem
{
    /**
     * Sistema al que pertenece
     */
    Sistema anfitrion;
    /**
     * archivo con el que trabaja
     */
    Archivo myfile;

    /**
     * Inicia el crud
     * @param anfitrion sistema que administra
     */
    public Crud(Sistema anfitrion)
    {
        this.anfitrion = anfitrion;

    }

    /**
     * El archivo es borrado y se escribe los datos de memoria
     * @throws IOException si el archivo diera algun error
     */
    public void ActualizarFichero() throws IOException {
        if(anfitrion.modoGurdado)
        {
            myfile.Crear();
            myfile.EscribirTodo(anfitrion.usuarios);
        }
    }

    /**
     * Agrega usuarios a su sistema
     * @throws IOException puede saltar bien por un problema con el archivo o por la entrada de datos
     */
    public  void AgregarUsuario() throws IOException {
    anfitrion.usuarios.add(new Usuario());
        ActualizarFichero();
    }
    /**
     * Elimina usuarios a su sistema
     * @throws IOException puede saltar bien por un problema con el archivo o por la entrada de datos
     */
    public  void EliminarUsuario() throws IOException {
        BufferedReader lector = new BufferedReader( new InputStreamReader(System.in));
        System.out.println("Porfavor ingrese un uid:");
        boolean eliminar = false;
        Usuario target = null;
        try {
            String  uid =  lector.readLine();
            for ( Usuario usuario : anfitrion.usuarios)
            {
                if (usuario.Uid.equals(uid))
                {
                    usuario.MostrarDatosSinUid();
                    System.out.println("Estas seguro de eliminar este usuario(N/s):");
                    switch(lector.readLine())
                    {
                        case "S":
                        case "s":
                            eliminar = true;
                            target = usuario;
                            break;
                        case "N":
                        case "n":
                        default:
                            System.out.println("No se elimino el usuario");
                            break;
                    }
                }
                else
                {
                    System.out.println("Usuario no encontrado");
                }

            }
        } catch (IOException e) {
            System.out.println("Usuario no encontrado");
        }
        if (eliminar)
        {
            anfitrion.usuarios.remove(target);
            System.out.println("Usuario Eliminado");
            eliminar = false;
        }
        ActualizarFichero();
    }
    /**
     * Modifca usuarios a su sistema
     * @throws IOException puede saltar bien por un problema con el archivo o por la entrada de datos
     */
    public  void ModificarUsuario() throws IOException {
        BufferedReader lector = new BufferedReader( new InputStreamReader(System.in));
        System.out.println("Porfavor ingrese un uid:");
        int i = 0;
        try {
            String  uid =  lector.readLine();
            for ( Usuario usuario : anfitrion.usuarios)
            {
                i++;
                if (usuario.Uid.equals(uid))
                {
                    usuario.MostrarDatosSinUid();
                    System.out.println("Estas seguro de actualizar este usuario(N/s):");
                    switch(lector.readLine())
                    {
                        case "S":
                        case "s":
                            anfitrion.usuarios.set(i - 1,new Usuario()) ;
                            System.out.println("Usuario Actualizado");
                            break;
                        case "N":
                        case "n":
                        default:
                            System.out.println("No se actualizo el usuario");
                            break;
                    }
                }
                else
                {
                    System.out.println("Usuario no encontrado");
                }
            }
        } catch (IOException e) {
            System.out.println("Usuario no encontrado");
        }
        ActualizarFichero();
    }
    /**
     * Muestra los usuarios a su sistema
     * @throws IOException puede saltar bien por un problema con el archivo o por la entrada de datos
     */
    public  void LeerUsuarios() throws IOException {
        for ( Usuario usuario : anfitrion.usuarios)
        {
            usuario.MostrarDatos();
        }
        ActualizarFichero();
    }

    /**
     * todo crud esta suscrito al evento de su sistema
     * teniendo en cuenta si existe o no el archivo lleva acabo un procedimiento
     */
    @Override
    public void OnfileMode()
    {
        this.myfile = new Archivo();
        if(myfile.Exists())
        {
            anfitrion.usuarios.addAll(myfile.LeerTodo());
        }
        else {
            try {
                myfile.Crear();
                myfile.EscribirTodo(anfitrion.usuarios);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

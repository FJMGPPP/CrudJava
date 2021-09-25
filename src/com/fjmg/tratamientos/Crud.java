package com.fjmg.tratamientos;

import com.fjmg.modelos.Archivo;
import com.fjmg.modelos.Sistema;
import com.fjmg.modelos.Usuario;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Crud implements Sistema.EventsSystem
{
    Sistema anfitrion;
    Archivo myfile;
    public Crud(Sistema anfitrion)
    {
        this.anfitrion = anfitrion;

    }
    public void ActualizarFichero() throws IOException {
        if(anfitrion.modoGurdado)
        {
            myfile.Crear();
            myfile.EscribirTodo(anfitrion.usuarios);
        }
    }
    public  void AgregarUsuario() throws IOException {
    anfitrion.usuarios.add(new Usuario());
        ActualizarFichero();
    }
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
    public  void LeerUsuarios() throws IOException {
        for ( Usuario usuario : anfitrion.usuarios)
        {
            usuario.MostrarDatos();
        }
        ActualizarFichero();
    }

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

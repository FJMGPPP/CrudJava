package com.fjmg.modelos;

import java.io.*;
import java.util.ArrayList;

public class Archivo
{
    /**
     * Url de donde se encuentra el archivo
     */
    final String Url = "./Usuarios.txt";
    /**
     * Archivo creado con la url dada
     */
    File archivo;
    public Archivo()
    {
        archivo = new File(Url);
    }

    /**
     * Comprueba que existe o no el archivo
     * @return true si es existe , false sino
     */
    public boolean Exists()
    {
        return  archivo.exists();
    }

    /**
     * Recrea el archivo o lo crea de no existir
     * @throws IOException
     */
    public void Crear() throws IOException {
        archivo.createNewFile();
    }

    /**
     * Lee todos los datos del archivo serializado
     * @return La lista de usuarios en el archivo
     */
    public ArrayList<Usuario> LeerTodo() {

        ArrayList<Usuario> usuarios = new ArrayList<Usuario>(0);
        Usuario linea = null;
        try {
            FileInputStream lector = new FileInputStream(archivo);
            ObjectInputStream serializador = new ObjectInputStream(lector);
            while (true) 
                {

                    try
                    {
                    linea = (Usuario) serializador.readObject();
                    } catch (ClassNotFoundException i)
                    {
                    break;
                    }
                    catch (IOException i){
                        break;
                    }
                    usuarios.add(linea);
                }
            serializador.close();
            lector.close();
        } catch (IOException i){
            i.printStackTrace();
        }
        return  usuarios;
    }
    /**
     * escribe todos los datos del archivo serializado
     */
    public void EscribirTodo(ArrayList<Usuario> usuarios) throws IOException {
        FileOutputStream escritor = new FileOutputStream(archivo);
        ObjectOutputStream serializador = new ObjectOutputStream(escritor);
        for (Usuario usuario : usuarios)
        {
            serializador.writeObject(usuario);
        }
    }
}


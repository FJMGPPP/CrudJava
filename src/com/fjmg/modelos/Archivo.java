package com.fjmg.modelos;

import java.io.*;
import java.util.ArrayList;

public class Archivo
{
    final String Url = "./Usuarios.txt";
    File archivo;
    public Archivo()
    {
        archivo = new File(Url);
    }
    public boolean Exists()
    {
        return  archivo.exists();
    }
    public void Crear() throws IOException {
        archivo.createNewFile();
    }
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
    public void EscribirTodo(ArrayList<Usuario> usuarios) throws IOException {
        FileOutputStream escritor = new FileOutputStream(archivo);
        ObjectOutputStream serializador = new ObjectOutputStream(escritor);
        for (Usuario usuario : usuarios)
        {
            serializador.writeObject(usuario);
        }
    }
}


package es.torvall;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class GestorEmpleados {
	
	// Metodo para encotrar un empleado por su id
	public void listarEmpleados(int id) {
		Employee empleado = null;
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		try {
			fis = new FileInputStream(Main.fichero);
			ois = new ObjectInputStream(fis);
			try{
				empleado = (Employee) ois.readObject(); // Leemos un empleado
				while(empleado != null){
					if(empleado.getEmp_no() == id){
						System.out.println(empleado.toString());
					}
					empleado = (Employee) ois.readObject();
				}
			}catch (EOFException eof){
				ois.close();
			}
		} catch (FileNotFoundException e) {
			System.err.println("Fichero no encontrado");
		} catch (IOException ioe) {
			System.err.println("Error de E/S");
		} catch (ClassNotFoundException cnfe) {
			System.err.println("El fichero no tiene el formato correcto");
		}
	}
}
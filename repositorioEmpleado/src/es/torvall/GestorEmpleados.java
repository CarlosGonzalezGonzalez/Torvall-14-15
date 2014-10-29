package es.torvall;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class GestorEmpleados {

	public static final String fichero = "./resources/empleados";

	// Metodo para encotrar un empleado por su id
	public ArrayList<Employee> listarEmpleados(int id) throws ClienteNoEncontrado{
		Employee empleado = null;
		ArrayList<Employee> arrayEmpleados = new ArrayList();
		FileInputStream fis = null;
		ObjectInputStream ois = null;

		try {
			fis = new FileInputStream(fichero);
			ois = new ObjectInputStream(fis);
			empleado = (Employee) ois.readObject(); // Leemos un empleado
			while (empleado != null) {
				if (empleado.getEmp_no() == id) {
					arrayEmpleados.add(empleado);
				}
				empleado = (Employee) ois.readObject();
			}
		} catch (FileNotFoundException e) {
			System.err.println("Fichero no encontrado");
		} catch (IOException e) {
			System.err.println("Error E/S");
		} catch (ClassNotFoundException e) {
			System.err.println("Clase no encontrada");
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				System.err.println("Error E/S");
			}
		}
		
		if(!arrayEmpleados.isEmpty()){
			return arrayEmpleados;
		}else{
			throw new ClienteNoEncontrado("No hay clientes con ese id");
		}
		
	}
}
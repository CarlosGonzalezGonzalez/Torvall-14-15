package es.torvall;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class GestorEmpleados {

	public static final String fichero = "./resources/empleados";
	private static final ArrayList<Employee> employeeList = new ArrayList();

	/**
	 * Método para crear un fichero de prueba
	 */
	private void dummy() {
		Employee e;
		// Listado de empleados
		employeeList.add(new Employee(7902, "Norris", "Chuck", "empleado",
				"17/12/1990", 5000, 0, 1145));
		employeeList.add(new Employee(7888, "Hogan", "Hulk", "vendedor",
				"1/01/1990", 1500, 0, 1378));
		employeeList.add(new Employee(7567, "Ronaldo", "Cristiano",
				"aparca_coches", "29/05/1991", 999999, 0, 1454));
		employeeList.add(new Employee(7499, "Diaz", "Marujita", "vendedor",
				"20/02/1990", 1500, 0, 1378));
		employeeList.add(new Employee(7499, "Hermida", "Jesus", "pica_teclas",
				"20/06/1989", 2500, 0, 500));
		employeeList.add(new Employee(6700, "Chocolatero", "Paquito",
				"cantante", "20/06/1989", 400, 0, 1145));

		ObjectOutputStream streamSalida = null;
		ObjectInputStream streamEntrada = null;
		try {
			// escribimos
			streamSalida = new ObjectOutputStream(new FileOutputStream(fichero));

			for (Employee emp : employeeList)
				streamSalida.writeObject(emp);

			if (streamSalida != null)
				streamSalida.close();

			// leemos

			streamEntrada = new ObjectInputStream(new FileInputStream(fichero));

			e = (Employee) streamEntrada.readObject();
			while (e != null) {

				// System.out.println(e.toString());
				e = (Employee) streamEntrada.readObject();
			}

			if (streamEntrada != null)
				streamEntrada.close();
		} catch (FileNotFoundException e1) {

		} catch (EOFException e1) {

		} catch (IOException e1) {

		} catch (ClassNotFoundException e1) {

		}

	}
	

    public void cargarFichero( ) {
        Employee e= new Employee(); 
        ObjectInputStream streamEntrada = null;

        try {
            /*El metodo lee del archivo que le hemos pasado
             **y guarda los objetos empleados en un ArrayList
             */
            streamEntrada = new ObjectInputStream(new FileInputStream(fichero));

            e = (Employee) streamEntrada.readObject();
            while (e != null) {
                employeeList.add(e);

                e = (Employee) streamEntrada.readObject();
            }

        } catch (FileNotFoundException e1) {
            System.err.println("Error, archivo no encontrado");
        } catch (EOFException e1) {
            
        } catch (IOException e1) {
            System.err.println("Error E/S");
        } catch (ClassNotFoundException ex) {
            System.err.println("Error clase no econtrada");
        } finally {
            try {
                streamEntrada.close();
            } catch (IOException ex) {
                System.err.println("Error E/S");
            }
        }
    }

	


	// Metodo para encotrar un empleado por su id
	public Employee listarEmpleados(int id) throws ClienteNoEncontrado{
		Employee empleado = null;
		Employee respuesta = null;
		// Creamos el flujo
		FileInputStream fis = null;
		ObjectInputStream ois = null;

		try {
			// Abrimos el flujo
			fis = new FileInputStream(fichero);
			ois = new ObjectInputStream(fis);
			empleado = (Employee) ois.readObject(); // Leemos un empleado
			try{
			while (empleado != null) {
				if (empleado.getEmp_no() == id) {
					respuesta = empleado; // Si el empleado tiene el id lo igualamos a la respuesta,que devolveremos mas adelante
				}
				empleado = (Employee) ois.readObject(); // Leemos otro empleado
			}
			} catch (EOFException e) {}
		} catch (FileNotFoundException e) {
			System.err.println("Fichero no encontrado");
		} catch (IOException e) {
			System.err.println("Error E/S");
		} catch (ClassNotFoundException e) {
			System.err.println("Clase no encontrada");
		} finally {
			try {
				ois.close(); // Cerramos el flujo
			} catch (IOException e) {
				System.err.println("Error E/S (2)");
			}
		}
		
		if(respuesta!=null){
			return respuesta; // Devolvemos el cliente
		}else{
			throw new ClienteNoEncontrado("No hay clientes con ese id"); // Mostramos el mensaje de que no hay clientes con el id introducido
		}
		
	}
	
	
}
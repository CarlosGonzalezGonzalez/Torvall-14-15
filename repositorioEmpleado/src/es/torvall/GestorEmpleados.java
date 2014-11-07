package es.torvall;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GestorEmpleados {
	public static final String fichero = "./resources/empleados";
	private  ArrayList<Employee> employeeList;

	/**
	 * Método para crear un fichero de prueba
	 */
public void dummy() {
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
				 System.out.println(e.toString());
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

	/*
	 * La siguiente clase recibe un identificador de empleado y el salario*
	 * nuevo que debera tener ese empleado
	 */
	public void cambiarSalario(int id, int salario) {
		ObjectOutputStream streamSalida = null;
		cargarFichero();

		for (Employee emp : employeeList) {
			if (emp.getEmp_no() == id) {
				try {
					emp.setSalary(salario);
				} catch (Exception e) {
					System.err.println("Exception");
				}
			}
			System.out.println(emp.toString());
		}
		guardarFichero();
	}

	/*
	 * El metodo va a guardar en un archivo los empleados que hay*en un
	 * arraylist
	 */
	public void guardarFichero() {
		ObjectOutputStream streamSalida = null;

		try {
			streamSalida = new ObjectOutputStream(new FileOutputStream(fichero));// Empezamos
																					// a
																					// escribir

			for (Employee emp : employeeList) {
				streamSalida.writeObject(emp);// escribimos en el fichero
			}

		} catch (IOException ex) {
			System.err.println("Error E/S");
		} finally {

			try {
				streamSalida.close();// terminamos de escribir
			} catch (IOException ex) {
				System.err.println("Error E/S");
			}
		}
	}

	public void cargarFichero() {
		employeeList = new ArrayList<Employee>();
		Employee e = new Employee();
		ObjectInputStream streamEntrada = null;

		try {
			/*
			 * El metodo lee del archivo que le hemos pasado*y guarda los
			 * objetos empleados en un ArrayList
			 */
			streamEntrada = new ObjectInputStream(new FileInputStream(fichero));// Abrimos
																				// el
																				// flujo

			e = (Employee) streamEntrada.readObject();
			while (e != null) {
				//System.out.println(e);
				employeeList.add(e);// AÃ±adimos el objeto employee a la clase
									// empleado

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
				streamEntrada.close();// Cerramos el flujo
			} catch (IOException ex) {
				System.err.println("Error E/S");
			}
		}
	}

	/**
	 * @author CarlosGonzalezGonzalez
	 * @param id
	 * @return
	 * @throws ClienteNoEncontrado
	 */
	// Metodo para encotrar un empleado por su id
	public Employee listarEmpleados(int id) throws ClienteNoEncontrado {
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
			try {
				while (empleado != null) {
					if (empleado.getEmp_no() == id) {
						respuesta = empleado; // Si el empleado tiene el id lo igualamos a la respuesta,que devolveremos mas adelante
					}
					empleado = (Employee) ois.readObject(); // Leemos otro empleado
				}
			} catch (EOFException e) {
			}
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

		if (respuesta != null) {
			return respuesta; // Devolvemos el cliente
		} else {// Mostramos el mensaje de que no hay clientes con el id introducido
			throw new ClienteNoEncontrado("No hay clientes con ese id"); 
		}

	}

	/**
	 * @author CarlosGonzalezGonzalez
	 * @param departamento
	 * @return
	 */
	public float calcularSueldoMedio(int departamento) {
		float sueldoMedio = 0;
		int contadorEmpleados = 0;

		if (employeeList == null) {
			cargarFichero();
		}

		for (Employee empleado : employeeList) {
			if (empleado.getDept_number() == departamento) { // Si el departamento del empleado es = al departamento introducido...
				sueldoMedio = sueldoMedio + empleado.getSalary(); // añadimos su sueldo
				contadorEmpleados++; // incrementamos la cantidad de empleados que hay en el departamento
			}
		}
		System.out.println(sueldoMedio);
		System.out.println(contadorEmpleados);
		if (contadorEmpleados != 0) {
			return sueldoMedio / contadorEmpleados;
		}
		return 0;

	}
	/**
	 * Metodo que elimina un Empleado por su ID
	 * @param id
	 * @return
	 */
	public String eliminar(int id) {
		String s = null;
		for (int i = 0; i < employeeList.size(); i++) {
			if (employeeList.get(i).getEmp_no() == id) {
				employeeList.remove(i);
				s = "Borrado OK";

			} else
				s = "El Empleado No Existe";

		}
		return s;

	}
	/**
	 * Método que añade un nuevo empleado
	 * 
	 * @param e
	 * @return
	 */
	public boolean addEmpleado(Employee e) {

		if (employeeList.add(e)) {
			guardarFichero();
			return true;
		}

		return false;

	}
	
	/**
	 * @author Katy
	 *  Metodo para encotrar a los empleados ordenados por apellido
	 * @return un ArrayList de empleados ordenado por apellidos
	 */

	public ArrayList<Employee> ordenApellido() {
		if(employeeList==null)
			cargarFichero();
		
		// Llamamos al método sort que ordena la lista por apellidos de cada empleado 
		// mediante un objeto Comparator
		Collections.sort(employeeList, new Comparator<Employee>() {
			public int compare(Employee e1, Employee e2) {
				return e1.getLastname().compareTo(e2.getLastname());
			}
		});

		return employeeList;

	}
}

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
    /**
     * @author carlos barriuso
     * @param idDepartamento
     * @return El numero de empleados que tiene el departamento, si es 0 puede que no exista el departamento o que el departamento no tiene empleados actualmente
     */
    public int numeroEmpleadoPorDepartemento(int idDepartamento){
    	int numeroEmpleadoEnDepartemento=0;
    	if (employeeList == null){ // Si el array esta vacio,se carga el fichero    	
    		cargarFichero();
    	}
    	
    	for(int i=0;i<employeeList.size();i++){ //se recorre el array
    		if (employeeList.get(i).getDept_number()==idDepartamento){//si el id del empleado coincide con el que pasamos lo contamos en la variable
    			numeroEmpleadoEnDepartemento++;
    		}   		 
    	}
    	return numeroEmpleadoEnDepartemento; 
    }

}

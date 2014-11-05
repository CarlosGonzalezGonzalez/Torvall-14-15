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
 private static final ArrayList<Employee> employeeList=new ArrayList();
   /*La siguiente clase recibe un identificador de empleado y el salario
    ** nuevo que debera tener ese empleado
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
  /*El metodo va a guardar en un archivo los empleados que hay 
  **en un arraylist
  */
    public void guardarFichero() {
        ObjectOutputStream streamSalida=null;
        
        try {
            streamSalida = new ObjectOutputStream(new FileOutputStream(fichero));//Empezamos a escribir

            for (Employee emp : employeeList) {
                streamSalida.writeObject(emp);//escribimos en el fichero
            }

           
        } catch (IOException ex) {
            System.err.println("Error E/S");
        } finally {

            try {
                streamSalida.close();//terminamos de escribir
            } catch (IOException ex) {
                System.err.println("Error E/S");
            }
        }
    }

    public void cargarFichero( ) {
         Employee e= new Employee(); 
        ObjectInputStream streamEntrada = null;

        try {
            /*El metodo lee del archivo que le hemos pasado
             **y guarda los objetos empleados en un ArrayList
             */
            streamEntrada = new ObjectInputStream(new FileInputStream(fichero));//Abrimos el flujo

            e = (Employee) streamEntrada.readObject();
            while (e != null) {
                employeeList.add(e);//Añadimos el objeto employee a la clase empleado

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
                streamEntrada.close();//Cerramos el flujo
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

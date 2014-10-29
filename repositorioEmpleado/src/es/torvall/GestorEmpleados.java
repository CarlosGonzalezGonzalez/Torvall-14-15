package es.torvall;

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
                emp.setSalary(salario);
            }
            System.out.println(emp.toString());
        }
        guardarFichero();
    }

    public void guardarFichero() {
        ObjectOutputStream streamSalida=null;
        
        try {
            streamSalida = new ObjectOutputStream(new FileOutputStream(fichero));

            for (Employee emp : employeeList) {
                streamSalida.writeObject(emp);
            }

           
        } catch (IOException ex) {
            System.err.println("Error E/S");
        } finally {

            try {
                streamSalida.close();
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


}

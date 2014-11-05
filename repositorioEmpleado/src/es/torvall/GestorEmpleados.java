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
            if (emp.getEmp_no() == id) {//buscamos dentro de nuestro arraylist el empleado al que corresponde la id y cambiamos su salario
                emp.setSalary(salario);
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


}

package es.torvall;

public class GestorEmpleados {
 public static final String fichero = "./resources/empleados";

    public void cambiarSalario(int id, int salario) {
        Employee e;
        ArrayList<Employee> employeeList = new ArrayList();
        ObjectInputStream streamEntrada = null;
        ObjectOutputStream streamSalida = null;

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

        } catch (EOFException e1) {

        } catch (IOException e1) {

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ejercicio2.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                streamEntrada.close();
            } catch (IOException ex) {
                Logger.getLogger(Ejercicio2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        /*Recorremos el arrayList comparando la id de cada uno de los empleados con
         **la id que buscamos y cuando lo encontramos modificamos el valor de su salario
         ** y le introducimos el salario que hemos recibido por parametro
         */
        for (Employee emp : employeeList) {
            if (emp.getEmp_no() == id) {
                emp.setSalary(salario);
            }
            System.out.println(emp.toString());
        }
        /*Por ultimo volvemos a escribir los cambios en el fichero
        **
        */
        try {
            streamSalida = new ObjectOutputStream(new FileOutputStream(fichero));

            for (Employee emp : employeeList) {
                streamSalida.writeObject(emp);
            }

            if (streamSalida == null) {
                streamSalida.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Ejercicio2.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                streamSalida.close();
            } catch (IOException ex) {
                Logger.getLogger(Ejercicio2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}

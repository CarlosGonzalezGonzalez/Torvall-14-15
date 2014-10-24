package es.torvall;

public class GestorEmpleados {


 static final String ruta="empleados";
    
    
   
    /*La siguiente clase recibe un identificador de empleado y el salario
    ** nuevo que debera tener ese empleado
    */
    public static void cambiarSalario(int id,int salario){
        Employee e =null;
        File f = new File(ruta);
        ObjectInputStream ois = null;
        try {
            FileInputStream fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            e = (Employee) ois.readObject();
            while (e != null) {
                if (e.getEmp_no()== id) {
                    e.setSalary(salario);
                }
            }
         

        } catch (FileNotFoundException ex) {
            System.err.println("Fichero no encontrado");
        } catch (IOException ex) {
            System.err.println("Error E/S");
        } catch (ClassNotFoundException ex) {
            System.err.println("Clase no encontrada");
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(Ejercicio2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}

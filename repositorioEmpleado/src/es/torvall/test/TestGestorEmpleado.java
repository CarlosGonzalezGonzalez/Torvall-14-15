package es.torvall.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import es.torvall.ClienteNoEncontrado;
import es.torvall.Employee;
import es.torvall.GestorEmpleados;

public class TestGestorEmpleado {

	@Test
	public void testListarEmpleados() {
		GestorEmpleados ge = new GestorEmpleados();

		try {
			assertEquals(7499, ge.listarEmpleados(7499).getEmp_no());
		} catch (ClienteNoEncontrado e) {
		}

	}
	
	@Test
	public void testCalcularSueldoMedio(){
		GestorEmpleados ge = new GestorEmpleados();
		
		assertEquals(2700f,ge.calcularSueldoMedio(1145));
		assertEquals(0f,ge.calcularSueldoMedio(1));
	}

	
     public void cambiarSalario() {
       
        ArrayList<Employee> employeeList = new ArrayList();

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

        assertTrue(employeeList.get(0).getEmp_no()==7902);//Aqui comprobamos que el id coincide
        assertFalse(employeeList.get(2).getEmp_no()==7);//Aqui comprobamos que el id no coincide
        try {
			employeeList.get(0).setSalary(500);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//Cambiamos el salario del empleado en la primera posicion del array
        assertTrue(employeeList.get(0).getSalary()==500);//Aqui comprobamos que el nuevo salario es el correcto
        assertFalse(employeeList.get(2).getSalary()==5000);//Aqui comprobamos que el salario del empleado se ha modificado y no es igual a su salario anterior
    }
	@Test
	public void pruebaEliminar(){
		GestorEmpleados g = new GestorEmpleados();
		g.cargarFichero();
		
		assertEquals("Borrado OK",g.eliminar(7902));
		
		
		assertEquals("El Empleado No Existe",g.eliminar(6));
	}
	
	/**
	* @author carlos barriuso
 	*El metodo comprueba que el metodo numeroEmpleadoPorDepartamento
 	*funciona correctamente
 	*/
 	@Test
	public void departementoNoExiste() {

		GestorEmpleados ge = new GestorEmpleados();
		ge.cargarFichero();
		assertTrue(ge.numeroEmpleadoPorDepartemento(10)==0);//si el departamento no existe
		assertTrue(ge.numeroEmpleadoPorDepartemento(1145)==2);
		assertTrue(ge.numeroEmpleadoPorDepartemento(500)==1);
		assertTrue(ge.numeroEmpleadoPorDepartemento(1378)==2);
		assertTrue(ge.numeroEmpleadoPorDepartemento(1454)==1);
	}

}

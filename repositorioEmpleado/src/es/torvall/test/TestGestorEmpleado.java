package es.torvall.test;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

import es.torvall.GestorEmpleados;

/**
 * @author carlos barriuso
 *El metodo comprueba que el metodo numeroEmpleadoPorDepartamento
 *funciona correctamente
 */
public class TestGestorEmpleado {
	
	

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

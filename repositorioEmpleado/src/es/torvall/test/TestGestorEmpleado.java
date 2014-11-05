package es.torvall.test;

import static org.junit.Assert.*;

import org.junit.Test;

import es.torvall.GestorEmpleados;

public class TestGestorEmpleado {
	
	GestorEmpleados g = new GestorEmpleados();

	@Test
	public void pruebaEliminar(){
		g.cargarFichero("./resources/empleados2");
		
		assertEquals("Borrado OK",g.eliminar(7902));
		
		
		assertEquals("El Empleado No Existe",g.eliminar(6));
	}

}

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
}

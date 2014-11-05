package es.torvall.test;

import static org.junit.Assert.*;

import org.junit.Test;

import es.torvall.Employee;

public class TestEmpleado {

	@Test
	public void pruebaNo_Emp() {
		Employee e = new Employee(1, " hola", "adios ", "kkk ", "11/12/2014",
				0, 0, 0);

		assertEquals(1, e.getEmp_no());
		Employee e2 = new Employee(-1, " hola", "adios ", "kkk ", "11/12/2014",
				0, 0, 0);
		assertEquals(e2.getEmp_no(),0);

	}

	@Test
	public void pruebaLastname() {
		 Employee e = new Employee(1, "Supercalifragilisticoexpialidoso",
		 "adios ", "kkk ", "11/12/2014",
		 0, 0, 0);
		 System.out.println(e.getLastname());
		 assertEquals("Supercalif", e.getLastname());

		Employee e2 = new Employee(1, "Alf", "adios ", "kkk ", "11/12/2014", 0,
				0, 0);
		System.out.println(e2.getLastname());
		assertEquals("Alf", e2.getLastname());

		Employee e3 = new Employee(1, "SuperÑ", "adios ", "kkk ", "11/12/2014",
				0, 0, 0);
		System.out.println(e3.getLastname());
		assertEquals("SuperÑ", e3.getLastname());

		Employee e4 = new Employee(1, "San José", "adios ", "kkk ",
				"11/12/2014", 0, 0, 0);
		System.out.println(e4.getLastname());
		assertEquals("San José", e4.getLastname());

	}

	@Test
	public void pruebaName() {
		Employee e = new Employee(1, "Supercalifragilisticoexpialidoso",
				"Supercalifragilisticoexpialidoso ", "kkk ", "11/12/2014", 0,
				0, 0);
		System.out.println(e.getName_for());
		assertEquals("Supercalif", e.getName_for());

		Employee e2 = new Employee(1, "Alf", "Tomás", "kkk ", "11/12/2014", 0,
				0, 0);
		System.out.println(e2.getName_for());
		assertEquals("Tomás", e2.getName_for());

		Employee e3 = new Employee(1, "SuperÑ", "Piña", "kkk ",
				"11/12/2014", 0, 0, 0);
		System.out.println(e3.getName_for());
		assertEquals("Piña", e3.getName_for());

		Employee e4 = new Employee(1, "San José", "Juan Pablo", "kkk ",
				"11/12/2014", 0, 0, 0);
		System.out.println(e4.getName_for());
		assertEquals("Juan Pablo", e4.getName_for());

	}
	@Test
	public void pruebaJob() {
		Employee e = new Employee(1, "Supercalifragilisticoexpialidoso",
				"Supercalifragilisticoexpialidoso ", "BomberoToreto ", "11/12/2014", 0,
				0, 0);
		System.out.println(e.getJob());
		assertEquals("BomberoTor", e.getJob());

		Employee e2 = new Employee(1, "Alf", "Tomás ", "Futbolista", "11/12/2014", 0,
				0, 0);
		System.out.println(e2.getJob());
		assertEquals("Futbolista", e2.getJob());

		Employee e3 = new Employee(1, "SuperÑ", "Piña ", "Albañil",
				"11/12/2014", 0, 0, 0);
		System.out.println(e3.getJob());
		assertEquals("Albañil", e3.getJob());

		Employee e4 = new Employee(1, "San José", "Juan Pablo ", "Entrenador/Futbolista",
				"11/12/2014", 0, 0, 0);
		System.out.println(e4.getJob());
		assertEquals("Entrenador", e4.getJob());

	}
	
	@Test
	public void pruebaDate() {
		Employee e4 = new Employee(1, "San José", "Juan Pablo ", "Entrenador/Futbolista",
				"25/10/2014", 0, 0, 0);
		System.out.println("fecha"+e4.getRegis_date());
		assertEquals("25/10/2014", e4.getRegis_date());
		
		Employee e5 = new Employee(1, "Supercalifragilisticoexpialidoso",
				"Supercalifragilisticoexpialidoso ", "BomberoToreto ", "10/10/2014", 0,
				0, 0);
		System.out.println(e5.getRegis_date());
		assertEquals("10/10/2014", e5.getRegis_date());

	}
	@Test
	public void pruebaSalary() {
		Employee e4 = new Employee(1, "San José", "Juan Pablo ", "Entrenador/Futbolista",
				"25/10/2014", 50000000, 0, 0);
		System.out.println(e4.getSalary());
		assertTrue(50000000==e4.getSalary());
		
		Employee e5 = new Employee(1, "Supercalifragilisticoexpialidoso",
				"Supercalifragilisticoexpialidoso ", "BomberoToreto ", "10/10/2014", -2200,
				0, 0);
		System.out.println(e5.getSalary());
		assertFalse(-2200==e5.getSalary());
		
		Employee e6 = new Employee(1, "Supercalifragilisticoexpialidoso",
				"Supercalifragilisticoexpialidoso ", "BomberoToreto ", "10/10/2014", 3000,
				0, 0);
		System.out.println(e6.getSalary());
		assertTrue(3000==e6.getSalary());
		
	

	}
	
	@Test
	public void pruebaCommision() {
		Employee e4 = new Employee(1, "San José", "Juan Pablo ", "Entrenador/Futbolista",
				"25/10/2014", 50000000, -1, 0);
		System.out.println(e4.getCommission());
		assertFalse(-1==e4.getCommission());
		
		Employee e5 = new Employee(1, "Supercalifragilisticoexpialidoso",
				"Supercalifragilisticoexpialidoso ", "BomberoToreto ", "10/10/2014", -2200,
				20, 0);
		System.out.println(e5.getCommission());
		assertTrue(e5.getCommission()==0);
		
		Employee e6 = new Employee(1, "Supercalifragilisticoexpialidoso",
				"Supercalifragilisticoexpialidoso ", "BomberoToreto ", "10/10/2014", 3000,
				0, 0);
		System.out.println(e6.getCommission());
		assertTrue(e6.getCommission()==0);
		
	

	}
	
	@Test
	public void pruebaNumer_Dept() {
		Employee e4 = new Employee(1, "San José", "Juan Pablo ", "Entrenador/Futbolista",
				"25/10/2014", 50000000, -1, -1);
		System.out.println(e4.getDept_number());
		assertTrue(0==e4.getDept_number());
		
		Employee e5 = new Employee(1, "Supercalifragilisticoexpialidoso",
				"Supercalifragilisticoexpialidoso ", "BomberoToreto ", "10/10/2014", -2200,
				20, 20);
		System.out.println(e5.getDept_number());
		assertTrue(e5.getDept_number()==0);
		
		Employee e6 = new Employee(1, "Supercalifragilisticoexpialidoso",
				"Supercalifragilisticoexpialidoso ", "BomberoToreto ", "10/10/2014", 3000,
				0, 0);
		System.out.println(e6.getDept_number());
		assertTrue(0==e6.getDept_number());
		
	

	}
}

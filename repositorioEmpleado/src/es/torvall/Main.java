package es.torvall;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);

		GestorEmpleados ge = new GestorEmpleados();
		
		ge.cargarFichero();
		int opcion;
		do{
          //Cambiar salario,listar,ordenar apellido,listar departamentos,
		//nº empleados departamento.
		System.out.println("1.Añadir Cliente\n2.Eliminar Cliente\n3.Guardar\n4.Cargar\n5.Calcular sueldo medio\n6.Cambiar Salario\n7.Consultar empleado"
				+ "\n8.Ordenar por apellido\n9.Numero empleados por departamento\n10.Listar Deparatamentos\n0.Salir");
		opcion=sc.nextInt();
		sc.nextLine();
		
		
			
		switch(opcion){
		case 1:
			System.out.println("Numero empleado");
			int numero = sc.nextInt();
			sc.nextLine();
			System.out.println("Apellido");
			String ap = sc.nextLine();
			System.out.println("Nombre");
			String nombre = sc.nextLine();
			System.out.println("Trabajo");
			String trabajo = sc.nextLine();
			System.out.println("Fecha");
			String fecha = sc.nextLine();
			System.out.println("Salario");
			float salario = sc.nextInt();
			sc.nextLine();
			System.out.println("Comision");
			int comision = sc.nextInt();
			sc.nextLine();
			System.out.println("Numero de departamento");
			int departamento = sc.nextInt();
			sc.nextLine();
			
			
			Employee e = new Employee(numero,ap,nombre,trabajo,fecha,salario,comision,departamento);
			boolean creado = false;
			creado = ge.addEmpleado(e);
			
			break;
		case 2:
			System.out.println("Id del empleado a eliminar:");
			int id = sc.nextInt();
			sc.nextLine();
			
			ge.eliminar(id);
			
			break;
		case 3:
			ge.guardarFichero();

			break;
		case 4:
			ge.cargarFichero();

			break;
		case 5:
			System.out.println("Introduzca departamento");
			departamento = sc.nextInt();
			sc.nextLine();

			System.out.println(ge.calcularSueldoMedio(departamento));

			break;
		case 6:
			System.out.println("Introduzca el id del empleado");
			id = sc.nextInt();
			sc.nextLine();
			
			System.out.println("Introduzca el salario nuevo:");
			salario = sc.nextFloat();
			sc.nextLine();
			
			ge.cambiarSalario(id, (int)salario);
			
			break;
		case 7:
			Employee empleado = new Employee();
			
			System.out.println("Introduzca el id del empleado");
			id = sc.nextInt();
			sc.nextLine();
			
			try {
				empleado = ge.listarEmpleados(id);
				System.out.println(empleado.toString());
			} catch (ClienteNoEncontrado ex) {
				System.err.println("Empleado no encontrado");
			}
			
			break;
		case 8:
			ge.ordenApellido();
			break;
		case 9:
			System.out.println("Introduzca el departamento:");
			departamento = sc.nextInt();
			sc.nextLine();
			
			ge.numeroEmpleadoPorDepartemento(departamento);
			
			break;
		case 10:
			ArrayList<Integer>arrayNumDept = new ArrayList();
			arrayNumDept = ge.listarDepartamentos();
			for(Integer i : arrayNumDept){
				System.out.println(i);
			}
			break;
		}
	}while(opcion != 0);
	}
		
		
	}

package es.torvall;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author Juan
 */
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;
	private int emp_no;
	private String lastname;
	private String name_for;
	private String job;
	//private GregorianCalendar regis_date;
	private Date regis_date;
	private float Salary;
	private int commission;
	private int Dept_number;
	private final int tamanoTotal = 10;
	private SimpleDateFormat format;

	public Employee() {
		format = new SimpleDateFormat("dd/MM/yyyy");
	}

	public Employee(int emp_no, String lastname, String name_for, String job,
			String regis_date, float Salary, int commission, int Dept_number) {
		format = new SimpleDateFormat("dd/MM/yyyy");
		try {
			this.setEmp_no(emp_no);
			this.setLastname(lastname);
			this.setName_for(name_for);
			this.setJob(job);
			this.setRegis_date(regis_date);
			this.setSalary(Salary);
			this.setCommission(commission);
			this.setDept_number(Dept_number);
		} catch (Exception ex) {
			this.emp_no = 0;
			this.lastname = " ";
			this.name_for = " ";
			this.job = " ";
			this.Salary = 0;
			this.commission = 0;
			this.Dept_number = 0;
		}

	}

	public int getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(int emp_no) throws Exception {
		if (emp_no < 0) {
			throw new Exception("No se puede crear un empleado con id negativo");
		}

		this.emp_no = emp_no;

	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		if (lastname.length() > 10) {

			this.lastname = lastname.substring(0, tamanoTotal);
		} else {
			this.lastname = lastname;
		}
	}

	public String getName_for() {
		return name_for;
	}

	public void setName_for(String name_for) {
		if (name_for.length() > 10) {
			this.name_for = name_for.substring(0, tamanoTotal);
		} else {
			this.name_for = name_for;
		}

	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		if (job.length() > 10) {
			this.job = job.substring(0, tamanoTotal);
		} else {
			this.job = job;
		}
	}

	/*
	 * public String getRegis_date() { int
	 * d=regis_date.get(GregorianCalendar.DAY_OF_MONTH); int
	 * m=regis_date.get(GregorianCalendar.MONTH); int
	 * y=regis_date.get(GregorianCalendar.YEAR);
	 *  String s=d+"/"+m+"/"+y; return
	 * s; }
	 */

	public String getRegis_date() {
		return format.format(regis_date).toString();
	}

	/*
	public void setRegis_date(String regis_date) {
		this.regis_date = new GregorianCalendar();
		int d = 0;
		int m = 0;
		int y = 0;
		String[] split = regis_date.split("/");
		d = Integer.parseInt(split[0]);
		m = Integer.parseInt(split[1]);
		y = Integer.parseInt(split[2]);

		this.regis_date.set(y, m, d);
	}*/
	
	
	public void setRegis_date(String regis_date) {
		
		try {
			this.regis_date = format.parse(regis_date);
		} catch (ParseException e) {
			this.regis_date = new Date();

		}
		
	}

	public float getSalary() {
		return Salary;
	}

	public void setSalary(float Salary) throws Exception {
		if (Salary < 0)
			throw new Exception(
					"No se puede crear un empleado con una salario negativo");
		this.Salary = Salary;
	}

	public int getCommission() {
		return commission;
	}

	public void setCommission(int commission) throws Exception {
		if (commission < 0)
			throw new Exception(
					"No se puede crear un empleado con una comision  negativa");
		this.commission = commission;
	}

	public int getDept_number() {
		return Dept_number;
	}

	public void setDept_number(int Dept_number) throws Exception {
		if (Dept_number < 0) {
			throw new Exception(
					"No se puede crear un empleado con un numero de departamento negativo");
		} else {
			this.Dept_number = Dept_number;
		}
	}

	@Override
	public boolean equals(Object obj) {

		final Employee other = (Employee) obj;
		if (this.emp_no != other.emp_no) {
			return false;
		}
		if (!this.lastname.equals(other.lastname)) {
			return false;
		}
		if (!this.name_for.equals(other.name_for)) {
			return false;
		}
		if (!this.job.equals(other.job)) {
			return false;
		}
		if (!this.regis_date.equals(other.regis_date)) {
			return false;
		}
		if (Float.floatToIntBits(this.Salary) != Float
				.floatToIntBits(other.Salary)) {
			return false;
		}
		if (this.commission != other.commission) {
			return false;
		}
		if (this.Dept_number != other.Dept_number) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Employee:" + "emp_no=" + emp_no + ", lastname=" + lastname
				+ ", name_for=" + name_for + ", job=" + job + ", regis_date="
				+ this.getRegis_date() + ", Salary=" + Salary + ", commission="
				+ commission + ", Dept_number=" + Dept_number + '}';
	}

}

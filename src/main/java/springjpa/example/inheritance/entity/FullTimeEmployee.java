package springjpa.example.inheritance.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;

@Entity
public class FullTimeEmployee extends Employee {
	private BigDecimal salary;

	public FullTimeEmployee() {
	}

	@Override
	public String toString() {
		return "FullTimeEmployee [id=" + getId() + ", name=" + getName() + ", salary=" + salary + "]";
	}

	public FullTimeEmployee(String name, BigDecimal salary) {
		super(name);
		this.salary = salary;
	}

	public BigDecimal getHourlyWage() {
		return salary;
	}

	public void setHourlyWage(BigDecimal salary) {
		this.salary = salary;
	}
}

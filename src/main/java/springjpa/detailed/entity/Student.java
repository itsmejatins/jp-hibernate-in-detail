package springjpa.detailed.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;

@Entity
@NamedQuery(name = "find_all_students", query = "SELECT s FROM Student s")
public class Student {

	@GeneratedValue
	@Id
	private Long id;

	@Column(nullable = false)
	private String name;
	@OneToOne(fetch = FetchType.LAZY)
	private Passport passport;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Passport getPassport() {
		return passport;
	}

	public void setPassport(Passport passport) {
		this.passport = passport;
	}

	public Long getId() {
		return this.id;
	}

	public Student() {
	};

	public Student(String name, Passport passport) {
		super();
		this.name = name;
		this.passport = passport;
	}

	public Student(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return String.format("Student[%d, %s]", id, name);
	}

}

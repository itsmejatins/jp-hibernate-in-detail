package springjpa.example.relationships.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import springjpa.example.relationships.entity.embeddables.Address;

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

	@ManyToMany
	@JoinTable(name = "STUDENT_COURSE", joinColumns = @JoinColumn(name = "STUDENT_ID"), inverseJoinColumns = @JoinColumn(name = "COURSE_ID"))
	List<Course> courses = new ArrayList<>();
	
	private Address address;

	@Override
	public String toString() {
		return String.format("Student[%d, %s, %s]", id, name, address);
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

	public List<Course> getCourses() {
		return Collections.unmodifiableList(this.courses);
	}

	public void addCourse(Course course) {
		this.courses.add(course);
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}

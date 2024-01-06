package springjpa.example.relationships.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Cacheable;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PreRemove;

@Entity
@NamedQuery(name = "courses_with_name_like_100_steps", query = "SELECT c FROM Course c WHERE name LIKE '%100 Steps%'")
@Cacheable
@SQLDelete(sql = "UPDATE course SET is_deleted=true WHERE id=?")
@Where(clause = "is_deleted = false")
public class Course {

	private static Logger LOGGER = LoggerFactory.getLogger(Course.class); // static variables don't map to schema

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	@CreationTimestamp
	private LocalDateTime createdDate;
	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;

	boolean isDeleted;

	@OneToMany(mappedBy = "course")
	private List<Review> reviews = new ArrayList<>();

	@ManyToMany(mappedBy = "courses")
	private List<Student> students = new ArrayList<>();

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", isDeleted=" + isDeleted + "]";
	}

	protected Course() {
	};

	public Course(String name) {
		this.name = name;
	}

	public Course(Long id, String name) {
		this(name);
		this.id = id;
	}

	@PreRemove
	public void preRemove() { // can give any name
		this.isDeleted = true;
		LOGGER.info("preRemove method called.");
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Review> getReviews() {
		return Collections.unmodifiableList(this.reviews);
	}

	public void addReview(Review review) {
		this.reviews.add(review);
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public List<Student> getStudents() {
		return Collections.unmodifiableList(students);
	}

	public void addStudent(Student student) {
		this.students.add(student);
	}

	public boolean isDeleted() {
		return this.isDeleted;
	}

}

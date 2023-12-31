package springjpa.detailed.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Course {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	@CreationTimestamp
	private LocalDateTime createdDate;
	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;

	@OneToMany(mappedBy = "course")
	private List<Review> reviews = new ArrayList<>();

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + "]";
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
}

package springjpa.detailed.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Review {

	@Id
	@GeneratedValue
	private Long id;
	private int rating;
	private String description;

	@ManyToOne
	private Course course;

	@Override
	public String toString() {
		return "Review [rating=" + rating + ", description=" + description + "]";
	}

	public Review() {
	}

	public Review(int rating, String description) {
		this.rating = rating;
		this.description = description;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

}

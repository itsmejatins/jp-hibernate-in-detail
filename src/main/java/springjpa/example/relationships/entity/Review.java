package springjpa.example.relationships.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import springjpa.example.relationships.entity.enums.ReviewRating;

@Entity
public class Review {

	@Id
	@GeneratedValue
	private Long id;
	
	@Enumerated
	private ReviewRating rating;
	private String description;

	@ManyToOne
	private Course course;

	@Override
	public String toString() {
		return "Review [rating=" + rating + ", description=" + description + "]";
	}

	public Review() {
	}

	public Review(ReviewRating rating, String description) {
		this.rating = rating;
		this.description = description;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public ReviewRating getRating() {
		return rating;
	}

	public void setRating(ReviewRating rating) {
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

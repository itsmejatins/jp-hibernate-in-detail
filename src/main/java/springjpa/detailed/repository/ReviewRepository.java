package springjpa.detailed.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import springjpa.detailed.entity.Course;
import springjpa.detailed.entity.Review;

@Transactional
@Repository
public class ReviewRepository {
	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	private EntityManager em;

	public Review findById(Long id) {
		Review review = em.find(Review.class, id);
		if (review == null)
			logger.warn("Attempted to retrieve a review which does not exists in the table!");
		return review;
	}
	
	public void save(Review review) {
		if(review.getId() != null)
			em.persist(review);
		else
			em.merge(review);
	}

	public List<Review> findAll() {
		TypedQuery<Review> query = em.createQuery("SELECT r FROM Review r", Review.class);
		return query.getResultList();
	}

	public void deleteById(Long id) {
		Review review = em.find(Review.class, id);
		if (review == null)
			logger.warn("Attempted to delete a review which does not exists in the database");
		else
			em.remove(review);
	}

	public Course getCourse(Long reviewId) {
		Review review = em.find(Review.class, reviewId);
		if (review != null)
			return review.getCourse();
		else {
			logger.warn("Attempted to find course of a review which does not exists!");
			return null;
		}
	}

}

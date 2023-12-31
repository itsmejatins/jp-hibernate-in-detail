package springjpa.detailed.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import jakarta.transaction.Transactional;
import springjpa.detailed.entity.Review;

@SpringBootTest
public class CourseRepositoryTest {
	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	CourseRepository dao;
	
	@Test
	@Transactional
	public void getReviewsTest() {
		List<Review> reviews = dao.getReviews(10003l);
		logger.info("reviews for course with id = 100003 -> {}", reviews);
	}
	
	@Test
	@DirtiesContext
	@Transactional
	public void addReviewsTest() {
		Review r1 = new Review(1, "Not good");
		Review r2 = new Review(3, "Average");
		Review r3 = new Review(5, "Best");
		List<Review> reviews = List.of(r1, r2, r3);
		
		logger.info("Reviews for course with id=10005 -> {}", dao.getReviews(10005l));
		logger.info("Adding reviews to course with id 10005");
		dao.addReviews(10005l, reviews);
		logger.info("Reviews for course with id=10005 -> {}", dao.getReviews(10005l));
	}
}

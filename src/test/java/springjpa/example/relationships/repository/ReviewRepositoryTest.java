package springjpa.example.relationships.repository;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.transaction.Transactional;
import springjpa.example.relationships.repository.ReviewRepository;

@SpringBootTest
public class ReviewRepositoryTest {
	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	ReviewRepository dao;

	@Test
	@Transactional
	public void getCourseTest() {
		logger.info("course of review with id 50001 -> {}", dao.getCourse(50001l));
	}

}

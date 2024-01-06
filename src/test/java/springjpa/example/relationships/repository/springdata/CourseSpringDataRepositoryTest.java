package springjpa.example.relationships.repository.springdata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;

import jakarta.transaction.Transactional;
import springjpa.example.relationships.entity.Course;
import springjpa.example.relationships.entity.Review;
import springjpa.example.relationships.entity.enums.ReviewRating;

@SpringBootTest
public class CourseSpringDataRepositoryTest {
	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	private CourseSpringDataRepository dao;

	@Test
	@Transactional
	public void findById_coursePresent() {
		Optional<Course> courseOptional = dao.findById(10001l);
		assertTrue(courseOptional.isPresent());
	}

	@Test
	@Transactional
	public void findById_courseAbsent() {
		Optional<Course> courseOptional = dao.findById(123123l);
		assertFalse(courseOptional.isPresent());
	}

	@Test
	@DirtiesContext
	public void saveTest() {
		long oldCount = dao.count();
		Course course = new Course("Distributed Systems");
		dao.save(course);
		logger.info("added the course distributed systems");
		course.setName("Distributed Data Systems");
		dao.save(course);
		logger.info("changed the name of distributed systems to distributed data systems");
		logger.info("Printing all courses after adding the course -> {}", dao.findAll());
		long newCount = dao.count();
		assertEquals(oldCount + 1, newCount);
	}

	@Test
	@DirtiesContext
	public void sortingTest() {
		dao.save(new Course("LEARN REACT in 100 Steps"));

		Sort sortStrat = Sort.by(Sort.Direction.DESC, "name").and(Sort.by(Sort.Direction.DESC, "id"));
		List<Course> sortedCourses = dao.findAll(sortStrat);
		logger.info("Printing all course in sorted order of their name desc and then id desc -> {}", sortedCourses);
	}
	
	@Test
	@DirtiesContext
	public void paginationTest() {
		dao.save(new Course("dummy course 1"));
		dao.save(new Course("dummy course 2"));
		dao.save(new Course("dummy course 3"));
		dao.save(new Course("dummy course 4"));
		dao.save(new Course("dummy course 5"));
		dao.save(new Course("dummy course 6"));
		dao.save(new Course("dummy course 7"));
		dao.save(new Course("dummy course 8"));
		dao.save(new Course("dummy course 9"));
		
		PageRequest pageRequest = PageRequest.of(0, 3);
		Page<Course> firstPage = dao.findAll(pageRequest);
		logger.info("First page -> {}", firstPage.getContent());
		
		Pageable secondPageable = firstPage.nextPageable();
		Page<Course> secondPage = dao.findAll(secondPageable);
		logger.info("Second Page -> {}", secondPage.getContent());
		
	}
	
	@Test
	@DirtiesContext
	public void customQuery_FindByName() {
		dao.save(new Course("dummy course"));
		dao.save(new Course("dummy course"));
		dao.save(new Course("dummy course"));
		dao.save(new Course("dummy course"));
		
		logger.info("Printing courses with name = dummy course -> {}", dao.findByName("dummy course"));
	}
	
	@Test
	@DirtiesContext
	public void customQuery_CountByName() {
		dao.save(new Course("dummy course"));
		dao.save(new Course("dummy course"));
		dao.save(new Course("dummy course"));
		dao.save(new Course("dummy course"));
		
		Long count = dao.countByName("dummy course");
		logger.info("Printing count of courses with name = dummy course -> {}", count);
		
		assertEquals(4, count);
	}	
	@Test
	@DirtiesContext
	public void customQuery_FindByNameAndId() {
		dao.save(new Course("dummy course"));
		dao.save(new Course("dummy course"));
		dao.save(new Course("dummy course"));
		dao.save(new Course("dummy course"));
		
		logger.info("Printing the course with name = dummy course and id = 3 -> {}", dao.findByNameAndId("dummy course", 3l));
	}
	
	@Test
	@DirtiesContext
	public void customQuery_FindByNameAndOrderById() {
		dao.save(new Course("dummy course"));
		dao.save(new Course("dummy course"));
		dao.save(new Course("dummy course"));
		dao.save(new Course("dummy course"));
		
		logger.info("Printing courses with name = dummy course in decreasing order of their id -> {}", dao.findByNameOrderByIdDesc("dummy course"));
	}
	
	@Test
	@Transactional // find out why transactional is needed here
	@DirtiesContext
	public void customQuery_deleteByName() {
		dao.save(new Course("dummy course1"));
		dao.save(new Course("dummy course2"));
		dao.save(new Course("dummy course3"));
		dao.save(new Course("dummy course4"));
		
		dao.deleteByName("dummy course2");
		
		logger.info("Printing all courses -> {}", dao.findAll());
	}
	
	@Test
	public void customQueriesTest() {
		logger.info("nameLike100Steps_JPQL -> {}", dao.nameLike100Steps_JPQL());
		logger.info("nameLike100Steps_SQL -> {}", dao.nameLike100Steps_SQL());
		logger.info("nameLike100Steps_namedQuery -> {}", dao.nameLike100Steps_namedQuery());
	}
	
	@Test
	@DirtiesContext
	@Transactional
	public void insertCourseWithReview() {
		Course dummyCourse = new Course("Dummy course");
		dummyCourse.addReview(new Review(ReviewRating.FIVE, "Dummy Review"));
		dao.save(dummyCourse);
		
		Course duplicate = dao.findByName("Dummy course").get(0);
		
		logger.info("Added dummy course with dummy review. Retriving it and then printing its reviews -> {}", duplicate.getReviews());
		
		assertIterableEquals(duplicate.getReviews(), dummyCourse.getReviews());
	}
	
}

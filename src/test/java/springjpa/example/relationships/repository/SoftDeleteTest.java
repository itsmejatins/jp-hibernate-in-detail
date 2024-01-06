package springjpa.example.relationships.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import springjpa.example.relationships.entity.Course;
import springjpa.example.relationships.repository.entitymanager.CourseRepository;
import springjpa.example.relationships.repository.springdata.CourseSpringDataRepository;

@SpringBootTest
public class SoftDeleteTest {

	@Autowired
	CourseSpringDataRepository sdDao;
	@Autowired
	CourseRepository emDao;
	@PersistenceContext
	EntityManager em;

	@Test
	@DirtiesContext
	public void testUsingDao() {
		sdDao.deleteById(10001L);
		assertNull(emDao.findById(10001l));
	}

	@Test
	@DirtiesContext
	@Transactional
	public void nativeQueryTest() {
		sdDao.deleteById(10001L);
		Query unmodifiedQuery = em.createNativeQuery("SELECT * FROM course WHERE id = 10001;", Course.class);
		List<Course> unmodifiedQueryResult = unmodifiedQuery.getResultList();

		Query modifiedQuery = em.createNativeQuery("SELECT * FROM course WHERE id = 10001 AND is_deleted = false;",
				Course.class);
		List<Course> modifiedQueryResult = modifiedQuery.getResultList();

		assertEquals(0, modifiedQueryResult.size());
		assertEquals(1, unmodifiedQueryResult.size());
	}

	@Test
	@DirtiesContext
	@Transactional
	public void preRemove() {
		Course course = em.find(Course.class, 10001l);
		em.remove(course);
		assertTrue(course.isDeleted());

	}
}

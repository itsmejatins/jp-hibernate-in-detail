package springjpa.example.relationships.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import springjpa.example.relationships.entity.Course;
import springjpa.example.relationships.entity.Student;

@SpringBootTest
public class JpqlTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	@PersistenceContext
	EntityManager em;
	
	@Test
	public void find_courses_without_students() {
		TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c WHERE c.students IS EMPTY", Course.class);
		List<Course> ans = query.getResultList();
		logger.info("Courses with no students are -> {}", ans);
	}
	
	@Test
	public void courses_with_more_than_two_students() {
		TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c WHERE SIZE(c.students) >= 2", Course.class);
		List<Course> ans = query.getResultList();
		logger.info("Courses with two or more students are -> {}", ans);
	}
	
	@Test
	public void print_courses_in_decreasing_order_of_students() {
		TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c ORDER BY size(c.students) DESC", Course.class);
		List<Course> ans = query.getResultList();
		logger.info("Courses with decreasing order of students are -> {}", ans);
	}
	
	@Test
	public void print_students_having_1234_in_their_passport_number() {
		TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s WHERE s.passport.number LIKE '%1234%'", Student.class);
		logger.info(" students whose passport number contain ‘1234’ string in it -> {} ", query.getResultList());
	}
	
	@Test
	public void natural_join_student_course() {
		//cannot use TypedQuery here
		Query query = em.createQuery("SELECT s, c FROM Student s JOIN s.courses c");
		List<Object[]> result = query.getResultList();
		for(Object[] res: result) {
			Student s = (Student)res[0];
			Course c = (Course)res[1];
			logger.info("Student = {}, Course = {} ", s, c);
		}
	}
	
	@Test
	public void left_join_student_course() {
		//cannot use TypedQuery here
		Query query = em.createQuery("SELECT s, c FROM Student s LEFT JOIN s.courses c");
		List<Object[]> result = query.getResultList();
		for(Object[] res: result) {
			Student s = (Student)res[0];
			Course c = (Course)res[1];
			logger.info("Student = {}, Course = {} ", s, c);
		}
	}
	
	@Test
	public void cross_join_student_course() {
		//cannot use TypedQuery here
		Query query = em.createQuery("SELECT s, c FROM Student s, Course c");
		List<Object[]> result = query.getResultList();
		for(Object[] res: result) {
			Student s = (Student)res[0];
			Course c = (Course)res[1];
			logger.info("Student = {}, Course = {} ", s, c);
		}
	}
}

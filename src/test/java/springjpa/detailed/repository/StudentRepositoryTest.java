package springjpa.detailed.repository;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import springjpa.detailed.entity.Course;
import springjpa.detailed.entity.Passport;
import springjpa.detailed.entity.Student;

@SpringBootTest
public class StudentRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	@PersistenceContext
	private EntityManager em;
	@Autowired
	StudentRepository sdao;
	@Autowired
	CourseRepository cdao;

	@Test
	@Transactional
	@DirtiesContext
	public void saveStudentWithPassportTest() {
		Passport pp = new Passport("ABCDEF");
		em.persist(pp);
		Student s = new Student("Aryan");
		s.setPassport(pp);
		em.persist(s);
		logger.info("fetchign all students -> {}", sdao.findAll());
		logger.info("Passport of Aryan -> {}", s.getPassport());
	}

	@Test
	@Transactional
	public void retrievePassportFromStudent() {
		Student jatin = sdao.findById(20001l);
		logger.info("Passport of jatin is -> {}", jatin.getPassport());
	}

	@Test
	@Transactional
	@DirtiesContext
	public void playingWithEntityManager() {
		Student s1 = new Student("s1");
		Student s2 = new Student("s2");

		em.persist(s1);
		em.merge(s2);

		s1.setName("s1_upd");
		s2.setName("s2_upd"); // does not gets updated in the database

		logger.info("playing with entity manager, find all students -> {}", sdao.findAll());
	}
	
	@Test
	@Transactional
	public void getCoursesTest() {
		logger.info("getting courses of jatin (20001) -> {}", sdao.getCourses(20001l));
	}
	
	@Test
	@Transactional
	public void insertStudentAndCourseHardcoded() {
		Student s = new Student("Ram");
		Course c = new Course("Ramayan");
		
		em.persist(s);
		em.persist(c);
		s.addCourse(c);
		c.addStudent(s);
		
		logger.info("details of student Ram -> {}", sdao.findById(1l));
		logger.info("details of course Ramayan -> {}", cdao.findById(1l));
		
		logger.info("fetching courses of Ram -> {}", sdao.getCourses(1l));
		logger.info("fetching students of Ramayan -> {}", cdao.getStudents(1l));
	}

}

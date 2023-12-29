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
import springjpa.detailed.entity.Passport;
import springjpa.detailed.entity.Student;

@SpringBootTest
public class StudentRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	@PersistenceContext
	private EntityManager em;
	@Autowired
	StudentRepository sdao;

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
	public void playingWithEntityManager() {
		Student s1 = new Student("s1");
		Student s2 = new Student("s2");

		em.persist(s1);
		em.merge(s2);

		s1.setName("s1_upd");
		s2.setName("s2_upd"); // does not gets updated in the database

		logger.info("playing with entity manager, find all students -> {}", sdao.findAll());
	}

}

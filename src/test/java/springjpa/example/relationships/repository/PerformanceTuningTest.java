package springjpa.example.relationships.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Subgraph;
import springjpa.example.relationships.entity.Course;
import springjpa.example.relationships.entity.Student;

@SpringBootTest
public class PerformanceTuningTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@PersistenceContext
	EntityManager em;

	@Test
	@Transactional
	public void creating_n_plus_one_problem() {
		List<Student> students = em.createQuery("SELECT s FROM Student s", Student.class).getResultList();
		for (Student s : students) {
			logger.info("Courses of student {} -> {}", s, s.getCourses());
		}

	}

	@Test
	@Transactional
	public void solving_n_plus_one_probem_EntityGraph() {
		EntityGraph<Student> entityGraph = em.createEntityGraph(Student.class);
		Subgraph<Object> subGraph = entityGraph.addSubgraph("courses");

		List<Student> students = em.createQuery("SELECT s FROM Student s", Student.class)
				.setHint("javax.persistence.loadgraph", entityGraph).getResultList();
		for (Student s : students) {
			logger.info("Courses of student {} -> {}", s, s.getCourses());
		}
	}
	
	@Test
	@Transactional
	public void solving_n_plus_one_probem_JoinFetch() {

		List<Student> students = em.createQuery("SELECT s FROM Student s JOIN FETCH s.courses c", Student.class).getResultList();
		for (Student s : students) {
			logger.info("Courses of student {} -> {}", s, s.getCourses());
		}
	}

}

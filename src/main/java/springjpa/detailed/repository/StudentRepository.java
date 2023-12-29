package springjpa.detailed.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import springjpa.detailed.entity.Student;

@Repository
public class StudentRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@PersistenceContext
	private EntityManager em;
	
	public List<Student> findAll()
	{
		TypedQuery<Student> query = em.createNamedQuery("find_all_students", Student.class);
		return query.getResultList();
	}
	public Student findById(Long id) {
		Student s = em.find(Student.class, id);
		return s;
	}

	public void save(Student s) {
		if (s.getId() == null)
			em.persist(s);
		else
			em.merge(s);
	}

	public void deleteById(Long id) {
		Student s = em.find(Student.class, id);
		if (s != null)
			em.remove(s);
		else
			logger.warn("Attempted to remove a entry which does not exist");
	}

}

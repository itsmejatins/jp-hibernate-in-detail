package springjpa.example.inheritance.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import springjpa.example.inheritance.entity.Employee;
import springjpa.example.inheritance.entity.FullTimeEmployee;
import springjpa.example.inheritance.entity.PartTimeEmployee;

@Repository
@Transactional
public class EmployeeRepository {
	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@PersistenceContext
	private EntityManager em;

	public List<Employee> findAll() {
		TypedQuery<Employee> query = em.createQuery("Select e from Employee e", Employee.class);
		return query.getResultList();
	}

	public Employee findById(Long id) {
		Employee s = em.find(Employee.class, id);
		return s;
	}

	public void save(Employee s) {
		if (s.getId() == null)
			em.persist(s);
		else
			em.merge(s);
	}

	public void deleteById(Long id) {
		Employee s = em.find(Employee.class, id);
		if (s != null)
			em.remove(s);
		else
			logger.warn("Attempted to remove an employee which does not exist in the database");
	}
	
	public List<FullTimeEmployee> findAllFTE() {
		TypedQuery<FullTimeEmployee> query = em.createQuery("Select e from FullTimeEmployee e", FullTimeEmployee.class);
		return query.getResultList();
	}
	
	public List<PartTimeEmployee> findAllPTE() {
		TypedQuery<PartTimeEmployee> query = em.createQuery("Select e from PartTimeEmployee e", PartTimeEmployee.class);
		return query.getResultList();
	}

}

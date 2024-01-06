package springjpa.example.relationships.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import springjpa.example.relationships.entity.Student;
import springjpa.example.relationships.entity.embeddables.Address;
import springjpa.example.relationships.repository.entitymanager.StudentRepository;

@SpringBootTest
public class EmbeddableTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	StudentRepository dao;

	@Test
	@DirtiesContext
	@Transactional
	public void test() {
		Student student = dao.findById(20001l);
		student.setAddress(new Address("H-11", "SocietyName", "Greater Noida"));
		dao.save(student);

		logger.info("Printing all students -> {}", dao.findAll());

		Student duplicate = dao.findById(20001l);
		assertEquals(new Address("H-11", "SocietyName", "Greater Noida"), duplicate.getAddress());
	}
}

package springjpa.example.inheritance;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import springjpa.example.inheritance.repository.EmployeeRepository;

@SpringBootTest
public class EmployeeRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	@Autowired
	private EmployeeRepository dao;

	@Test
	public void insert() {
		logger.error("are baap re test test test  !!!");
	}

}

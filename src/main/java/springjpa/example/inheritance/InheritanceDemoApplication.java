package springjpa.example.inheritance;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springjpa.example.inheritance.entity.FullTimeEmployee;
import springjpa.example.inheritance.entity.PartTimeEmployee;
import springjpa.example.inheritance.repository.EmployeeRepository;

@SpringBootApplication
public class InheritanceDemoApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	private EmployeeRepository dao;

	public static void main(String args[]) {
		SpringApplication.run(InheritanceDemoApplication.class, args);
	}

	@Override
//	@Transactional
	public void run(String... args) throws Exception {

		dao.save(new PartTimeEmployee("pte 1", new BigDecimal("15")));
		dao.save(new FullTimeEmployee("fte 1", new BigDecimal("10000")));
		dao.save(new FullTimeEmployee("fte 2", new BigDecimal("200000")));
		dao.save(new PartTimeEmployee("pte 1", new BigDecimal("20")));

//		for inheritance, not for mapped super class
//		logger.info("printing all employees -> {}", dao.findAll());
//		logger.info("find by id 1 -> {}", dao.findById(1l));

//		for mapped super class 
		logger.info("Mapped super class: retrive all FTE -> {}", dao.findAllFTE());
		logger.info("Mapped super class: retrive all PTE -> {}", dao.findAllPTE());

	}
}

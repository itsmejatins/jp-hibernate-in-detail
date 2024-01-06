package springjpa.example.relationships;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springjpa.example.relationships.repository.entitymanager.CourseRepository;
import springjpa.example.relationships.repository.springdata.CourseSpringDataRepository;

@SpringBootApplication
public class RelationshipsDemoApplication implements CommandLineRunner{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	CourseSpringDataRepository sdDao;
	@Autowired
	CourseRepository emDao;
	
	public static void main(String[] args) {
		SpringApplication.run(RelationshipsDemoApplication.class, args);
		// Run unit tests  
		// All demonstrations shown there.
	}

	@Override
	public void run(String... args) throws Exception {
//		sdDao.deleteById(10001L);
//		logger.info("retrieving course with id = 10001l after soft deleting it -> {}", sdDao.findById(10001l));
		
	}

}

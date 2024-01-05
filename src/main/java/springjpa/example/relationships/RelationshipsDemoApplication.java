package springjpa.example.relationships;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springjpa.example.relationships.entity.Course;
import springjpa.example.relationships.repository.springdata.CourseSpringDataRepository;

@SpringBootApplication
public class RelationshipsDemoApplication implements CommandLineRunner{

	@Autowired
	CourseSpringDataRepository dao;
	
	public static void main(String[] args) {
		SpringApplication.run(RelationshipsDemoApplication.class, args);
		// Run unit tests  
		// All demonstrations shown there.
	}

	@Override
	public void run(String... args) throws Exception {
		
	}

}

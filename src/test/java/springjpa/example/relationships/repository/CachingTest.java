package springjpa.example.relationships.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import springjpa.example.relationships.entity.Course;
import springjpa.example.relationships.entity.Review;
import springjpa.example.relationships.repository.entitymanager.CourseRepository;
import springjpa.example.relationships.repository.springdata.CourseSpringDataRepository;

@SpringBootTest
public class CachingTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	CourseRepository eDao;
	@Autowired
	CourseSpringDataRepository sdDao;

	@Test
	@Transactional
	public void first_level_cache_test_1() {
		logger.info("eDao :: First call to find course with id = 10001L -> {}", eDao.findById(10001L));
		logger.info("eDao :: Second call to find course with id = 10001L -> {}", eDao.findById(10001L));
		
		logger.info("sdDao :: First call to find course with id = 10001L -> {}", sdDao.findById(10001L));
		logger.info("sdDao :: Second call to find course with id = 10001L -> {}", sdDao.findById(10001L));
		
	}

	@Test
	public void first_level_cache_test_2() {
		logger.info("eDao :: First call to find course with id = 10001L -> {}", eDao.findById(10001L));
		logger.info("eDao :: Second call to find course with id = 10001L -> {}", eDao.findById(10001L));

		logger.info("sdDao :: First call to find course with id = 10001L -> {}", sdDao.findById(10001L));
		logger.info("sdDao :: Second call to find course with id = 10001L -> {}", sdDao.findById(10001L));

	}
	
	@Test
	@Transactional // useless, no caching
	public void first_level_cache_test_3() {
		logger.info("eDao :: First call to find course which does not exists in the database -> {}", eDao.findById(123123l));
		logger.info("eDao :: Second call to find course which does not exists in the database -> {}", eDao.findById(123123l));
		
		logger.info("sdDao :: First call to find course which does not exists in the database -> {}", sdDao.findById(123123l));
		logger.info("sdDao :: Second call to find course which does not exists in the database -> {}", sdDao.findById(123123l));
		
	}
	
	
	@Transactional
	public void m() {
		logger.info("hi hi hi -> {}", sdDao.findById(10001l));
	}
	
	@Test
	public void n() {
		m();
		m();
	}
}

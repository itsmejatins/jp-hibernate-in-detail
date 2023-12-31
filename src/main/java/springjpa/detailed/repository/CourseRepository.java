package springjpa.detailed.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import springjpa.detailed.entity.Course;
import springjpa.detailed.entity.Review;
import springjpa.detailed.entity.Student;

@Repository
@Transactional
public class CourseRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	private EntityManager em;
	
	@Autowired
	private ReviewRepository rDao;
	
	public void save(Course course) {
		if(course.getId() != null)
			em.persist(course);
		else
			em.merge(course);
	}

	public Course findById(Long id) {
		Course course = em.find(Course.class, id);
		if (course == null)
			logger.warn("Attempted to retrieve a course which does not exists in the table!");
		return course;
	}

	public List<Course> findAll() {
		TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c", Course.class);
		return query.getResultList();
	}

	public List<Review> getReviews(Long courseId) {
		Course course = em.find(Course.class, courseId);
		if (course != null) {
			List<Review> reviews = course.getReviews();
			return reviews;
		}
		logger.warn("Course does not exists");
		return null;
	}

	public void addReviews(Long courseId, List<Review> reviews) {

		Course course = em.find(Course.class, courseId);
		if (course == null) {
			logger.warn(
					"Attempted to add reviews to a course which does not exists. Not persisting the provided reviews");
			return;
		}

		for (Review review : reviews) {
			
			rDao.save(review);
			course.addReview(review);
		}
	}
	
	public void deleteById(Long id) {
		Course course = em.find(Course.class, id);
		if(course == null) 
			logger.warn("Attempted to delete a course which does not exists in the database");
		else 
			em.remove(course);
	}
	
	public List<Student> getStudents(Long courseId){
		Course course = em.find(Course.class, courseId);
		if(course == null) {
			logger.warn("Attempting to find students of a course which does not exists in the database, return a null pointer to List<Student>");
			return null;
		}
		return course.getStudents();
	}
	
}

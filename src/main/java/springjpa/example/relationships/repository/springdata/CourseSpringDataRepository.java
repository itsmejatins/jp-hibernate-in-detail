package springjpa.example.relationships.repository.springdata;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import springjpa.example.relationships.entity.Course;

public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {

	public List<Course> findByName(String name);
	public Long countByName(String name);
	public List<Course> findByNameAndId(String name, Long id);
	public List<Course> findByNameOrderByIdDesc(String name);
	public void deleteByName(String name);
	@Query("SELECT c FROM Course c WHERE name LIKE '%100 Steps%'")
	public List<Course> nameLike100Steps_JPQL();
	@Query(value = "SELECT * FROM course WHERE name LIKE '%100 Steps%' ", nativeQuery = true)
	public List<Course> nameLike100Steps_SQL();
	@Query(name ="courses_with_name_like_100_steps")
	public List<Course> nameLike100Steps_namedQuery();

}

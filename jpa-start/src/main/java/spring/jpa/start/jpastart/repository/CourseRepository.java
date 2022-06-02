package spring.jpa.start.jpastart.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.jpa.start.jpastart.entity.Course;
import spring.jpa.start.jpastart.entity.Review;
import spring.jpa.start.jpastart.entity.Student;

@Repository
@Transactional
public class CourseRepository {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EntityManager entityManager;
	
	public Course findById(Long id) {
		return entityManager.find(Course.class, id);
	}
	
	public Course save(Course course) {
		if(course.getId()==null) {
			entityManager.persist(course);
		}
		else {
			entityManager.merge(course);
		}
		return course;
	}
	
	public void deleteById(Long id) {
		Course course = findById(id);
		entityManager.remove(course);
	}
	
	public void addReviewsForCourse(Long courseId, List<Review> reviews) {
		Course course = findById(courseId);
		for (Review review:reviews) {
			course.addReview(review);
			review.setCourse(course);
			entityManager.persist(review);
		}
	}
	
	public void addHardCodedReviewsForCourse() {
		//get the course 10003
		Course course = findById(10003L);
		logger.info("course.getReviews() -> {}", course.getReviews());
		
		//add 2 reviews to it
		Review review1 = new Review("5", "Great Hands-on Stuff.");	
		Review review2 = new Review("5", "Hatsoff.");
		
		//setting the relationship
		course.addReview(review1);
		review1.setCourse(course);
		
		course.addReview(review2);
		review2.setCourse(course);
		
		//save it to the database
		entityManager.persist(review1);
		entityManager.persist(review2);
	}
	
	public void playWithEntityManager() {
		Course course = new Course("Web Services");
		entityManager.persist(course);
		entityManager.flush();
		
		Course course2 = new Course("Angular Js");
		entityManager.persist(course2);
		entityManager.flush();
		
		
		
		course.setName("Web Services - Updated");
		entityManager.flush();
		course.setName("Web Services - Updated2");
		
		entityManager.refresh(course);
		
		entityManager.detach(course2);
		
		course2.setName("Angular Js - Updated");
		course2.setName("Angular Js - Updated - Updated");
		
		entityManager.clear();
		
	}
	
	public List jpql_basic() {
		Query query = entityManager.createQuery("Select c from Course c");
		List resultList = query.getResultList();
		return resultList;
	}
	
	public List<Course> jpql_typed() {
		TypedQuery<Course> query = entityManager.createNamedQuery("query_get_all_courses", Course.class);
		List<Course> resultList = query.getResultList();
		return resultList;
	}
	
	public void retrieveCourseAndStudents() {
		Course course = entityManager.find(Course.class, 10001L);
		
		logger.info("student -> {}", course);
		logger.info("courses -> {}", course.getStudents());
	}
	
	public void findById_firstLevelCacheDemo() {
		
		Course course = findById(10001L);
		logger.info("First Course Retrieved {}", course);

		Course course1 = findById(10001L);
		logger.info("First Course Retrieved again {}", course1);

	}
}

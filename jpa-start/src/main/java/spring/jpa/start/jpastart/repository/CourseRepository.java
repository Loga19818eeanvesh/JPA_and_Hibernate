package spring.jpa.start.jpastart.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.jpa.start.jpastart.entity.Course;

@Repository
@Transactional
public class CourseRepository {
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
}

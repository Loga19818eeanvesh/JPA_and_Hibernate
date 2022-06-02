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
import spring.jpa.start.jpastart.entity.Student;

@Repository
@Transactional
public class CourseJpqlRepository {
	@Autowired
	private EntityManager entityManager;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public List findById_basic() {
		Query query = entityManager.createQuery("Select c From Course c");
		List resultList = query.getResultList();
		return resultList;
	}
	
	public List<Course> findById_typed() {
		TypedQuery<Course> query = entityManager.createNamedQuery("query_get_all_courses", Course.class);
		List<Course> resultList = query.getResultList();
		return resultList;
	}
	
	public List<Course> courses_without_students() {
		TypedQuery<Course> query = entityManager.createQuery("Select c from Course c where c.students is empty", Course.class);
		List<Course> resultList = query.getResultList();
		return resultList;
	}
	
	public List<Course> courses_with_atleast_2_students() {
		TypedQuery<Course> query = entityManager.createQuery("Select c from Course c where size(c.students) >= 2", Course.class);
		List<Course> resultList = query.getResultList();
		return resultList;
	}
	
	public List<Course> jpql_courses_ordered_by_students() {
		TypedQuery<Course> query = entityManager.createQuery("Select c from Course c order by size(c.students) desc", Course.class);
		List<Course> resultList = query.getResultList();
		return resultList;
	}
	
	public List<Student> jpql_students_with_passports_in_a_certain_pattern() {
		TypedQuery<Student> query = entityManager.createQuery("Select s from Student s where s.passport.number like '%1234%'", Student.class);
		List<Student> resultList = query.getResultList();
		return resultList;
	}
	
	public void join(){
		Query query = entityManager.createQuery("Select c, s from Course c JOIN c.students s");
		List<Object[]> resultList = query.getResultList();
		logger.info("Results Size -> {}", resultList.size());
		for(Object[] result:resultList){
			logger.info("Course{} Student{}", result[0], result[1]);
		}
	}
	
	public void left_join(){
		Query query = entityManager.createQuery("Select c, s from Course c LEFT JOIN c.students s");
		List<Object[]> resultList = query.getResultList();
		logger.info("Results Size -> {}", resultList.size());
		for(Object[] result:resultList){
			logger.info("Course{} Student{}", result[0], result[1]);
		}
	}
	
	public void cross_join(){
		Query query = entityManager.createQuery("Select c, s from Course c, Student s");
		List<Object[]> resultList = query.getResultList();
		logger.info("Results Size -> {}", resultList.size());
		for(Object[] result:resultList){
			logger.info("Course{} Student{}", result[0], result[1]);
		}
	}
}

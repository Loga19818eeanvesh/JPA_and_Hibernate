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
public class CourseJpqlRepository {
	@Autowired
	private EntityManager entityManager;
	
	public List findById_basic() {
		Query query = entityManager.createQuery("Select c From Course c");
		List resultList = query.getResultList();
		return resultList;
	}
	
	public List<Course> findById_typed() {
		TypedQuery<Course> query = entityManager.createNamedQuery("Select c From Course c", Course.class);
		List<Course> resultList = query.getResultList();
		return resultList;
	}
}

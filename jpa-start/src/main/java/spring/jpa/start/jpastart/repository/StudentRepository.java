package spring.jpa.start.jpastart.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.jpa.start.jpastart.entity.Passport;
import spring.jpa.start.jpastart.entity.Student;

@Repository
@Transactional
public class StudentRepository {
	@Autowired
	EntityManager entityManager;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public Student findById(Long id) {
		return entityManager.find(Student.class, id);
	}
	
	public Student save(Student student) {
		if(student.getId()==null) {
			entityManager.persist(student);
		}
		else {
			entityManager.merge(student);
		}
		return student;
	}
	
	public void deleteById(Long id) {
		Student student = findById(id);
		entityManager.remove(student);
	}
	
	public void saveStudentWithPassport() {
		Passport passport = new Passport("Z123456");
		entityManager.persist(passport);

		Student student = new Student("Mike");

		student.setPassport(passport);
		entityManager.persist(student);	
	}
	
	public void someOperationToUnderstandPersistenceContext() {
		//Database Operation 1 - Retrieve student
		Student student = entityManager.find(Student.class, 20001L);
		//Persistence Context (student)
		
		
		//Database Operation 2 - Retrieve passport
		Passport passport = student.getPassport();
		//Persistence Context (student, passport)

		//Database Operation 3 - update passport
		passport.setNumber("E123457");
		//Persistence Context (student, passport++)
		
		//Database Operation 4 - update student
		student.setName("Ranga - updated");
		//Persistence Context (student++ , passport++)
	}
	
	public void retrievePassportAndAssociatedStudent() {
		Passport passport = entityManager.find(Passport.class, 40001L);
		logger.info("passport -> {}", passport);
		logger.info("student -> {}", passport.getStudent());
	}
	
	
}

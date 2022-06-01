package spring.jpa.start.jpastart;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import spring.jpa.start.jpastart.entity.Course;
import spring.jpa.start.jpastart.entity.Review;
import spring.jpa.start.jpastart.repository.CourseRepository;
import spring.jpa.start.jpastart.repository.StudentRepository;

@SpringBootApplication
public class JpaStartApplication implements CommandLineRunner{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(JpaStartApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		//studentRepository.saveStudentWithPassport();
		
		Course course = courseRepository.findById(10001L);
		logger.info("Course 10001 -> {}", course);
		
		courseRepository.deleteById(10002L);
		
		courseRepository.save(new Course("Microservices"));
		
		courseRepository.playWithEntityManager();
		
		List courses= courseRepository.jpql_basic();
		logger.info("Courses List -> {}", courses);
		
		List<Course> coursesList = courseRepository.jpql_typed();
		logger.info("Courses Typed List -> {}", coursesList);
		
		studentRepository.saveStudentWithPassport();
		
		studentRepository.retrievePassportAndAssociatedStudent();
		
		List<Review> reviews = new ArrayList<>();
		
		reviews.add(new Review("5", "Great Hands-on Stuff."));	
		reviews.add(new Review("5", "Hatsoff."));

		courseRepository.addReviewsForCourse(10003L, reviews );	
		
	}

}

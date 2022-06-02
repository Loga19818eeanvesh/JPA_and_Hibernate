package spring.jpa.start.jpastart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.jpa.start.jpastart.entity.Course;

public interface CourseSpringDataRepository extends JpaRepository<Course,Long>{

}

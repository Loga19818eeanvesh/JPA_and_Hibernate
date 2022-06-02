package spring.jpa.start.jpastart.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@NamedQueries(value = { 
		@NamedQuery(name = "query_get_all_courses", 
				query = "Select  c  From Course c"),
		@NamedQuery(name = "query_get_100_Step_courses", 
		query = "Select  c  From Course c where name like '%100 Steps'") })
//@Table(name="CourseDetalis")
@Cacheable
public class Course {
	@Id
	@GeneratedValue
	private Long id;
	
	//@Column(name="fullname", nullable=false)
	private String name;
	
	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;

	@CreationTimestamp
	private LocalDateTime createdDate;
	
	@OneToMany(mappedBy="course")
	private List<Review> reviews = new ArrayList<>();
	
	@ManyToMany(mappedBy="courses")
	private List<Student> students = new ArrayList<>();

	protected Course() {
		super();
	}

	public Course(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}
	
	public List<Review> getReviews() {
		return reviews;
	}

	public void addReview(Review review) {
		this.reviews.add(review);
	}
	
	public void removeReview(Review review) {
		this.reviews.remove(review);
	}
	
	public List<Student> getStudents() {
		return students;
	}

	public void addStudent(Student student) {
		this.students.add(student);
	}
	
	public void removeStudent(Student student) {
		this.students.remove(student);
	}
	
	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", lastUpdatedDate=" + lastUpdatedDate + ", createdDate="
				+ createdDate + "]";
	}

	

	
}

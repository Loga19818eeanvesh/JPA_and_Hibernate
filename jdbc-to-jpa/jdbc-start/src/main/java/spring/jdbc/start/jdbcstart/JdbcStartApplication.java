package spring.jdbc.start.jdbcstart;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import spring.jdbc.start.jdbcstart.entity.Person;
import spring.jdbc.start.jdbcstart.jdbc.PersonJbdcDao;

//@SpringBootApplication
public class JdbcStartApplication implements CommandLineRunner{
	@Autowired
	private PersonJbdcDao personJdbcDao;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		SpringApplication.run(JdbcStartApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		logger.info("All users -> {}", personJdbcDao.findAll());
		logger.info("User id 10001 -> {}", personJdbcDao.findById(10001));
		logger.info("Deleting 10001 -> No of Rows Deleted - {}", personJdbcDao.deleteById(10001));
		logger.info("User name person1 -> {}", personJdbcDao.findByName("person1"));
		logger.info("Inserting 10004 -> {}", 
				personJdbcDao.insert(new Person(10004, "person4", "Berlin", new Date())));
		logger.info("Update 10003 -> {}", 
				personJdbcDao.update(new Person(10003, "person3-up", "Utrecht", new Date())));
	}

}

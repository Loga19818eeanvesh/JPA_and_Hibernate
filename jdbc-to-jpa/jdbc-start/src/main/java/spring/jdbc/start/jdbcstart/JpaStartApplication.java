package spring.jdbc.start.jdbcstart;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import spring.jdbc.start.jdbcstart.entity.Person;
import spring.jdbc.start.jdbcstart.jpa.PersonJpaRepository;

@SpringBootApplication
public class JpaStartApplication implements CommandLineRunner{
	@Autowired
	private PersonJpaRepository personJpaRepository;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		SpringApplication.run(JpaStartApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		logger.info("User id 10001 -> {}", personJpaRepository.findById(10001));
		personJpaRepository.deleteById(10001);
		//logger.info("User name person1 -> {}", personJdbcDao.findByName("person1"));
		logger.info("Inserting 10004 -> {}", 
				personJpaRepository.insert(new Person( "person4", "Berlin", new Date())));
		logger.info("Update 10003 -> {}", 
				personJpaRepository.update(new Person(10003, "person3-up", "Utrecht", new Date())));
		logger.info("All users -> {}", personJpaRepository.findAll());
	}

}

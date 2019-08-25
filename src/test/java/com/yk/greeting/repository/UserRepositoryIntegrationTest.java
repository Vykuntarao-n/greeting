package com.yk.greeting.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.yk.greeting.entity.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryIntegrationTest {
	
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UserRepository userRepository;

	@Test
	public void whenFindByFirstNameAndLastNameAllIgnoreCase_thenReturnUser() {
		User user = new User("FisrtName", "LastName");
		entityManager.persist(user);
		entityManager.flush();
		// when
		Optional<User> found = userRepository.findByFirstNameAndLastNameAllIgnoreCase(user.getFirstName(),
				user.getLastName());
		// then
		assertThat(found.get().getFirstName(), is(user.getFirstName()));

	}

}

package com.yk.greeting.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yk.greeting.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	/**
	 * Find {@link User} matching with the First Name and LastName by ignoring the case
	 * @param firstname
	 * @param lastname
	 * @return
	 */
	Optional<User> findByFirstNameAndLastNameAllIgnoreCase(String firstname, String lastname);

}

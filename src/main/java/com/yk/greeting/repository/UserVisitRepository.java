package com.yk.greeting.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.yk.greeting.entity.UserVisit;
import com.yk.greeting.projection.VisitorProjection;
/**
 * 
 * @author Vykuntarao
 *
 */
@Repository
public interface UserVisitRepository
        extends JpaRepository<UserVisit, Long> {
	
	/**
	 * Find the Distinct FirstNames of the {@link UserVisit}
	 * @return FirstNames
	 */
	//@Query(value= "Select distinct Last_Name  from User JOIN User_visit ON User.user_id =  User_visit.user_id " , nativeQuery = true)
	@Query("SELECT distinct u.firstName FROM UserVisit uv JOIN uv.user u"  )
	Set<String> findDistinctByFirstName();
	
	
	/**
	 * Find the Distinct LastNames of the {@link UserVisit}
	 * @return LastNames
	 */
	@Query("SELECT distinct u.lastName FROM UserVisit uv JOIN uv.user u"  )
	Set<String> findDistinctByLastName();
	
	/**
	 * Returns the visitCount for all the Visitors FirstName and LastName
	 * @return Visitor visitCount, Visitor FirstName and LastName
	 */
	//@Query(value = "Select count(*)  as visitCount , first_Name as userFirst ,  last_Name as userLast "
	//		+ " from User JOIN  User_visit ON  User.user_id =  User_visit.user_id "
	//		+ " group by first_Name , last_Name" , nativeQuery = true)
	@Query("SELECT count(uv) as visitCount , u.firstName as userFirst , u.lastName as userLast  "
			+ " FROM UserVisit uv JOIN uv.user u GROUP BY u.firstName , u.lastName "  )
	List<VisitorProjection> findVisitors();
 
}

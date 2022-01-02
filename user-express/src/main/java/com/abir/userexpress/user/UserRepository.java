package com.abir.userexpress.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	
	@Query("select u from User u Where u.email=?1 OR u.name=?1")
	public User findByLogger(String logger);

}

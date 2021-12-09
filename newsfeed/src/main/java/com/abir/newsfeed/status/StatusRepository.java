package com.abir.newsfeed.status;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository("statusRepository")
public interface StatusRepository extends PagingAndSortingRepository<Status, Long> {

}

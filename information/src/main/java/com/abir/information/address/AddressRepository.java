package com.abir.information.address;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository("addressRepository")
public interface AddressRepository extends PagingAndSortingRepository<Address, Long>{

}

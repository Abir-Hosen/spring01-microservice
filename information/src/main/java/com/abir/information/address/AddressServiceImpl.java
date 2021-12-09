package com.abir.information.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("addressService")
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressRepository addressRepository;

	private Pageable pageable = PageRequest.of(0, 1000);

	@Override
	public Object getById(Long id) {
		return addressRepository.findById(id).get();
	}
	
	@Override
	public Page<?> getAll() {
		return addressRepository.findAll(pageable);
	}

	@Override
	public Address save(Object object) {
		return addressRepository.save((Address) object);
	}

	@Override
	public void delete(Long id) {
		addressRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		addressRepository.deleteAll();
	}

}

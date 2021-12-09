package com.abir.newsfeed.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.abir.newsfeed.template.Address;
import com.abir.newsfeed.template.RespTempStatusWithAddress;

@Service("statusService")
public class StatusServiceImpl implements StatusService {

	@Autowired
	private StatusRepository statusRepository;
	
	@Autowired
	private RestTemplate restTemplate;

	private Pageable pageable = PageRequest.of(0, 1000);

	@Override
	public Object getById(Long id) {
		return statusRepository.findById(id).get();
	}
	
	@Override
	public Page<?> getAll() {
		return statusRepository.findAll(pageable);
	}

	@Override
	public Status save(Object object) {
		return statusRepository.save((Status) object);
	}

	@Override
	public void delete(Long id) {
		statusRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		statusRepository.deleteAll();
	}

	@Override
	public RespTempStatusWithAddress getStatusByIdWithAddress(Long id) {
		Status status = statusRepository.findById(id).get();
		Address address = restTemplate.getForObject("http://INFORMATION-SERVICE/info/api/address/"+status.getAddressId(), Address.class);
		RespTempStatusWithAddress respTempStatusWithAddress = new RespTempStatusWithAddress();
		respTempStatusWithAddress.setStatus(status);
		respTempStatusWithAddress.setAddress(address);
		return respTempStatusWithAddress;
	}

}

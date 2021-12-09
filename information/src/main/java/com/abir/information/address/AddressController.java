package com.abir.information.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/address")
public class AddressController {

	@Autowired
	private AddressService addressService;

	@GetMapping("/{id}")
	public Address getAddressById(@PathVariable Long id) {
		return (Address) addressService.getById(id);
	}
	
	@GetMapping
	public Page<?> getAllAddress() {
		return addressService.getAll();
	}

	@PostMapping
	public Address saveAddress(@RequestBody Address address) {
		return (Address) addressService.save(address);
	}

	@GetMapping("/hello")
	public String helloWorld() {
		return "Hello world";
	}

}

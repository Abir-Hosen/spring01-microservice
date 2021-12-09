package com.abir.newsfeed.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abir.newsfeed.template.RespTempStatusWithAddress;

@RestController
@RequestMapping("/api/status")
public class StatusController {

	@Autowired
	private StatusService statusService;

	@GetMapping("/{id}")
	public Status getStatusById(@PathVariable Long id) {
		return (Status) statusService.getById(id);
	}
	
	@GetMapping
	public Page<?> getAllStatus() {
		return statusService.getAll();
	}

	@PostMapping
	public Status saveStatus(@RequestBody Status address) {
		return (Status) statusService.save(address);
	}

	@GetMapping("/hello")
	public String helloWorld() {
		return "Hello world";
	}
	

	@GetMapping("/address/{id}")
	public RespTempStatusWithAddress statusByIdWithAddress(@PathVariable Long id) {
		return statusService.getStatusByIdWithAddress(id);
	}

}

package com.abir.servicegateway.common;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackMethodController {

	@GetMapping("/info-service-fallback")
	public String informationServiceFallBackMethod() {
		return "Information service is getting longer than expected. Please try again leter.";
	}

	@GetMapping("/feed-service-fallback")
	public String newsfeedServiceFallBackMethod() {
		return "Newsfeed service is getting longer than expected. Please try again leter.";
	}

}

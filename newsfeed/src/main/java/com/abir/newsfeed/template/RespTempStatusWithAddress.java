package com.abir.newsfeed.template;

import com.abir.newsfeed.status.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespTempStatusWithAddress {
	
	private Status status;
	private Address address;

}

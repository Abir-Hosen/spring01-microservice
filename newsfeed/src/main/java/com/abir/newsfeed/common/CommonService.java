package com.abir.newsfeed.common;

import org.springframework.data.domain.Page;

public interface CommonService {

	public Object getById(Long id);
	public Page<?> getAll();
	public Object save(Object object);
	public void delete(Long id);
	public void deleteAll();
}

package com.abir.userexpress.user;

import com.abir.userexpress.common.CommonService;

public interface UserService extends CommonService {

	public User findByLogger(String logger);
}

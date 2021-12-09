package com.abir.newsfeed.status;

import com.abir.newsfeed.common.CommonService;
import com.abir.newsfeed.template.RespTempStatusWithAddress;

public interface StatusService extends CommonService {
	
	public RespTempStatusWithAddress getStatusByIdWithAddress(Long id);

}

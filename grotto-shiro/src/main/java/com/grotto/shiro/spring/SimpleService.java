package com.grotto.shiro.spring;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Component;

/**
 * Simple Service with methods protected with annotations.
 */
//@Component
public class SimpleService {
	private static Logger logger = LogManager.getLogger();

	@RequiresPermissions({"write"})
	public void writeRestrictedCall() {
		logger.info("Executing method that requires the 'write' permission");
	}

	@RequiresPermissions({"read"})
	public void readRestrictedCall() {
		logger.info("Executing method that requires the 'read' permission");
	}
}

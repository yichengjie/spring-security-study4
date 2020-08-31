package com.yicj.security.browser.session.support;

import org.springframework.web.context.request.RequestAttributes;

public interface SessionStrategy {

	void setAttribute(RequestAttributes request, String name, Object value);
	
	Object getAttribute(RequestAttributes request, String name);
	
	void removeAttribute(RequestAttributes request, String name);
	
}
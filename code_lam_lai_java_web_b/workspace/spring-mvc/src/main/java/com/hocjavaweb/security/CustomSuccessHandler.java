package com.hocjavaweb.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.hocjavaweb.utils.SecurityUtils;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {
		String targetUrl = determineTargetUrl(authentication);
		if (response.isCommitted()) {
			return;
		}
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}
	
	private String determineTargetUrl(Authentication authentication) {
		String url="";
		List<String> code = SecurityUtils.getAuthorities();
		if(isAdmin(code)) {
			url="/quan-tri/trang-chu";
		}else if(isManager(code)) {
			url="/quan-tri/trang-chu";
		}else if(isUser(code)) {
			url="/trang-chu";
		}
		return url;
	}
	
	private Boolean isAdmin(List<String> code) {
		if(code.contains("ADMIN")) {
			return true;
		}
		return false;
	}
	
	private Boolean isManager(List<String> code) {
		if(code.contains("MANAGER")) {
			return true;
		}
		return false;
	}
	
	private Boolean isUser(List<String> code) {
		if(code.contains("USER")) {
			return true;
		}
		return false;
	}

	public RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}
	
}

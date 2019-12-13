package com.hocjavaweb.controller.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hocjavaweb.dto.CategoryDTO;
import com.hocjavaweb.dto.NewsDTO;
import com.hocjavaweb.service.ICategoryService;
import com.hocjavaweb.service.INewsService;

@Controller(value = "homeControllerOfWeb")
public class HomeController {
	
	@Autowired
	private INewsService newsService;
	
	@Autowired
	private ICategoryService categoryService;
	
	 @RequestMapping(value = "/trang-chu", method = RequestMethod.GET)
	 public ModelAndView homePage(@RequestParam(value = "code",required = false) String code) {
	     ModelAndView mav = new ModelAndView("web/home");
	     mav.addObject("listCategoryModel",categoryService.findALlList());
	     mav.addObject("listSlide",newsService.findAll());
	     NewsDTO dto = new NewsDTO();
	     dto.setListResult(newsService.findAll());
	     mav.addObject("model",dto);
	     if(code != null ) {
	    	 	try {
	    	 		CategoryDTO categoryDTO = categoryService.findOneByCode(code);
	    	 		Long id = categoryDTO.getId();
		    	    dto.setListResult(newsService.finaAllByCategoryId(id));
		    	    mav.addObject("model",dto);
				} catch (Exception e) {
					e.printStackTrace();
				}
	    	 	
	     }
	     return mav;
	 }
	 
	 @RequestMapping(value ="/dang-nhap", method = RequestMethod.GET)
	 public ModelAndView loginPage() {
	     ModelAndView mav = new ModelAndView("login");
	     return mav;
	 }
	 
	 @RequestMapping(value ="/thoat", method = RequestMethod.GET)
	 public ModelAndView logoutPage(HttpServletRequest request, HttpServletResponse response) {
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 if (auth != null) {
			 new SecurityContextLogoutHandler().logout(request, response, auth);
		 }
	     return new ModelAndView("redirect:/trang-chu");
	 }
	 
	 @RequestMapping(value ="/accessDenied", method = RequestMethod.GET)
	 public ModelAndView accessDeniedPage() {
		 return new ModelAndView("redirect:/dang-nhap?accessDenied");
	 }
	 
}

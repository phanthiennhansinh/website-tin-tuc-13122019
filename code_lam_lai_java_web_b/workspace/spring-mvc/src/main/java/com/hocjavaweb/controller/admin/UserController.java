package com.hocjavaweb.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	
	@RequestMapping(value = "/quan-tri/user/danhsach", method = RequestMethod.GET)
	public ModelAndView showList() {
	    ModelAndView mav = new ModelAndView("admin/user");
	    return mav;
	}

	
}

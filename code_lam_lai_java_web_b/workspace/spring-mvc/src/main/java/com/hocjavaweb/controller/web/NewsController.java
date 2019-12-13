package com.hocjavaweb.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hocjavaweb.dto.CategoryDTO;
import com.hocjavaweb.dto.NewsDTO;
import com.hocjavaweb.service.ICategoryService;
import com.hocjavaweb.service.INewsService;

@Controller
public class NewsController {

	@Autowired
	private INewsService newService;
	
	@Autowired
	private ICategoryService categoryService;

	 @RequestMapping(value = "/bai-viet/chi-tiet", method = RequestMethod.GET)
	 public ModelAndView sanphamPage(@RequestParam(value = "id",required = false) int id) {
	     ModelAndView mav = new ModelAndView("web/detail");
	     List<CategoryDTO> list = categoryService.findALlList();
	     mav.addObject("listCategory",list);
	     NewsDTO dto = newService.findById(id);
	     mav.addObject("detailNews",dto);
	     return mav;
	 }
}

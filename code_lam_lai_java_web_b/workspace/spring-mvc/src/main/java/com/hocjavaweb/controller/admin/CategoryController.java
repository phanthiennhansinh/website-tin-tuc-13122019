package com.hocjavaweb.controller.admin;

import java.util.List;
import java.util.Map;

import org.aspectj.bridge.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hocjavaweb.dto.CategoryDTO;
import com.hocjavaweb.service.impl.CategoryService;
import com.hocjavaweb.utils.MessageUtils;

@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private MessageUtils messageUtils;
	
	@RequestMapping(value = "/quan-tri/the-loai/danh-sach", method = RequestMethod.GET)
	 public ModelAndView showList(@RequestParam(value = "message",required = false) String message,
			 						@RequestParam(value = "keyword",required = false) String keyword) {
	     ModelAndView mav = new ModelAndView("admin/category/list");
	     CategoryDTO dto = new CategoryDTO();
	     dto.setListResult(categoryService.findALlList());
	     mav.addObject("categoryModel",dto);
	     
	     if(message!= null) {
	    	 Map<String, String> map = messageUtils.getMessage(message);
	    	 mav.addObject("message",map.get("message"));
		  	 mav.addObject("alert",map.get("alert"));
	     }
	     
	     if(keyword != null) {
	    	// List<CategoryDTO> dtos = categoryService.search(keyword);
		    // mav.addObject("listSearch", dtos);
	    	 dto.setListResult(categoryService.search(keyword));
	    	 mav.addObject("categoryModel",dto);
	     }
	     return mav;
	 }
	
	@RequestMapping(value = "/quan-tri/the-loai/chinh-sua", method = RequestMethod.GET)
	 public ModelAndView editList(@RequestParam(value = "id",required = false) Long id,
			 						@RequestParam(value = "message",required = false) String message){
	     ModelAndView mav = new ModelAndView("admin/category/edit");
	     CategoryDTO dto = new CategoryDTO();
	     if(id != null) {
	    	 dto = categoryService.findOneById(id);
	     }
	     mav.addObject("model",dto);
	     
	     if(message!= null) {
	    	 Map<String,String> map = messageUtils.getMessage(message);
	    	 mav.addObject("message",map.get("message"));
		  	 mav.addObject("alert",map.get("alert"));
	     }
	     return mav;
	 }
	/* @RequestMapping(value = "/quan-tri/the-loai/search", method = RequestMethod.GET)
	 public ModelAndView editList(@RequestParam(value = "keyword",required = false) String keyword){
	     ModelAndView mav = new ModelAndView("admin/category/search");
	    // String keyword = "a";
	     List<CategoryDTO> dtos = categoryService.search(keyword);
	     mav.addObject("listSearch", dtos);
	     return mav;
	 }*/
	
}

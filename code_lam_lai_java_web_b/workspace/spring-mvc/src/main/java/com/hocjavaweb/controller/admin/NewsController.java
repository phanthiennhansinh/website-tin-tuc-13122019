package com.hocjavaweb.controller.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hocjavaweb.dto.NewsDTO;
import com.hocjavaweb.service.ICategoryService;
import com.hocjavaweb.service.INewsService;
import com.hocjavaweb.utils.MessageUtils;

@Controller(value = "admin-news")
public class NewsController {

	@Autowired
	private INewsService newsService;
	
	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private MessageUtils messageUtils;
	
	@RequestMapping(value = "/quan-tri/bai-viet/danh-sach", method = RequestMethod.GET)
	 public ModelAndView showList(@RequestParam("page") int page,
			 						@RequestParam("limit") int limit,
			 						@RequestParam(value = "message",required = false) String message,
			 						@RequestParam(value = "keyword",required = false) String keyword) {
		ModelAndView mav = new ModelAndView("admin/news/list");
		NewsDTO model = new NewsDTO();
		model.setPage(page);
		model.setLimit(limit);
	//	Pageable pageable = new PageRequest(page-1, limit);
		Pageable pageable2 = new PageRequest(page-1, limit, new Sort (Sort.Direction.ASC, "title") );
	    model.setListResult(newsService.findAll(pageable2));
	    model.setTotalItem(newsService.getTotalItem());
	    model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getLimit()));
	    mav.addObject("model",model);
	    
	    if(message != null) {
	    	 Map< String, String> map = messageUtils.getMessage(message);
	    	 mav.addObject("message",map.get("message"));
	    	 mav.addObject("alert",map.get("alert"));
	    }
	    
	    if(keyword != null) {
	    //	List<NewsDTO> list = newsService.search(keyword);
	    //	mav.addObject("listSearch",list);
	    	model.setListResult(newsService.search(keyword));
	    	mav.addObject("model", model);
	    }
	    
		return mav;
	 }
	
	@RequestMapping(value = "/quan-tri/bai-viet/chinh-sua", method = RequestMethod.GET)
	 public ModelAndView news_edit_Page(@RequestParam(value = "id",required = false) Long id,
			 							@RequestParam(value = "message",required = false) String message
			 							){
	     ModelAndView mav = new ModelAndView("admin/news/edit");
	     NewsDTO dto = new NewsDTO();
	     if(id != null) {
	    	 dto = newsService.findById(id);
	     }
	     mav.addObject("categories",categoryService.findAll());
	     mav.addObject("model", dto);
	     
	     if(message != null) {
	    	 Map< String, String> map = messageUtils.getMessage(message);
	    	 mav.addObject("message",map.get("message"));
	    	 mav.addObject("alert",map.get("alert"));
	     }
		 
	     return mav;
	 }
}

package com.hocjavaweb.api.admin;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hocjavaweb.dto.NewsDTO;
import com.hocjavaweb.service.INewsService;
import com.hocjavaweb.utils.UploadFileUtils;

@RestController(value = "newsAPIOfAdmin")
public class NewsAPI {

	@Autowired
	private INewsService newsService;
	
	@Autowired
	private UploadFileUtils uploadFileUtils;
	
	@PostMapping("/api/news")
	public NewsDTO createNews(@RequestBody NewsDTO dto) {
		try {
        	if(dto.getBase64()!="") {
        		String partSeparator = ",";
        		if(dto.getBase64().contains(partSeparator)) {
        			String encodedImg = dto.getBase64().split(partSeparator)[1];
        			byte[] decodeBase64 = Base64.getDecoder().decode(encodedImg.getBytes());
                    uploadFileUtils.writeOrUpdate(decodeBase64, "/thumbnail/"+dto.getName());
        		}
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
		return newsService.save(dto);
	}
	
	@PutMapping("/api/news")
	public NewsDTO updateNews(@RequestBody NewsDTO dto) {
		try {
        	if(dto.getBase64()!="") {
        		String partSeparator = ",";
        		if(dto.getBase64().contains(partSeparator)) {
        			String encodedImg = dto.getBase64().split(partSeparator)[1];
        			byte[] decodeBase64 = Base64.getDecoder().decode(encodedImg.getBytes());
                    uploadFileUtils.writeOrUpdate(decodeBase64, "/thumbnail/"+dto.getName());
        		}
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
		return newsService.save(dto);
	}
	
	@DeleteMapping("/api/news")
	public void deleteNews(@RequestBody long[] ids) {
		newsService.delete(ids);
	}
}

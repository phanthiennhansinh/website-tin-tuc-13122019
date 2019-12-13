package com.hocjavaweb.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class MessageUtils {
	public Map<String , String> getMessage(String message){
		Map<String, String> map = new HashMap<>();
		if(message.equals("insert_success")) {
   		 	map.put("message","insert success");
   		 	map.put("alert","success");
		}else if(message.equals("update_success")) {
			map.put("message","update succes");
			map.put("alert","success");
   	 	}else if(message.equals("delete_success")) {
			map.put("message","delete succes");
			map.put("alert","success");
   	 	}else if(message.equals("error_system")) {
   	 		map.put("message","error system");
   	 		map.put("alert","danger");
   	 	}
		return map;
	}
}

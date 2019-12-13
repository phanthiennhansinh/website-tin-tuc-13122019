package com.hocjavaweb.api.admin;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hocjavaweb.dto.UploadFileDTO;
import com.hocjavaweb.utils.UploadFileUtils;

@RestController
public class UpLoadFileAPI {
	
	@Autowired
	private UploadFileUtils uploadFileUtils;

    @PostMapping(value = "/api/upload")
    public ResponseEntity<Void> uploadFile(@RequestBody UploadFileDTO uploadFileDTO) {
        try {
        	System.out.println(uploadFileDTO.getBase64());
            byte[] decodeBase64 = Base64.getDecoder().decode(uploadFileDTO.getBase64().getBytes());
            uploadFileUtils.writeOrUpdate(decodeBase64, "/thumbnail/"+uploadFileDTO.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.noContent().build();
    }
	
}

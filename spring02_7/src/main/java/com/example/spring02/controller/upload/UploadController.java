package com.example.spring02.controller.upload;

import java.io.File;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UploadController {
	private static final Logger logger =
			LoggerFactory.getLogger(UploadController.class);
	
	@Resource(name="uploadPath")
	String uploadPath;
	
	@RequestMapping(value="/upload/uploadForm",
			method=RequestMethod.GET)
	public void uploadForm() {
		
	}
	
	@RequestMapping(value = "/upload/uploadForm",
			method=RequestMethod.POST)
	public ModelAndView uploadForm(
			MultipartFile file, ModelAndView mav) throws Exception {
		logger.info("파일이름:"+file.getOriginalFilename());
		String savedName = file.getOriginalFilename();
		logger.info("파일크기:"+file.getSize());
		logger.info("컨텐츠 타입:"+file.getContentType());
		savedName=uploadFile(savedName, file.getBytes());
		mav.setViewName("upload/uploadResult");
		mav.addObject("savedName",savedName);
		return mav;
	}
	
	private String uploadFile(String originalName, byte[]fileData)
		throws Exception {
		UUID uid = UUID.randomUUID();
		String savedName = uid.toString()+"_"+originalName;
		File target = new File(uploadPath, savedName);
		FileCopyUtils.copy(fileData, target);
		return savedName;
	}
}

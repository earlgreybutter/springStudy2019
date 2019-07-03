package com.example.spring01.controller;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring01.model.dto.ProductDTO;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;

// 컨트롤러 이노테이션 
@Controller
public class MainController {
	// 로그 객체 생성 
	private static final Logger logger=
			LoggerFactory.getLogger(MainController.class);
	// 시작페이지로 이동 
	@RequestMapping("/") //url pattern mapping
	public String main(Model model) {
		// Model : 데이터를 담는 그릇 역할, map구조로 저장됨
		// model.addAttribute("변수명",값)
		model.addAttribute("message", "홈페이지 방문을 환영합니다.");
		// <beans:property name="prefix" value="/WEB-INF/views/" />
		// <beans:property name="suffix" value=".jsp" />
		// /WEB-INF/views/main.jsp
		return "main"; //main.jsp로 포워딩됨. 
	}
	
	@RequestMapping(value="gugu.do", method=RequestMethod.GET)
	public String gugu(@RequestParam int dan, Model model) {
		
		//int dan=7;
		String result="";
		for(int i=1;i<=9;i++) {
			result += dan+"x"+i+"="+dan*i+"<br>";		
		}	
		model.addAttribute("result" , result);
		return "test/gugu";
	}
	
	@RequestMapping("test")
	public void test() {
		
	}
	
	@RequestMapping("test/doA")
	public String doA(Model model) {
		logger.info("doA called...");
		model.addAttribute("message","홈페이지 방문을 환영합니다. ");
		return "test/doB";
	}
	
	@RequestMapping("test/doB")
	public void doB() {
		logger.info("doB called...");
	}
	
	@RequestMapping("test/doC")
	public ModelAndView doC() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("product", new ProductDTO("샤프", 1000));
		return new ModelAndView("test/doC", "map", map);
	}
	
	@RequestMapping("test/doD")
	public String doD() {
		return "redirect:/test/doE";
	}
	
	@RequestMapping("test/doE")
	public void doE() {
		
	}
	
}
  
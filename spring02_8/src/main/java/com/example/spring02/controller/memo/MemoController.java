package com.example.spring02.controller.memo;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.memo.dto.MemoDTO;
import com.example.spring02.service.memo.MemoService;

@Controller
@RequestMapping("/memo/*")
public class MemoController {

	@Inject
	MemoService memoService;
	
	@RequestMapping("list.do")
	public ModelAndView list(ModelAndView mav) {
		List<MemoDTO> items=memoService.list();
		mav.setViewName("memo/memo_list");
		mav.addObject("list",items);
		return mav;
	}
	
	@RequestMapping("insert.do")
	public String insert(@ModelAttribute MemoDTO dto) {
		memoService.insert(dto.getWriter(), dto.getMemo());
		return "redirect:/memo/list.do";
	}
	@RequestMapping("view/{idx}")
	public ModelAndView view(@PathVariable int idx,
			ModelAndView mav) {
		mav.setViewName("memo/view");
		mav.addObject("dto", memoService.memo_view(idx));
		return mav;
	}
	@RequestMapping("update/{idx}")
	public String update(@PathVariable int idx, MemoDTO dto) {
		memoService.update(dto);
		return "redirect:/memo/list.do";
	}
	@RequestMapping("delete/{idx}")
	public String delete(@PathVariable int idx) {
		memoService.delete(idx);
		return "redirect:/memo/list.do";
	}
}

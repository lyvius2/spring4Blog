package com.yunson.firstapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@Controller
public class TestController {

	@Autowired
	private TestDao testDao;

	@RequestMapping(value = "/test")
	public String Test(Model model) throws SQLException {
		model.addAttribute("Test", testDao.getTest());
		return "test";
	}

	@RequestMapping(value = "/test2", method = RequestMethod.GET)
	public ModelAndView Test2() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("Test", "shit up");
		mv.setViewName("test");
		return mv;
	}

	@RequestMapping(value = "/members", method = RequestMethod.GET)
	public ModelAndView getMemberList() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("memberList", testDao.getList());
		mv.setViewName("memberList");
		return mv;
	}

	@RequestMapping(value = "/member/{AME_SEQ}", method = RequestMethod.GET)
	public ModelAndView getMember(@PathVariable int AME_SEQ) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("member", testDao.getMember(new MemberVO(AME_SEQ)));
		mv.setViewName("memberDetail");
		return mv;
	}
}

package com.yunson.firstapp.member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;

/**
 * Created by yhwang131 on 2016-08-22.
 */
@Controller
@RequestMapping("/member")
public class MemberController {

	public static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberDao memberDao;

	@Autowired
	private ShaPasswordEncoder encoder;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerMemberForm(Model model) {
		model.addAttribute("memberVO", new MemberVO());
		return "member/memberForm";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerMember(@ModelAttribute("memberVO") @Valid MemberVO memberVO, Errors errors, HttpServletRequest request) {
		if(errors.hasErrors()) {
			return "member/memberForm";
		}
		if(!memberVO.getPassword().equals(request.getParameter("passwordConfirm"))) {
			errors.rejectValue("password", null, "비밀번호 확인을 다시 한번 해주십시오.");
			return "member/memberForm";
		}
		HashMap<String, Object> param = new HashMap<>();
		param.put("username", memberVO.getUsername());
		if(memberDao.getMemberDetail(param) != null) {
			errors.rejectValue("username", null, "이미 동일한 ID의 사용자가 존재합니다.");
			return "member/memberForm";
		}
		memberVO.setPassword(encoder.encodePassword(memberVO.getPassword(), null));
		memberDao.setMember(memberVO);
		return "redirect:profile/" + memberVO.getSeq();
	}

	@RequestMapping(value = "/profile/{seq}")
	public String memberProfile(@PathVariable int seq, Model model) {
		HashMap<String, Object> param = new HashMap<>();
		param.put("seq", seq);
		model.addAttribute("member", memberDao.getMemberDetail(param));
		return "member/memberDetail";
	}

	@RequestMapping("/list")
	public String memberList(@RequestParam(value = "searchText", required = false)String searchText, Model model) {
		model.addAttribute("memberList", memberDao.getMemberList(searchText));
		return "member/memberList";
	}

}

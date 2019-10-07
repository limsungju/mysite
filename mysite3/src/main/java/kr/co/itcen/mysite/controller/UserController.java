package kr.co.itcen.mysite.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.itcen.mysite.security.Auth;
import kr.co.itcen.mysite.security.AuthUser;
import kr.co.itcen.mysite.service.UserService;
import kr.co.itcen.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(@ModelAttribute UserVo vo) {
		
		return "user/join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo vo,
			BindingResult result,
			Model model) { // @Valid : Vo에서 설정해준 제약 조건 값을 확인한다.
		
		if(result.hasErrors()) { // result에 Error가 있는지 없는지 확인
			model.addAllAttributes(result.getModel()); // result.getModel()이 Map을 리턴해주어, 키/값을 자동으로 셋팅해준다. 아래 4줄과 동일한 코드
//			Map<String, Object> map = result.getModel();
//			for(String key : map.keySet()) {
//				model.addAttribute(key, map.get(key));
//			}
			
//			List<ObjectError> list = result.getAllErrors();
//			for(ObjectError error : list) { // 에러내용을 찍어보기
//				System.out.println(error);
//			}
			
			return "user/join"; // Error가 있다면 join으로 리턴
		}
		
		userService.join(vo);
		return "/user/joinsuccess";
	}
	
	@RequestMapping(value="/joinsuccess", method=RequestMethod.GET)
	public String joinsuccess() {
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	@Auth("USER")
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String modify(@AuthUser UserVo authUser, Model model) {
		System.out.println("authUser:"+authUser);
		UserVo userVo = userService.getUser(authUser.getNo());
		
		if (userVo == null) {
			return "user/login";
		}
		
		model.addAttribute("userVo", userVo);
		
		return "user/update";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String modify(@ModelAttribute @Valid UserVo userVo, BindingResult result, @AuthUser UserVo authUser, Model model) {
		
		if(result.hasErrors()) { // result에 Error가 있는지 없는지 확인
			model.addAllAttributes(result.getModel());
			
			return "user/update";
		}
		//vo.setNo(vo.getNo());
		userService.update(userVo);
		
		authUser.setName(userVo.getName());
		authUser.setPassword(userVo.getPassword());
		
		return "redirect:/";
	}
	
//	// Exception이 나면 이곳에서 받는다.
//	@ExceptionHandler(UserDaoException.class)
//	public String handlerException() {
//		return "error/exception";
//	}
	
	
}

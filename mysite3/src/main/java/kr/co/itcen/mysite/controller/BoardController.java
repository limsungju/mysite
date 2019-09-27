package kr.co.itcen.mysite.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.itcen.mysite.search.Search;
import kr.co.itcen.mysite.service.BoardService;
import kr.co.itcen.mysite.service.UserService;
import kr.co.itcen.mysite.vo.BoardVo;
import kr.co.itcen.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private UserService userService;
	
//	@RequestMapping(value = {"", "/list"}, method = RequestMethod.POST)
//	public String getList(@RequestParam(value="kwd", required = false,defaultValue = "") String kwd) {
//		
//		return "redirect:/board?kwd="+kwd;
//	}
	
	@RequestMapping(value = {"", "/list"}, method = RequestMethod.GET)
	public String getList(Search search, Model model) {
		if(search.getPage() < 1) {
			search.setPage(1);
		}
		System.out.println(search.getKwd());
		System.out.println(search.getPage());
		if(search.getKwd() == null || search.getKwd().length() == 0) {
			search.setKwd("");
		}
		
		Map<String, Object> map = boardService.getList(search);
		
		model.addAttribute("list", map.get("list"));
		model.addAttribute("pagination", map.get("pagination"));
		
		return "board/list";
	}
	
	@RequestMapping(value = ("/write"), method = RequestMethod.GET)
	public String write(HttpSession session, Model model) {
		// 접근 제어(ACL)
		if (session == null) {
			return "redirect:/";
		}
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		UserVo userVo = userService.getUser(authUser.getNo());
		model.addAttribute("userVo", userVo);
		return "board/write";
	}
	
	@RequestMapping(value = ("/write{no}"), method = RequestMethod.POST)
	public String write(@ModelAttribute BoardVo vo, @PathVariable("no") Long no, HttpSession session, Model model) {
		// 접근 제어(ACL)
		if (session == null) {
			return "redirect:/";
		}
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		System.out.println("no : " +no);
		System.out.println("no2 : " +vo.getNo());
		UserVo userVo = userService.getUser(authUser.getNo());
		model.addAttribute("userVo", userVo);
		
		System.out.println("겟No" + vo.getNo());
		if(no == null) {
			no = vo.getNo();
		}
		
		BoardVo boardVo = new BoardVo();
		boardVo.setTitle(vo.getTitle());
		boardVo.setContents(vo.getContents());
		boardVo.setOrderNo(1);
		boardVo.setDepth(0);
		boardVo.setUserNo(authUser.getNo());
		
		if(no == null) {
			boardService.insert(boardVo);
		} else {
			boardVo.setNo(no);
			BoardVo selectBVo = boardService.select(no);
			
			Integer groupNo = selectBVo.getGroupNo();
			System.out.println(groupNo);
			Integer orderNo = selectBVo.getOrderNo()+1;
			System.out.println(orderNo);
			Integer depth = selectBVo.getDepth()+1;
			System.out.println(depth);
			
			boardVo.setGroupNo(groupNo);
			boardVo.setOrderNo(orderNo);
			boardVo.setDepth(depth);
			boardService.update(groupNo, orderNo);
			boardService.insertBoard(boardVo);
		}
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = ("/view/{no}"), method = RequestMethod.GET)
	public String view(@PathVariable("no") Long no, HttpSession session, Model model) {
		
		boardService.hitUpdate(no); // 조회수 증가
		
		BoardVo boardVo = boardService.getView(no);
		model.addAttribute("boardVo", boardVo);
		
		if (session == null) {
			return "board/view";
		}
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "board/view";
		}
		model.addAttribute("authUser", authUser);
		
		return "board/view";
	}
	
	@RequestMapping(value = ("/modify"), method = RequestMethod.GET)
	public String modify(@ModelAttribute BoardVo vo, Model model) {
		
		BoardVo boardVo = boardService.getView(vo.getNo());
		model.addAttribute("boardVo", boardVo);
		
		return "board/modify";
	}
	
	@RequestMapping(value = ("/modify"), method = RequestMethod.POST)
	public String modify(@ModelAttribute BoardVo boardVo, HttpSession session, Model model) {
		if (session == null) {
			return "redirect:/board/list";
		}
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/board/list";
		}
		
		if (authUser.getNo() != boardVo.getUserNo()) {
			return "redirect:/board/list";
		}
		
		boardService.boardUpdate(boardVo);
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = ("/delete/{no}"), method = RequestMethod.GET)
	public String delete(@PathVariable("no") Long no, HttpSession session, Model model ) {
		if (session == null) {
			return "redirect:/board/list";
		}
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/board/list";
		}
		
		BoardVo boardVo = boardService.getView(no);
		
		model.addAttribute("boardVo", boardVo);
		
		return "board/delete";
	}
	
	@RequestMapping(value = ("/delete"), method = RequestMethod.POST)
	public String delete(@ModelAttribute BoardVo vo, @RequestParam("text") String text, HttpSession session) {
		if (session == null) {
			return "redirect:/board/list";
		}
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/board/list";
		}
		
		if (authUser.getNo() != vo.getNo()) {
			return "redirect:/board/list";
		}
		
		if ("삭제하기".equals(text)) {
			boardService.delete(vo.getNo());
		}
		
		return "redirect:/board/list";
	}
	
	
	
}

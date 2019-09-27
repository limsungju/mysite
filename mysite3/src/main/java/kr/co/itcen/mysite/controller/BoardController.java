package kr.co.itcen.mysite.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.itcen.mysite.search.Search;
import kr.co.itcen.mysite.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
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
	
}

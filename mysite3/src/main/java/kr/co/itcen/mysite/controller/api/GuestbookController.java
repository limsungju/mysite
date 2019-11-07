package kr.co.itcen.mysite.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.itcen.mysite.dto.JSONResult;
import kr.co.itcen.mysite.service.GuestbookService;
import kr.co.itcen.mysite.vo.GuestbookVo;

//@RestController
@RestController("guestbookApiController")
@RequestMapping("/api/guestbook")
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@PostMapping(value = "/add")
	public JSONResult add(@RequestBody GuestbookVo guestbookVo) {
		guestbookService.insert(guestbookVo);
		guestbookVo.setPassword("");
		return JSONResult.success(guestbookVo);
	}
	
	@GetMapping(value = "/list/{no}")
	public JSONResult list(@PathVariable(value = "no") Long startNo) {
		List<GuestbookVo> list = guestbookService.getList(startNo);
		return JSONResult.success(list);
	}
	
	@DeleteMapping(value = "/{no}")
	public JSONResult delete(@PathVariable(value = "no") Long no, @RequestParam String password) {
		boolean result = guestbookService.delete(no, password);
		return JSONResult.success(result);
	}
}

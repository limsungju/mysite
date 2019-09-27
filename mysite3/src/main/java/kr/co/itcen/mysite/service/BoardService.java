package kr.co.itcen.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.mysite.repository.BoardDao;
import kr.co.itcen.mysite.search.Search;
import kr.co.itcen.mysite.vo.BoardVo;
import kr.co.itcen.web.PaginationUtil;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;

	public Map<String, Object> getList(Search search) {
		
		int totalCnt = boardDao.totalCnt(search.getKwd()); // 전체 게시글 수 구하기
		
		PaginationUtil pagination = new PaginationUtil(search.getPage(), totalCnt, 5, 5);
		
		search.setPagination(pagination);
		search.setStrNo((pagination.getCurrentPage() - 1) * pagination.getListSize());
		search.setEndNo(pagination.getListSize());
		List<BoardVo> list = boardDao.getList(search);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("pagination", pagination);
		
		
		return map;
		
	}
	
	
}

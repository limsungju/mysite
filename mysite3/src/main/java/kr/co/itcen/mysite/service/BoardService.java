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
	
	// 게시글 정보 출력
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
	
	// 게시글 추가
	public void insert(BoardVo boardVo) {
		boardDao.insert(boardVo);
	}
	
	// 게시글 g_no, o_no, depth 출력
	public BoardVo select(Long no) {
		BoardVo result = boardDao.select(no);
		return result;
	}
	
	// 게시글 답글 달기
	public void update(Integer groupNo, Integer orderNo) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("groupNo", groupNo);
		map.put("orderNo", orderNo);
		boardDao.update(map);
		
	}
	
	// 게시글 답글 달기
	public void insertBoard(BoardVo boardVo) {
		boardDao.insertBoard(boardVo);
	}

	public void hitUpdate(Long no) {
		boardDao.hitUpdate(no);
	}

	public BoardVo getView(Long no) {
		BoardVo result = boardDao.getView(no);
		return result;
	}

	public void boardUpdate(BoardVo boardVo) {
		boardDao.boardUpdate(boardVo);
	}

	public void delete(Long no) {
		boardDao.boardDelete(no);
	}
	
	
}

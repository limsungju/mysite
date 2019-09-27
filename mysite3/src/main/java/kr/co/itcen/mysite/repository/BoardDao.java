package kr.co.itcen.mysite.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.mysite.search.Search;
import kr.co.itcen.mysite.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	// 게시글 수 구하기
	public int totalCnt(String kwd) {
		int count = sqlSession.selectOne("board.getListCount", kwd);
		return count;
	}

	// 게시판 정보 가져오기
	public List<BoardVo> getList(Search search) {
		List<BoardVo> list = sqlSession.selectList("board.getList", search);
		return list;
	}

	public Boolean insert(BoardVo boardVo) {
		int count = sqlSession.insert("board.insert", boardVo);
		return count == 1;
	}

	public BoardVo select(Long no) {
		BoardVo boardVo = sqlSession.selectOne("board.select", no);
		return boardVo;
	}

	public Boolean update(Map<String, Integer> map) {
		int count = sqlSession.update("board.update", map);
		return count == 1;
	}

	public Boolean insertBoard(BoardVo boardVo) {
		int count = sqlSession.insert("board.insertBoard", boardVo);
		return count == 1;
	}

	public Boolean hitUpdate(Long no) {
		int count = sqlSession.update("board.hitUpdate", no);
		return count == 1;
	}

	public BoardVo getView(Long no) {
		BoardVo result = sqlSession.selectOne("board.getView", no);
		return result;
	}

	public Boolean boardUpdate(BoardVo boardVo) {
		int count = sqlSession.update("board.boardUpdate", boardVo);
		return count == 1;
	}

	public Boolean boardDelete(Long no) {
		int count = sqlSession.update("board.delete", no);
		return count == 1;
	}

	





	



	

}

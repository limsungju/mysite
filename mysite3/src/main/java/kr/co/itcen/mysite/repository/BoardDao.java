package kr.co.itcen.mysite.repository;

import java.util.List;

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

	





	



	

}

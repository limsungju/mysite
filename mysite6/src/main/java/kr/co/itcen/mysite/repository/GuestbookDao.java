package kr.co.itcen.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.mysite.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	
	@Autowired
	private SqlSession sqlSession; // applicationContext.xml에서 설정 후 사용
	
	public Boolean insert(GuestbookVo guestVo) {
		int count = sqlSession.insert("guestbook.insert", guestVo);
		return count == 1;
	}
	
	public void delete(GuestbookVo guestVo) {
		sqlSession.delete("guestbook.delete", guestVo);
	}

	public List<GuestbookVo> getList() {
		List<GuestbookVo> result = sqlSession.selectList("guestbook.getList");
		
		return result;
	}
	
	
}

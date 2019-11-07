package kr.co.itcen.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.mysite.repository.GuestbookDao;
import kr.co.itcen.mysite.vo.GuestbookVo;

@Service
public class GuestbookService {

	@Autowired
	private GuestbookDao guestBookDao;
	
	public List<GuestbookVo> getList() {
		List<GuestbookVo> gbList = guestBookDao.getList();
		return gbList;
	}
	
	public List<GuestbookVo> getList(Long startNo) {
		List<GuestbookVo> gbList = guestBookDao.getList(startNo);
		return gbList;
	}

	public void delete(GuestbookVo vo) {
		guestBookDao.delete(vo);
	}
	
	public Boolean delete(Long no, String password) {
		return guestBookDao.delete(no, password) == 1;
		
	}

	public void insert(GuestbookVo vo) {
		guestBookDao.insert(vo);
		
	}

}

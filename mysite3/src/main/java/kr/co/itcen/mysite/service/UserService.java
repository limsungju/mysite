package kr.co.itcen.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.mysite.repository.UserDao;
import kr.co.itcen.mysite.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	// 회원가입
	public void join(UserVo vo) {
		userDao.insert(vo);
	}

	// 접속한 사용자 email, password 가져오기
	public UserVo getUser(UserVo vo) {
		return userDao.get(vo);
	}

	// 사용자 정보 얻어오기
	public UserVo getUser(Long no) {
		return userDao.get(no);
	}

	// 회원정보 수정
	public void update(UserVo vo) {
		userDao.update(vo);
	}

	public Boolean existUser(String email) {
		return userDao.get(email) != null;
	}

}

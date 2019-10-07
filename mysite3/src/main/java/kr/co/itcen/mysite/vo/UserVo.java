package kr.co.itcen.mysite.vo;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class UserVo {
	private Long no;

	@NotEmpty // 값을 꼭 넣어주어야 하는 것
	@Length(min = 2, max = 8) // 최소 2자부터 최대 8자 입력가능
	private String name;

	@NotEmpty
	@Email // 이메일 형식인지 확인
	private String email;
	private String password;
	private String gender;
	private String joinDate;
	private String role;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	// form:radiobuttons를 사용하기 위해 만듬
	public String[] getGenders() {
		return new String[] { "male", "female" };
	}

	@Override
	public String toString() {
		return "UserVo [no=" + no + ", name=" + name + ", email=" + email + ", password=" + password + ", gender="
				+ gender + ", joinDate=" + joinDate + ", role=" + role + "]";
	}

}

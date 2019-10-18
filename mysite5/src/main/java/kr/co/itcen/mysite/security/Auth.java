package kr.co.itcen.mysite.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD}) // 어디에 이 annotation을 붙힐 수 있는가, ElementType.TYPE : class에 붙힐 수 있게 해준다.
@Retention(RetentionPolicy.RUNTIME) // runtime 때 하는지 source 때 하는지
public @interface Auth { // annotation을 만들때는 @를 붙여준다.
	public enum Role{USER, ADMIN};
	
	// public Role role() default Role.USER;
	public String value() default "USER"; // @Auth() ()안에 입력이 없을 시 USER를 기본으로 적용
	
	
	// public int test() default 1;
}

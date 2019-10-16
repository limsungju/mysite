package kr.co.itcen.mysite.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import kr.co.itcen.config.app.DBConfig;
import kr.co.itcen.config.app.MyBatisConfig;

@Configuration
@EnableAspectJAutoProxy // <aop:aspectj-autoproxy /> 와 같음
@ComponentScan({"kr.co.itcen.mysite.service", "kr.co.itcen.mysite.repository", "kr.co.itcen.mysite.aspect"}) // <context:annotation-config />, <context:component-scan> 와 같음
@Import({DBConfig.class, MyBatisConfig.class})
public class AppConfig {
	
}

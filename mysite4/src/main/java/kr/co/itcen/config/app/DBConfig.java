package kr.co.itcen.config.app;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement // aop로 트랜잭션 시작, 끝을 서비스에 걸어줄 수 있다. (@Transactional), 2가지 이상의 작업을 하는 중 도중에 에러가 나면 rollback해준다.
@PropertySource("classpath:kr/co/itcen/config/app/properties/jdbc.properties")
public class DBConfig {
	
	@Autowired
	Environment env;
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		basicDataSource.setUrl(env.getProperty("jdbc.url"));
		basicDataSource.setUsername(env.getProperty("jdbc.username"));
		basicDataSource.setPassword(env.getProperty("jdbc.password"));
		basicDataSource.setInitialSize(env.getProperty("jdbc.initialSize", Integer.class)); // 처음 connection pool 개수
		basicDataSource.setMaxActive(env.getProperty("jdbc.maxActive", Integer.class)); // connection pool 최대 개수
		
		return basicDataSource;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
	
}

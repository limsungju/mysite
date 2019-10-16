package kr.co.itcen.config.app;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement // aop로 트랜잭션 시작, 끝을 서비스에 걸어줄 수 있다. (@Transactional), 2가지 이상의 작업을 하는 중 도중에 에러가 나면 rollback해준다.
public class DBConfig {
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName("org.mariadb.jdbc.Driver");
		basicDataSource.setUrl("jdbc:mariadb://192.168.1.84:3306/webdb?characterEncoding=utf8");
		basicDataSource.setUsername("webdb");
		basicDataSource.setPassword("webdb");
		basicDataSource.setInitialSize(10); // 처음 connection pool 개수
		basicDataSource.setMaxActive(100); // connection pool 최대 개수
		
		return basicDataSource;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
	
}

package kr.co.itcen.config.web;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

/*
 * 1. ViewResolver
 * 2. DefaultServletHandler
 * 3. MessageConverter
 */
@Configuration
@EnableWebMvc // MVC와 관련된 기본 설정을 해준다. ( 따로 설정하면 기본 설정을 하지 않음 )
public class MVCConfig extends WebMvcConfigurerAdapter{
	
	// ViewResolver 설정 : Controller에서 url Mapping 시켜줄때 앞,뒤에 자동으로 코드를 넣어준다.
	@Bean
	public ViewResolver viewResolver() { // ViewResolver인터페이스의 InternalResourceViewResolver를 객체를 만든다.
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		//viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setExposeContextBeansAsAttributes(true);
		viewResolver.setOrder(1); // 찾는순서가 1번이다.
		
		return viewResolver;
	}
	
	// DefaultServletHandler / <mvc:default-servlet-handler />
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	// MessageConverter
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		Jackson2ObjectMapperBuilder builder = 
				new Jackson2ObjectMapperBuilder()
				.indentOutput(true)
				.dateFormat(new SimpleDateFormat("yyyy-mm-dd"))
				.modulesToInstall(new ParameterNamesModule()); // pom.xml에 Jackson Module Parameter Names Library 추가
		
		MappingJackson2HttpMessageConverter converter = 
				new MappingJackson2HttpMessageConverter(builder.build());
		
		converter.setSupportedMediaTypes(
				Arrays.asList(
					new MediaType("application", "json", Charset.forName("UTF-8"))
				)
			); // Arrays.asList : 값이 배열로 되어있는 경우 사용
		
		return converter;
	}
	
	// MessageConverter
	@Bean
	public StringHttpMessageConverter stringHttpMessageConverter() {
		StringHttpMessageConverter converter = new StringHttpMessageConverter();
		converter.setSupportedMediaTypes(
				Arrays.asList(
					new MediaType("application", "json", Charset.forName("UTF-8"))
				)
			);
		
		return converter;
	}
	
	// MessageConverter
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(mappingJackson2HttpMessageConverter());
		converters.add(stringHttpMessageConverter());
	}
	
	
	
	
	
	
}

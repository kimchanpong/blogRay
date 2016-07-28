package ray.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import ray.interceptor.LoginCheckInterceptorImpl;

/**
 * Created by ChanPong on 2016-05-17.
 */
@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@EnableScheduling
@ComponentScan(basePackages = {"ray.controller", "ray.service", "ray.repository", "ray.configuration", "ray.util.crypt"})
public class WebAdapter extends WebMvcConfigurerAdapter {
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/view/");
		return resolver;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry
				.addInterceptor(new LoginCheckInterceptorImpl())
				.addPathPatterns("/**")
						// 제외할 목록
				.excludePathPatterns("/login")
				.excludePathPatterns("/logout")
				.excludePathPatterns("/index");
		super.addInterceptors(registry);
	}
}
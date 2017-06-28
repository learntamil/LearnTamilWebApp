package com.learntamil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.accept.ContentNegotiationManagerFactoryBean;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.WebJarsResourceResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@SpringBootApplication
public class LearnTamilApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(LearnTamilApplication.class, args);
	}

	@Bean
	public WebMvcConfigurerAdapter adapter()
	{
		return new WebMvcConfigurerAdapter()
		{
			@Override
			public void addResourceHandlers(ResourceHandlerRegistry registry)
			{
				registry.addResourceHandler("/resources/static/js/**").addResourceLocations("/resources/static/js/");
				registry.addResourceHandler("/resources/static/css/**").addResourceLocations("/resources/static/css/");
				registry.addResourceHandler("/resources/static/views/**")
						.addResourceLocations("/resources/static/views/");
				registry.addResourceHandler("/resources/static/**").addResourceLocations("/resources/static/");
				registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/").resourceChain(true)
						.addResolver(new WebJarsResourceResolver());
			}

			@Override
			public void addViewControllers(ViewControllerRegistry registry)
			{
				super.addViewControllers(registry);
				registry.addViewController("/").setViewName("homepage");
			}

			@Bean
			public ViewResolver getHtmlViewResolver()
			{
				InternalResourceViewResolver resolver = new InternalResourceViewResolver();
				resolver.setPrefix("/WEB-INF/jsp/");
				resolver.setSuffix(".html");
				resolver.setOrder(2);
				return resolver;
			}
			
			@Override
		    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		        configurer.enable();
		    }
			
			@Bean
		    public ViewResolver getViewResolver(){
		    	ContentNegotiatingViewResolver contentNegotiatingViewResolver = new ContentNegotiatingViewResolver();
				contentNegotiatingViewResolver.setContentNegotiationManager(configureContentNegotiationManager());
		        contentNegotiatingViewResolver.setDefaultViews(Arrays.asList(new MappingJackson2JsonView()));
		        
		        contentNegotiatingViewResolver.setOrder(1);
		        return contentNegotiatingViewResolver;
		    }
			
			private ContentNegotiationManager configureContentNegotiationManager()
			{
				ContentNegotiationManagerFactoryBean contentNegotiationManagerFactoryBean = new ContentNegotiationManagerFactoryBean();
		    	contentNegotiationManagerFactoryBean.setIgnoreAcceptHeader(true);
		    	contentNegotiationManagerFactoryBean.setDefaultContentType(MediaType.TEXT_HTML);
		    	contentNegotiationManagerFactoryBean.addMediaType("json", MediaType.APPLICATION_JSON);
				return contentNegotiationManagerFactoryBean.getObject();
			}
		};
	}
}

// package com.exist.ecc.app.config;
//
// import org.springframework.context.MessageSource;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.ComponentScan;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.support.ReloadableResourceBundleMessageSource;
// import org.springframework.web.servlet.ViewResolver;
// import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
// import org.springframework.web.servlet.config.annotation.EnableWebMvc;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
// import org.springframework.web.servlet.view.InternalResourceViewResolver;
// import org.springframework.web.servlet.view.JstlView;
//
// @EnableWebMvc
// @ComponentScan(basePackages = "com.exist.ecc")
// public class SpringMvcConfig extends WebMvcConfigurerAdapter{
//
//     @Bean
//     public ViewResolver viewResolver() {
//         InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//         viewResolver.setViewClass(JstlView.class);
//         viewResolver.setPrefix("/WEB-INF/pages/");
//         viewResolver.setSuffix(".jsp");
//         return viewResolver;
//     }
//
// 	@Bean
//     public MessageSource messageSource() {
//         ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//         messageSource.setBasename("messages");
//         messageSource.setDefaultEncoding("UTF-8");
//         return messageSource;
//     }
//
//     @Override
//     public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//         configurer.enable();
//     }
//
// }

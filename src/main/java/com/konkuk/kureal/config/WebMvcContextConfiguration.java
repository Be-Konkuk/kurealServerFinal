//package com.konkuk.kureal.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
//
//@Configuration
//@EnableWebMvc
//@ComponentScan(basePackages = {"com.konkuk.kureal.controller"})
//public class WebMvcContextConfiguration extends WebMvcConfigurerAdapter{
//
//   private static final int CACHE_PERIOD_ONE_DAY_TO_SECOND = 86400;
//
//   @Override
//   public void addResourceHandlers(ResourceHandlerRegistry registry) {
//       registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/META-INF/resources/webjars/")
//               .setCachePeriod(CACHE_PERIOD_ONE_DAY_TO_SECOND);
//       registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(CACHE_PERIOD_ONE_DAY_TO_SECOND);
//       registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(CACHE_PERIOD_ONE_DAY_TO_SECOND);
//       registry.addResourceHandler("/img_map/**").addResourceLocations("/img_map/").setCachePeriod(CACHE_PERIOD_ONE_DAY_TO_SECOND);
//       registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(CACHE_PERIOD_ONE_DAY_TO_SECOND);
//   }
//
////    // default servlet handler를 사용하게 합니다.
////    @Override
////    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configure) {
////        configure.enable();
////    }
//
//   @Override
//   public void addViewControllers(final ViewControllerRegistry registry) {
//       // html 파일을 추가하게 된다면 여기서 주소를 엮을 수 있다 ex)registry.addViewController("/").setViewName("main");
//       registry.addViewController("/").setViewName("main");
//       registry.addViewController("/game").setViewName("game");
//       registry.addViewController("/room").setViewName("room");
//   }
//
//   @Bean
//   public InternalResourceViewResolver getInternalResourceViewResolver() {
//       InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//       resolver.setPrefix("/WEB-INF/views/");
//       resolver.setSuffix(".html");
//       return resolver;
//   }
//}

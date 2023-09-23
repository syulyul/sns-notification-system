package bitcamp.myapp;

import bitcamp.myapp.interceptor.NotReadNotiCountIntercepter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

@EnableTransactionManagement
@SpringBootApplication
public class App implements WebMvcConfigurer {

//  @Bean
//  public MultipartResolver multipartResolver() {
//    return new StandardServletMultipartResolver();
//  }

//  @Bean
//  public ViewResolver viewResolver() {
//    InternalResourceViewResolver vr = new InternalResourceViewResolver();
//    vr.setViewClass(JstlView.class);
//    vr.setPrefix("/WEB-INF/jsp/");
//    vr.setSuffix(".jsp");
//    return vr;
//  }

  public static void main(String[] args) throws Exception {
    SpringApplication.run(App.class, args);
  }

  @Override
  public void configurePathMatch(PathMatchConfigurer configurer) {
    System.out.println("AppConfig.configurePathMatch() 호출됨");
    UrlPathHelper pathHelper = new UrlPathHelper();

    // @MatrixVariable 기능 활성화
    pathHelper.setRemoveSemicolonContent(false);

    // DispatcherServlet의 MVC Path 관련 설정을 변경한다.
    configurer.setUrlPathHelper(pathHelper);
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    System.out.println("AppConfig.addInterceptors() 호출됨");
    registry
        .addInterceptor(notReadNotiCountIntercepter())
        .addPathPatterns("/**")
        .excludePathPatterns("/auth/**")
        .excludePathPatterns("/");
    
  }

  @Bean
  NotReadNotiCountIntercepter notReadNotiCountIntercepter() {
    return new NotReadNotiCountIntercepter();
  }
}

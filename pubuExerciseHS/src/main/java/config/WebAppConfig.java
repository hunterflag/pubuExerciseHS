package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan // xxx 全部 packageage
/*
// Java 1.8 後 Deprecated 
//@SuppressWarnings("deprecation")  
//public class WebAppConfig extends WebMvcConfigurerAdapter {
*/
public class WebAppConfig implements WebMvcConfigurer {

}

package cn.bptop.device.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter
{
    @Override
    public void addViewControllers(ViewControllerRegistry registry)
    {
//        registry.addViewController("/").setViewName("login");
////        registry.addViewController("/login").setViewName("login");
//        registry.addViewController("/login.html").setViewName("login");
//        registry.addViewController("/home.html").setViewName("index");
    }
//    @Override
//    public void addInterceptors(InterceptorRegistry registry)
//    {
//        registry .addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/","/user/login","/login.html");
//
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler("/file/**").addResourceLocations("file:C:/file/");
    }




}

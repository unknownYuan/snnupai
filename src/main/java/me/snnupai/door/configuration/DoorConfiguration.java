package me.snnupai.door.configuration;


import me.snnupai.door.interceptor.LoginRequiredInterceptor;
import me.snnupai.door.interceptor.PassportInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@Component
public class DoorConfiguration extends WebMvcConfigurerAdapter {
    @Autowired
    PassportInterceptor passportInterceptor;

    @Autowired
    LoginRequiredInterceptor loginRequiredInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(passportInterceptor);
        registry.addInterceptor(loginRequiredInterceptor)
                .addPathPatterns("/edit")
                .addPathPatterns("/msg/list");
//                .addPathPatterns("/snnupai_profile");
        super.addInterceptors(registry);
    }
}

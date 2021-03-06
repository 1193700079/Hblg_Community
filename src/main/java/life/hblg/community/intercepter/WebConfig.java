package life.hblg.community.intercepter;

//拦截器

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
//@EnableWebMvc    由于这是开启了spring的自定义配置 扫描包的时候 会和原来不同
public class WebConfig implements WebMvcConfigurer {


    @Autowired
    SessionInterceptor sessionInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LocaleChangeInterceptor ());
//        拦截所有controller     addPathPatterns 里面包含了拦截的地址 后者表示忽略的拦截地址
        registry.addInterceptor(sessionInterceptor).addPathPatterns("/**").excludePathPatterns("/admin/**");

    }
}


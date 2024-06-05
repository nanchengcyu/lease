package cn.nanchengyu.lease.web.admin.custom.config;

import cn.nanchengyu.lease.web.admin.custom.converter.StringToBaseConverterFactory;
import cn.nanchengyu.lease.web.admin.custom.converter.StringToItemTypeConverter;
import cn.nanchengyu.lease.web.admin.custom.interceptor.AuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ClassName: WebMvcConfigurattion
 * Package: cn.nanchengyu.lease.web.admin.custom.config
 * Description:
 * 自定义StringToItemTypeConverter注册
 * @Author 南城余
 * @Create 2024/5/31 16:41
 * @Version 1.0
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
//    @Autowired
//    private StringToItemTypeConverter stringToItemTypeConverter;
    @Autowired
    private StringToBaseConverterFactory stringToBaseConverterFactory;
    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;
    @Override
    public void addFormatters(FormatterRegistry registry) {
//        registry.addConverter(this.stringToItemTypeConverter);
        registry.addConverterFactory(this.stringToBaseConverterFactory);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(this.authenticationInterceptor).addPathPatterns("/admin/**").excludePathPatterns("/admin/login/**");
    }
}

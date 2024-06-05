package cn.nanchengyu.lease.web.app.custom.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("cn.nanchengyu.lease.web.app.mapper")
public class MybatisPlusConfiguration {

}
package cn.nanchengyu.lease.web.admin.custom.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("cn.nanchengyu.lease.web.admin.mapper")
public class MybatisPlusConfiguration {

}
package com.runonground.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = {"com.runonground.community.model.dto", "com.runonground.match.model.dto"})
public class DBConfig {

}

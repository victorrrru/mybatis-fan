package cn.interheart.rain.common.datasourceConfig;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author Administrator
 * @date 2018/01/20 10:21
 */
@Configuration
@MapperScan("cn.interheart.rain.domain")
public class FirstConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        //spring支持的连接池HikariPool
        //MysqlXADataSource是mysql数据源
        //我们用的是DruidXADataSource连接池
        return DataSourceBuilder.create().build();
    }
}

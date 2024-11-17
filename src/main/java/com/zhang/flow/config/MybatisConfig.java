package com.zhang.flow.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * mybatis配置
 * @author linzhiqinag
 */

@Configuration
public class MybatisConfig {
    private Logger logger = LoggerFactory.getLogger(MybatisConfig.class);




    @Value("${spring.datasource.druid.username}")
    private String username;

    @Value("${spring.datasource.druid.password}")
    private String password;

    @Value("${spring.datasource.druid.url}")
    private String dbUrl;


    @Bean
    public DruidDataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        try {
            druidDataSource.setUsername(username);
            druidDataSource.setPassword(password);
            druidDataSource.setUrl(dbUrl);
            druidDataSource.setFilters("stat,wall");
            druidDataSource.setUseGlobalDataSourceStat(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return druidDataSource;
    }

    @Bean
    public MybatisSqlSessionFactoryBean mysqlSessionFactory(DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setLogImpl(StdOutImpl.class);
        configuration.setCacheEnabled(true);
        sqlSessionFactoryBean.setConfiguration(configuration);
        sqlSessionFactoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources1 = resolver.getResources("classpath*:mapper/*.xml");
        Resource[] resources = new Resource[resources1.length];
        for (int i = 0; i < resources1.length; i++) {
            resources[i] = resources1[i];
        }
        sqlSessionFactoryBean.setMapperLocations(resources1);
        return sqlSessionFactoryBean;
    }
}
package com.move.mock.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;

@Configuration
public class DataSourceConfig {

    @Autowired
    DataSource dataSource;

    @Bean(name = "dataSource")
    @Qualifier(value = "dataSource")//限定描述符除了能根据名字进行注入，但能进行更细粒度的控制如何选择候选者
    @Primary//用@Primary区分主数据源
    @ConfigurationProperties(prefix = "c3p0")//指定配置文件中，前缀为c3p0的属性值c
    public DataSource dataSource() throws PropertyVetoException {

        ComboPooledDataSource dataSource = DataSourceBuilder.create().type(ComboPooledDataSource.class).build();
        dataSource.setJdbcUrl("jdbc:mysql://192.168.5.105:13306/appDev?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        dataSource.setUser("root");
        dataSource.setPassword("admin");
        dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        return dataSource; // 创建数据源

    }

    /**
     * 返回 sqlSessionFactory
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws IOException {

        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactory.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
        sqlSessionFactory.setTypeAliasesPackage("com.move.mock.bean");
        return sqlSessionFactory;

    }

}
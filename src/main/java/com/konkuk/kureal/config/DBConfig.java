package com.konkuk.kureal.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class DBConfig implements TransactionManagementConfigurer{
    //@Value("${mysql.driver}")
    @Value("com.mysql.jdbc.Driver")
    private String driverClassName;

    //@Value("${mysql.url}")
    @Value("jdbc:mysql://localhost:3306/kureal")
    private String databaseUrl;

    //@Value("${mysql.username}")
    @Value("root")
    private String databaseUserName;

    @Value("${mysql.password}")

    private String databasePassword;

    @Bean
    public DataSource dataSource() {
        System.out.println("HEY!! "+driverClassName+","+databaseUrl+","+databaseUserName+","+databasePassword);
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(databaseUrl);
        dataSource.setUsername(databaseUserName);
        dataSource.setPassword(databasePassword);

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return transactionManager();
    }
}

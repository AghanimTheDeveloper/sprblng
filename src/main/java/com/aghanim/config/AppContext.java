package com.aghanim.config;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

import java.util.Properties;

import static org.hibernate.cfg.Environment.*;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:hibernate.properties")
@ComponentScan("com.aghanim")
public class AppContext {

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource source = new DriverManagerDataSource();

        source.setDriverClassName(env.getRequiredProperty("hibernate.connection.driver_class"));
        source.setUrl(env.getRequiredProperty("hibernate.connection.url"));
        source.setUsername(env.getRequiredProperty("hibernate.connection.username"));
        source.setPassword(env.getRequiredProperty("hibernate.connection.password"));

        return source;
    }

    private Properties getProperties(){
        Properties props = new Properties();

        props.put(DIALECT, env.getRequiredProperty("hibernate.dialect"));
        props.put(SHOW_SQL, env.getRequiredProperty("hibernate.show_sql"));
        props.put(HBM2DDL_AUTO, env.getRequiredProperty("hibernate.hbm2ddl.auto"));

        return props;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

        factoryBean.setDataSource(dataSource());
        factoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        factoryBean.setPackagesToScan(env.getRequiredProperty("hibernate.packages_to_scan"));
        factoryBean.setJpaProperties(getProperties());

        return factoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager(){
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(entityManagerFactory().getObject());
        return manager;
    }
}

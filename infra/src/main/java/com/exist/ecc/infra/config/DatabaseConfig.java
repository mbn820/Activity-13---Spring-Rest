package com.exist.ecc.infra.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

@Configuration
public class DatabaseConfig {

	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://localhost:5433/testpersondatabase");
		dataSource.setUsername("postgres");
		dataSource.setPassword("ex1stgl0bal");
		dataSource.setInitialSize(10);
		dataSource.setMaxActive(5);
		dataSource.setMaxWait(5000);
		return dataSource();
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource( dataSource() );
		sessionFactoryBean.setHibernateProperties( hibernateProperties() );
		sessionFactoryBean.setPackagesToScan(new String[] {"com.exist.ecc.core.model"});
		return sessionFactoryBean;
	}

	public Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		properties.put("hibernate.show_sql", "false");
		properties.put("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
		properties.put("hibernate.cache.use_second_level_cache", "true");
		properties.put("hibernate.cache.use_query_cache", "true");
		properties.put("net.sf.ehcache.configurationResourceName", "ehcache.xml");
		return properties;
	}

}

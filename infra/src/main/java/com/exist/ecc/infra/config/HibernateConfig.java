package com.exist.ecc.infra.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

@Configuration
@PropertySource("classpath:database.properties")
public class HibernateConfig {
	private Environment environment;

	@Autowired
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName( environment.getProperty("jdbc.driver") );
		dataSource.setUrl( environment.getProperty("jdbc.url") );
		dataSource.setUsername( environment.getProperty("jdbc.username") );
		dataSource.setPassword( environment.getProperty("jdbc.password") );
		dataSource.setInitialSize( environment.getProperty("connection.initial_size", Integer.class) );
		dataSource.setMaxActive( environment.getProperty("connection.max_active", Integer.class) );
		dataSource.setMaxWait( environment.getProperty("connection.max_wait", Integer.class) );
		return dataSource;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource( dataSource() );
		sessionFactoryBean.setHibernateProperties( hibernateProperties() );
		sessionFactoryBean.setPackagesToScan(new String[] { "com.exist.ecc.core.model" });
		return sessionFactoryBean;
	}

	public Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put( "hibernate.dialect", environment.getProperty("hibernate.dialect") );
		properties.put( "hibernate.show_sql", environment.getProperty("hibernate.show_sql") );
		properties.put( "hibernate.cache.region.factory_class", environment.getProperty("hibernate.cache.region.factory_class") );
		properties.put( "hibernate.cache.use_second_level_cache", environment.getProperty("hibernate.cache.use_second_level_cache") );
		properties.put( "hibernate.cache.use_query_cache", environment.getProperty("hibernate.cache.use_query_cache") );
		properties.put( "net.sf.ehcache.configurationResourceName", environment.getProperty("net.sf.ehcache.configurationResourceName") );
		return properties;
	}

}

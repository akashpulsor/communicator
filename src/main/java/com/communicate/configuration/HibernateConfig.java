package com.communicate.configuration;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {

	private static final Logger logger = Logger.getLogger(HibernateConfig.class);
	private @Value("${jdbc.driverClassName}") 
	String jdbcDriver;

	private @Value("${jdbc.url}") 
	String jdbcUrl;

	private @Value("${jdbc.username}") 
	String userName;

	private @Value("${jdbc.password}") 
	String password;

	private @Value("${hibernate.hbm2ddl.auto}")
	String hbm2ddl;

	private @Value("${hibernate.dialect}")
	String hbmDialect;

	@Bean
	public ComboPooledDataSource getDataSource() throws PropertyVetoException
	{
		ComboPooledDataSource ds = new ComboPooledDataSource(); 
		ds.setDriverClass(jdbcDriver);
		ds.setJdbcUrl(jdbcUrl);
		ds.setUser(userName);
		ds.setPassword(password);
		return ds;
	}


	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws PropertyVetoException {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(getDataSource());
		em.setPackagesToScan(new String[] { "com.communicate.model" });

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());

		return em;
	}

	Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", hbm2ddl);
		properties.setProperty("hibernate.dialect", hbmDialect);
		return properties;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
		return new PersistenceExceptionTranslationPostProcessor();
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);

		return transactionManager;
	}
}

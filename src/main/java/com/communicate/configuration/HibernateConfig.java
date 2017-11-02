package com.communicate.configuration;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import com.mchange.v2.c3p0.ComboPooledDataSource;


@Component
public class HibernateConfig {

	private static final Logger logger = Logger.getLogger(HibernateConfig.class);
	private @Value("${spring.datasource.driver-class-name}") 
	String jdbcDriver;

	private @Value("${spring.datasource.url}") 
	String jdbcUrl;

	private @Value("${spring.datasource.username}") 
	String userName;

	private @Value("${spring.datasource.password}") 
	String password;
	
	private @Value("${spring.datasource.maximum_pool_size}")
	int maxPool;
	
	private @Value("${spring.datasource.minimum_pool_size}")
	int minPool;

	private @Value("${spring.jpa.properties.hibernate.hbm2ddl.auto}")
	String hbm2ddl;

	private @Value("${spring.jpa.properties.hibernate.dialect}")
	String hbmDialect;

	
	@Bean
	@ConfigurationProperties("spring.datasource")
	public ComboPooledDataSource getDataSource() throws PropertyVetoException
	{
		ComboPooledDataSource ds = new ComboPooledDataSource(); 
		ds.setDriverClass(jdbcDriver);
		ds.setJdbcUrl(jdbcUrl);
		ds.setUser(userName);
		ds.setPassword(password);
		ds.setMaxPoolSize(maxPool);
		ds.setMaxPoolSize(minPool);
		return ds;
	}


	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws PropertyVetoException {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(getDataSource());
		em.setPackagesToScan("com.communicate.model");
		

		
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

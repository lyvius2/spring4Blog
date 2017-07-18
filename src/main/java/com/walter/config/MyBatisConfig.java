package com.walter.config;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * myBatis Setting JAVA Config
 * Created by Walter on 2017-07-16.
 */
/*
@Configuration
@PropertySource("classpath:/db.properties")
@MapperScan(basePackages = "com.walter.dao", sqlSessionFactoryRef = "sqlSessionFactory")
@EnableTransactionManagement
public class MyBatisConfig {

	@Value("${postgresql.uri}")
	private String uri;

	@Value("${postgresql.username}")
	private String username;

	@Value("${postgresql.password}")
	private String password;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl(uri);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource);
		factoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));

		ClassPathResource[] resources = new ClassPathResource[5];
		resources[0] = new ClassPathResource("query/CategorySQL.xml");
		resources[1] = new ClassPathResource("query/CodeSQL.xml");
		resources[2] = new ClassPathResource("query/LogSQL.xml");
		resources[3] = new ClassPathResource("query/MemberSQL.xml");
		resources[4] = new ClassPathResource("query/PostSQL.xml");

		factoryBean.setMapperLocations(resources);
		factoryBean.setTypeAliasesPackage("com.walter.model");
		factoryBean.afterPropertiesSet();
		return factoryBean.getObject();
	}

	@Bean
	public SqlSession sqlSession(SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(dataSource);
		return transactionManager;
	}

	@Bean
	public TransactionTemplate transactionTemplate(PlatformTransactionManager transactionManager) {
		TransactionTemplate transactionTemplate = new TransactionTemplate();
		transactionTemplate.setTransactionManager(transactionManager);
		return transactionTemplate;
	}
}
*/
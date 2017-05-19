package com.example.config;

import javax.sql.DataSource;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.config.properties.PostgreSQLDatabaseProperties;

@EnableConfigurationProperties(value = PostgreSQLDatabaseProperties.class)
public abstract class DatabaseConfig {
	
	@Autowired
	private PostgreSQLDatabaseProperties databaseProperties;

    @Bean
    public abstract DataSource dataSource();

    protected void configureDataSource(BasicDataSource dataSource) {
    	dataSource.setDriverClassName(databaseProperties.getDriverClassName());
    	dataSource.setUrl(databaseProperties.getUrl());
    	dataSource.setUsername(databaseProperties.getUserName());
    	dataSource.setPassword(databaseProperties.getPassword());
        /*dataSource.setMaxActive(databaseProperties.getMaxActive());
        dataSource.setMaxIdle(databaseProperties.getMaxIdle());
        dataSource.setMinIdle(databaseProperties.getMinIdle());
        dataSource.setMaxWait(databaseProperties.getMaxWait());
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);*/
    }
}

@Configuration
@EnableTransactionManagement
@MapperScan(annotationClass = Mapper.class, basePackages = {"com.example"}, sqlSessionFactoryRef = "sqlSession")
class DefaultDatabaseConfig extends DatabaseConfig {
	
	@Autowired
	private ApplicationContext applicationContext;

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		configureDataSource(dataSource);
		return dataSource;
	}
	
	@Bean
    public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource());
		transactionManager.setGlobalRollbackOnParticipationFailure(false);
		return transactionManager;
    }
	
	@Bean(name = "sqlSession")
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource());
		sessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:config/mybatis/sql-mapper-config.xml"));
		sessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:config/mybatis/mappers/**/*SQL.xml"));
		return sessionFactoryBean.getObject();
	}
}
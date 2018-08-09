package com.example.test.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.AutoMappingBehavior;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Configuration
@MapperScan(basePackages = "com.example.test.service", sqlSessionFactoryRef = "mysqlSqlSession")
public class MySqlDataSourceConfig {

	@Primary
	@Bean(name = "mysqlDataSource")
	@Qualifier(value = "mysqlDataSource")
	@ConfigurationProperties(prefix = "spring.mysql.datasource")
	public DataSource mysqlDataSource() {
		return (DataSource) DataSourceBuilder.create().build();
	}

	@Primary
	@Bean(name = "mysqlTransactionManager")
	@Qualifier(value = "mysqlTransactionManager")
	public DataSourceTransactionManager mysqlTransactionManager() {
		return new DataSourceTransactionManager(mysqlDataSource());
	}

	@Primary
	@Bean(name = "mysqlSqlSession")
	@Qualifier(value = "mysqlSqlSession")
	public SqlSessionFactoryBean mysqlSqlSessionFactoryBean() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(mysqlDataSource());
		sqlSessionFactoryBean.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
		sqlSessionFactory.getConfiguration().setJdbcTypeForNull(JdbcType.NULL);
		sqlSessionFactory.getConfiguration().setCallSettersOnNulls(true);
  sqlSessionFactory.getConfiguration().setAutoMappingBehavior(AutoMappingBehavior.NONE);
		return sqlSessionFactoryBean;
	}
	
	@Bean(name = "mysqlDefaultTransactionDefinition")
	public DefaultTransactionDefinition defaultTransactionDefinition(){
		return new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED);
	}
}

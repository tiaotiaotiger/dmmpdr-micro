package com.bocom;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.netflix.discovery.shared.Application;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.bocom.dao")
public class DmmpdrMicroApplication {
	private static Logger logger = Logger.getLogger(Application.class);

	// DataSource配置
	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource() {
		return new com.alibaba.druid.pool.DruidDataSource();
	}

	// 提供SqlSeesion
	@Bean
	public SqlSessionFactory sqlSessionFactoryBean() throws Exception {

		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());

		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

		sqlSessionFactoryBean.setMapperLocations(resolver
				.getResources("classpath:/mapper/*.xml"));

		return sqlSessionFactoryBean.getObject();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	@Bean
	public ServletRegistrationBean druidServlet() {
		ServletRegistrationBean srb = new ServletRegistrationBean();
		srb.setServlet(new StatViewServlet());
		srb.addUrlMappings("/druid/*");
		srb.addInitParameter("loginUsername", "chenzz");
		srb.addInitParameter("loginPassword", "chenzz");
		return srb;
	}

	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean frb = new FilterRegistrationBean();
		frb.setFilter(new WebStatFilter());
		frb.setName("druidWebStatFilter");
		frb.addUrlPatterns("/*");
		frb.addInitParameter("exclusions",
				"*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		frb.addInitParameter("profileEnable", "true");
		frb.addInitParameter("principalCookieName", "USER_COOKIE");
		frb.addInitParameter("principalSessionName", "USER_SESSION");
		return frb;
	}

	/**
	 * Main Start
	 */
	public static void main(String[] args) {
		SpringApplication.run(DmmpdrMicroApplication.class, args);
	}
}

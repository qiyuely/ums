package com.qiyuely.ums.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.github.pagehelper.PageHelper;

/**
 * mybatis配置
 * 
 * @author Qiaoxin.Hong
 *
 */
@Configuration
@MapperScan("com.qiyuely.ums.dao")
@EnableTransactionManagement
public class MybatisConfig {
	
	@Autowired
	private MybatisProperties properties;
	
	/**
	 * mybatis SqlSessionFactory
	 * @return
	 * @throws Exception 
	 */
	@Bean
	public SqlSessionFactory getSqlSessionFactory(DataSource dataSource, PageHelper pageHelper) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setMapperLocations(properties.resolveMapperLocations());
		bean.setConfiguration(properties.getConfiguration());
		//分页插件
		bean.setPlugins(new Interceptor[] {pageHelper});
		return bean.getObject();
	}
	
	/**
	 * 分页控件
	 * @return
	 */
	@Bean
	public PageHelper getPageHelper() {
		PageHelper pageHelper = new PageHelper();  
        Properties p = new Properties();  
        p.setProperty("offsetAsPageNum", "true");  
        p.setProperty("rowBoundsWithCount", "true");  
        p.setProperty("reasonable", "true");  
        pageHelper.setProperties(p);  
		return pageHelper;
	}
}

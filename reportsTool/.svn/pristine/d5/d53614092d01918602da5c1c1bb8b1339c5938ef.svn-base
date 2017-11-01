package com.gtmap.platform.jdbc;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 描述：JDBC数据库操作工具扩展类，提供数据库相关操作和分页数据处理功能
 * 
 * @author byj
 * @version 1.0
 */
public class JdbcTemplateEx extends JdbcTemplate{
	//数据源
	private DataSource dataSource;
	
	/**
	 * 继承JdbcTemplate构造函数
	 */
	public JdbcTemplateEx() {
		super();
	}
	/**
	 * 继承JdbcTemplate构造函数
	 */
	public JdbcTemplateEx(DataSource dataSource) {
		super(dataSource);
	}
	/**
	 * 继承JdbcTemplate构造函数
	 */
	public JdbcTemplateEx(DataSource dataSource, boolean lazyInit) {
		super(dataSource, lazyInit);
	}
	public DataSource getDataSource() {
		return dataSource;
	}
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}

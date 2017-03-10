package com.bocom.util;

import java.sql.Connection;
import java.util.List;

/**
 * ClassName:DBUtil
 * Function: 数据库操作公共类
 * Date:     2017年1月6日 下午1:50:52
 * @author   chenzz
 * @since    JDK 1.6
 */
@SuppressWarnings("all")
public interface DBUtil {

	/**
	 * 获取数据库连接
	 */
	public Connection getConn(String ip, String port, String db, String user,
			String pwd);

	/**
	 * 关闭数据库连接
	 */
	public void closeConn(Connection conn);

	/**
	 * 获取数据访问连接
	 */
	public String getJdbcUrl();

	/**
	 * 执行查询
	 */
	public List executeQuery(Connection conn, String sql, Object... params);

	/**
	 * 动态生成语句
	 */
	public String concatSql(String commonSql, String sqlParam,
			String[] sqlParamVal);
	/**
	 * 获取表查询语句
	 */
	public String getTableSql();
	
	/**
	 * 获取表字段查询语句
	 */
	public String getTableColumnSql();

}

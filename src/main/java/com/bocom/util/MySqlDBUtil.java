package com.bocom.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * ClassName:MySqlDBUtil
 * Function: MySQL数据库操作公共类
 * Date:     2017年1月6日 下午2:00:00
 * @author   chenzz
 * @since    JDK 1.6
 */
@SuppressWarnings("all")
public class MySqlDBUtil implements DBUtil {

	private static final String driverClass = "com.mysql.jdbc.Driver";
	private static final int loginTimeout = 1;
	private static final String jdbcUrl = "jdbc:mysql://{0}:{1}/{2}?useUnicode=true&characterEncoding=utf-8";
	public static final String queryTableSql = "SELECT table_name,table_rows,table_comment FROM information_schema.TABLES WHERE TABLE_SCHEMA=?";
	public static final String queryTableColumnSql = "SELECT table_name,column_name,column_type,column_comment FROM information_schema.COLUMNS WHERE table_schema=?";
	public static String jdbcUrlFormat;

	public Connection getConn(String ip, String port, String db, String user,
			String pwd) {
		Connection conn = null;
		if (StringUtils.isEmpty(db) || StringUtils.isEmpty(ip)
				|| StringUtils.isEmpty(port) || StringUtils.isEmpty(user)
				|| StringUtils.isEmpty(pwd)) {
			return null;
		}
		try {
			Class.forName(driverClass);
			jdbcUrlFormat = MessageFormat.format(jdbcUrl, ip, port, db);
			DriverManager.setLoginTimeout(loginTimeout);
			conn = DriverManager.getConnection(jdbcUrlFormat, user, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public String getJdbcUrl() {
		return jdbcUrlFormat;
	}

	public void closeConn(Connection conn) {
		jdbcUrlFormat = null;
		if (null != conn) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List executeQuery(Connection conn, String sql, Object... params) {
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			if (null != params && params.length > 0) {
				for (int i = 1, len = params.length; i <= len; i++) {
					ps.setObject(i, params[i - 1]);
				}
			}
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Map<String, Object> tm = new HashMap<String, Object>();
				ResultSetMetaData rsmd = rs.getMetaData();
				for (int i = 1, len = rsmd.getColumnCount(); i <= len; i++) {
					String columnLabel = rsmd.getColumnName(i);
					tm.put(columnLabel, rs.getObject(columnLabel));
				}
				resultList.add(tm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultList;
	}

	public String concatSql(String commonSql, String sqlParam,
			String[] sqlParamVal) {
		if (null == sqlParamVal || sqlParamVal.length == 0) {
			return commonSql;
		}
		StringBuilder sql = new StringBuilder(200);
		sql.append(commonSql).append(" AND ").append(sqlParam).append(" IN (");
		for (int i = 1, len = sqlParamVal.length; i <= len; i++) {
			if (i == len) {
				sql.append("?");
			} else {
				sql.append("?,");
			}
		}
		sql.append(")");
		return sql.toString();
	}
	
	public String getTableSql(){
		return queryTableSql;
	}
	
	public String getTableColumnSql(){
		return queryTableColumnSql;
	}

}

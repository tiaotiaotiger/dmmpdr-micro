package com.bocom.util;

import java.sql.CallableStatement;
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
 * ClassName:OracleDBUtil
 * Function: Oracle数据库操作公共类
 * Date:     2017年1月6日 下午2:10:00
 * @author   chenzz
 * @since    JDK 1.6
 */
@SuppressWarnings("all")
public class OracleDBUtil implements DBUtil {

	private static final String driverClass = "oracle.jdbc.OracleDriver";
	private static final int loginTimeout = 1;
	private static final String jdbcUrl = "jdbc:oracle:thin:@{0}:{1}:{2}";
	public static final String queryTableSql = "select table_name,table_rows,table_comment from(select t.table_name,t.num_rows table_rows, uc.comments table_comment from tabs t,user_tab_comments uc where uc.table_name=t.table_name and uc.table_type='TABLE') where 1=1";
	public static final String queryTableColumnSql = "select table_name,column_name,case when data_length is null then data_type else data_type||'('||data_length||')' end column_type,column_comment  FROM (select t.table_name,t.column_name,t.data_type,case when (t.data_type ='VARCHAR' or t.data_type ='VARCHAR2') then t.data_length||'' when t.data_type = 'NUMBER' then (case when (t.data_scale = 0 or t.data_scale is null) then t.data_precision || '' else t.data_precision || ',' || t.data_scale  end) else t.data_precision || ''end data_length,cc.comments column_comment  from user_tab_cols t,user_col_comments cc where t.table_name=cc.table_name and t.column_name=cc.column_name) where 1=1";
	public static final String procedureTableSql="{call dbms_stats.gather_schema_stats(?,estimate_percent=>100,cascade=> TRUE)}";
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
	
	public void executeCall(Connection conn, String sql, Object... params) {
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		try {
			CallableStatement cs = conn.prepareCall(sql);
			if (null != params && params.length > 0) {
				for (int i = 1, len = params.length; i <= len; i++) {
					cs.setObject(i, params[i - 1]);
				}
			}
			cs.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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

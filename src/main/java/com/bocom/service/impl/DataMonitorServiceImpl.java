package com.bocom.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bocom.entity.DataMonitorHis;
import com.bocom.entity.DataServer;
import com.bocom.entity.DataServer2Table;
import com.bocom.entity.DataTable;
import com.bocom.entity.DataTableColumn;
import com.bocom.enums.DataMonitorCategoyEnum;
import com.bocom.enums.DataMonitorTypeEnum;
import com.bocom.enums.DataServerImplementClassEnum;
import com.bocom.enums.DataServerStatusEnum;
import com.bocom.enums.DataTableImpLevelEnum;
import com.bocom.enums.DataTableStatusEnum;
import com.bocom.service.DataMonitorHisService;
import com.bocom.service.DataMonitorService;
import com.bocom.service.DataServer2TableService;
import com.bocom.service.DataServerService;
import com.bocom.service.DataTableColumnService;
import com.bocom.service.DataTableService;
import com.bocom.util.DBUtil;
import com.bocom.util.MySqlDBUtil;
import com.bocom.util.OracleDBUtil;

@Service
@SuppressWarnings("all")
public class DataMonitorServiceImpl implements DataMonitorService {
	private Logger LOG = LoggerFactory.getLogger(DataMonitorServiceImpl.class);
	@Autowired
	private DataServerService dataServerService;
	@Autowired
	private DataTableService dataTableService;
	@Autowired
	private DataServer2TableService dataServer2TableService;
	@Autowired
	private DataTableColumnService dataTableColumnService;
	@Autowired
	private DataMonitorHisService dataMonitorHisService;

	@Override
	public void updateTableNColumn(List<DataServer> list) {
		LOG.info("updateTableNColumn start...");
		if (null != list && list.size() > 0) {
			Date date = new Date();
			for (DataServer data : list) {
				String db = data.getInstanceName();
				String ip = data.getIpAddr();
				String port = data.getPort();
				String user = data.getLoginName();
				String pwd = data.getLoginPwd();
				if (StringUtils.isEmpty(db) || StringUtils.isEmpty(ip)
						|| StringUtils.isEmpty(port)
						|| StringUtils.isEmpty(user)
						|| StringUtils.isEmpty(pwd)) {
					continue;
				}
				String serverId = data.getId();

				String areaType = data.getAreaType();
				String areaTypeName = data.getAreaTypeName();
				String createBy = data.getCreateBy();
				String createByName = data.getCreateByName();
				String createByOrg = data.getCreateByOrg();
				String createByOrgName = data.getCreateByOrgName();
				String serverType = data.getImplClassName();

				DBUtil dbUtil = null;
				String[] sqlParam = null;
				if (DataServerImplementClassEnum.ORACLE.getCode().equals(
						serverType)) {
					dbUtil = new OracleDBUtil();
					sqlParam = new String[] {};
				} else {
					dbUtil = new MySqlDBUtil();
					sqlParam = new String[] { db };
				}

				Connection conn = dbUtil.getConn(ip, port, db, user, pwd);
				if (null == conn) {
					LOG.info("database is not connect,{}", dbUtil.getJdbcUrl());
					continue;
				}
				// 删除原有服务器和表关联信息
				HashMap<String, Object> ds2tParam = new HashMap<String, Object>();
				ds2tParam.put("serverId", serverId);
				List<DataServer2Table> ds2tList = dataServer2TableService
						.listDataByParam(ds2tParam);
				if (null != ds2tList && ds2tList.size() > 0) {
					StringBuilder ds2tableIds = new StringBuilder(200);
					for (DataServer2Table ds2t : ds2tList) {
						ds2tableIds.append(ds2t.getTableId()).append(",");
					}
					// 删除表和字段信息
					dataTableService.delete(ds2tableIds.toString());
				}
				dataServer2TableService.deleteByRefId(ds2tParam);

				// 获取表信息及数据量
				List<Map<String, Object>> tableList = dbUtil.executeQuery(conn,
						dbUtil.getTableSql(), sqlParam);

				Map<String, String> tableIdNNameMap = new HashMap<String, String>();
				// 拼接dataTable list
				if (null != tableList && tableList.size() > 0) {
					List<DataTable> dbTableList = new ArrayList<DataTable>();
					for (Map<String, Object> map : tableList) {
						String tableName = (String) map.get("TABLE_NAME");
						String dataName = (String) map.get("TABLE_COMMENT");
						Number tableRow = (Number) map.get("TABLE_ROWS");
						String tableRows = tableRow == null ? null : tableRow
								.toString();
						DataTable dt = new DataTable();
						dt.setTableName(tableName);
						dt.setDataName(dataName);
						dt.setDataCount(tableRows);
						dt.setAreaType(areaType);
						dt.setAreaTypeName(areaTypeName);
						dt.setCreateBy(createBy);
						dt.setCreateByName(createByName);
						dt.setCreateByOrg(createByOrg);
						dt.setCreateByOrgName(createByOrgName);
						dt.setCreateTime(date);
						dt.setStatus(DataTableStatusEnum.VALID.getCode()
								.toString());
						dt.setImportantLevel(DataTableImpLevelEnum.IMPORTANT
								.getCode().toString());
						dbTableList.add(dt);
					}
					// 插入table list
					if (null != dbTableList && dbTableList.size() > 0) {
						int[] tableIds = dataTableService.addBatch(dbTableList);
						for (DataTable dt : dbTableList) {
							tableIdNNameMap.put(dt.getTableName(), dt.getId());
						}
						List<DataServer2Table> dstList = new ArrayList<DataServer2Table>();
						for (int tableId : tableIds) {
							DataServer2Table dst = new DataServer2Table();
							dst.setServerId(serverId);
							dst.setTableId(String.valueOf(tableId));
							dst.setCreateBy(createBy);
							dst.setCreateByOrg(createByOrg);
							dst.setCreateTime(date);
							dstList.add(dst);
						}
						// 插入server 与 table关联关系
						if (null != dstList && dstList.size() > 0) {
							dataServer2TableService.addBatch(dstList);
						}
					}
				}

				// 获取表字段信息
				List<Map<String, Object>> columnList = dbUtil.executeQuery(
						conn, dbUtil.getTableColumnSql(), sqlParam);
				// 关闭数据库链接
				dbUtil.closeConn(conn);

				// 拼接dataTable list
				if (null != columnList && columnList.size() > 0) {
					List<DataTableColumn> dbColumnList = new ArrayList<DataTableColumn>();
					for (Map<String, Object> map : columnList) {
						String tableName = (String) map.get("TABLE_NAME");
						String columnName = (String) map.get("COLUMN_NAME");
						String definition = (String) map.get("COLUMN_TYPE");
						String description = (String) map.get("COLUMN_COMMENT");

						DataTableColumn dtc = new DataTableColumn();
						dtc.setTableId(tableIdNNameMap.get(tableName));
						dtc.setColumnName(columnName);
						dtc.setDefinition(definition);
						dtc.setDescription(description);
						dtc.setCreateBy(createBy);
						dtc.setCreateByOrg(createByOrg);
						dtc.setCreateTime(date);
						dbColumnList.add(dtc);
					}
					if (null != dbColumnList && dbColumnList.size() > 0) {
						dataTableColumnService.addBatch(dbColumnList);
					}
				}
				List<DataMonitorHis> monitorList = new ArrayList<DataMonitorHis>();
				// 更新历史信息
				DataMonitorHis his = new DataMonitorHis(data.getId(), db, null,
						null, DataMonitorTypeEnum.TABBLE_N_COLUMN.getCode(),
						DataMonitorCategoyEnum.AUTOMATIC.getCode());
				his.setCreateUser(StringUtils.isEmpty(createBy) ? null
						: Integer.valueOf(createBy));
				his.setCreateUserName(createByName);
				his.setCreateTime(date);
				monitorList.add(his);
				dataMonitorHisService.addBatch(monitorList);
			}
		}
		LOG.info("updateTableNColumn end.");
	}

	@Override
	public void updateServerStatus(List<DataServer> list,
			Map<String, Object> param) {
		List<DataMonitorHis> serverMonitorList = null;
		if (null != list && list.size() > 0) {
			serverMonitorList = new ArrayList<DataMonitorHis>();
			Map<String, String> serverStatusMap = new HashMap<String, String>();
			List<String> lastOfflineList = new ArrayList<String>();
			Date date = new Date();
			for (DataServer data : list) {
				String db = data.getInstanceName();
				String ip = data.getIpAddr();
				String port = data.getPort();
				String user = data.getLoginName();
				String pwd = data.getLoginPwd();

				if (StringUtils.isEmpty(db) || StringUtils.isEmpty(ip)
						|| StringUtils.isEmpty(port)
						|| StringUtils.isEmpty(user)
						|| StringUtils.isEmpty(pwd)) {
					continue;
				}
				String serverstatus = data.getStatus();
				String serverId = data.getId();
				String serverType = data.getImplClassName();
				DBUtil dbUtil = null;
				String[] sqlParam = null;
				if (DataServerImplementClassEnum.ORACLE.getCode().equals(
						serverType)) {
					dbUtil = new OracleDBUtil();
					sqlParam = new String[] {};
				} else {
					dbUtil = new MySqlDBUtil();
					sqlParam = new String[] { db };
				}

				Connection conn = dbUtil.getConn(ip, port, db, user, pwd);
				String jdbcUrl = dbUtil.getJdbcUrl();
				dbUtil.closeConn(conn);
				String afterStatus = DataServerStatusEnum.OFFLINE.getCode()
						.toString();
				if (null != conn) {
					afterStatus = DataServerStatusEnum.ONLINE.getCode()
							.toString();
				} else {
					// 数据服务器离线，更新字段
					lastOfflineList.add(serverId);
					LOG.info("database is not connect,{}", jdbcUrl);
				}
				if (afterStatus.equals(serverstatus)) {
					continue;
				}
				String statusValue = serverStatusMap.get(afterStatus);
				serverStatusMap.put(afterStatus, StringUtils
						.isEmpty(statusValue) ? serverId : statusValue + ","
						+ serverId);
				DataMonitorHis his = new DataMonitorHis(serverId, db,
						serverstatus, afterStatus,
						DataMonitorTypeEnum.SERVER_STATUS.getCode(),
						(Integer) param.get("category"),
						(Integer) param.get("createUser"),
						(String) param.get("createUserName"), date);
				serverMonitorList.add(his);
			}
			if (null != serverStatusMap && serverStatusMap.size() > 0) {
				Set<String> keySet = serverStatusMap.keySet();
				for (String status : keySet) {
					Map<String, Object> param1 = new HashMap<String, Object>();
					param1.put("id", Arrays.asList(serverStatusMap.get(status)
							.split(",")));
					param1.put("status", status);

					dataServerService.updateMultiple(param1);
				}
			}

			if (null != serverMonitorList && serverMonitorList.size() > 0) {
				dataMonitorHisService.addBatch(serverMonitorList);
			}

			// 更新服务器最后离线时间
			if (null != lastOfflineList && lastOfflineList.size() > 0) {
				HashMap<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("id", lastOfflineList);
				paramMap.put("lastOfflineTime", date);
				dataServerService.updateMultiple(paramMap);
			}
		}
	}

	@Override
	public void updateTableCount(Map<String, Object> param) {
		if (null != param && param.size() > 0) {
			List<Map<String, Object>> dbTableList = new ArrayList<Map<String, Object>>();
			List<DataMonitorHis> dbHisList = new ArrayList<DataMonitorHis>();

			String tableIdStr = (String) param.get("tableId");
			List<DataServer> serverList = dataServerService
					.listDataByTableId(Arrays.asList(tableIdStr.split(",")));
			if (null != serverList && serverList.size() > 0) {
				Date date = new Date();
				for (DataServer data : serverList) {
					String db = data.getInstanceName();
					String ip = data.getIpAddr();
					String port = data.getPort();
					String user = data.getLoginName();
					String pwd = data.getLoginPwd();

					if (StringUtils.isEmpty(db) || StringUtils.isEmpty(ip)
							|| StringUtils.isEmpty(port)
							|| StringUtils.isEmpty(user)
							|| StringUtils.isEmpty(pwd)) {
						continue;
					}

					String dataCount = data.getRemark();
					String tableIdsServer = data.getCreateBy();
					String tableNamesServer = data.getCreateByName();
					if (StringUtils.isNotEmpty(tableIdsServer)
							&& StringUtils.isNotEmpty(tableNamesServer)) {
						List<String> tableIdList = Arrays.asList(tableIdsServer
								.split(","));
						List<String> tableNameList = Arrays
								.asList(tableNamesServer.split(","));
						List<String> tableDataCount = Arrays.asList(dataCount
								.split(","));
						HashMap<String, String> tableIdNNameMap = new HashMap<String, String>();
						for (int i = 0, len = tableIdList.size(); i < len; i++) {
							tableIdNNameMap.put(tableIdList.get(i),
									tableNameList.get(i));
						}
						StringBuilder tableNames = new StringBuilder(200);
						HashMap<String, String> dataTableCountMap = new HashMap<String, String>();
						if (null != tableIdList && tableIdList.size() > 0) {
							for (int i = 0, len = tableIdList.size(); i < len; i++) {
								String key = tableIdList.get(i);
								tableNames.append(tableIdNNameMap.get(key))
										.append(",");
								dataTableCountMap.put(key,
										tableDataCount.get(i));
							}
						}
						if (null != tableNames && tableNames.length() > 0) {
							// 存在数据，获取表信息及数据量
							String serverType = data.getImplClassName();
							DBUtil dbUtil = null;
							String[] sqlParam = null;
							if (DataServerImplementClassEnum.ORACLE.getCode()
									.equals(serverType)) {
								dbUtil = new OracleDBUtil();
								sqlParam = new String[] {};
							} else {
								dbUtil = new MySqlDBUtil();
								sqlParam = new String[] { db };
							}

							Connection conn = dbUtil.getConn(ip, port, db,
									user, pwd);
							if (null == conn) {
								LOG.info("database is not connect,{}",
										dbUtil.getJdbcUrl());
								continue;
							}
							if (dbUtil instanceof OracleDBUtil) {
								// oracle必须执行更新最新数据
								((OracleDBUtil) dbUtil).executeCall(conn,
										OracleDBUtil.procedureTableSql,
										new String[] { user });
							}
							String tableSql = dbUtil.getTableSql();
							String[] split = tableNames.toString().split(",");
							Object[] tableParam = ArrayUtils.addAll(sqlParam,
									split);
							tableSql = dbUtil.concatSql(dbUtil.getTableSql(),
									"table_name", split);
							List<Map<String, Object>> tableList = dbUtil
									.executeQuery(conn, tableSql, tableParam);
							String jdbcUrl = dbUtil.getJdbcUrl();
							dbUtil.closeConn(conn);
							if (null != tableList && tableList.size() > 0) {
								for (Map<String, Object> map : tableList) {
									String tableName = (String) map
											.get("TABLE_NAME");
									Number tableRow = (Number) map
											.get("TABLE_ROWS");
									String tableRows = tableRow == null ? null
											: tableRow.toString();
									String tableId = "";
									Set<Entry<String, String>> entrySet = tableIdNNameMap
											.entrySet();
									for (Entry<String, String> entry : entrySet) {
										if (null != entry
												&& tableName.equals(entry
														.getValue())) {
											tableId = entry.getKey();
											break;
										}
									}
									String dbTableRow = dataTableCountMap
											.get(tableId);
									if (StringUtils.isEmpty(tableRows)
											|| tableRows.equals(dbTableRow)) {
										LOG.info(
												"table[{},{}] dataCount not changed,{}",
												tableId, tableName, jdbcUrl);
										continue;
									}
									Map<String, Object> updateParam = new HashMap<String, Object>();
									updateParam.put("id",
											Arrays.asList(tableId.split(",")));
									updateParam.put("dataCount", tableRows);
									dbTableList.add(updateParam);

									DataMonitorHis his = new DataMonitorHis(
											tableId, tableName, dbTableRow,
											tableRows,
											DataMonitorTypeEnum.TABLE_COUNT
													.getCode(),
											(Integer) param.get("category"),
											(Integer) param.get("createUser"),
											(String) param
													.get("createUserName"),
											date);
									dbHisList.add(his);
								}
							}
						}
					}
				}
			}
			if (null != dbTableList && dbTableList.size() > 0) {
				for (Map updateParam : dbTableList) {
					dataTableService.updateMultiple(updateParam);
				}
			}
			if (null != dbHisList && dbHisList.size() > 0) {
				dataMonitorHisService.addBatch(dbHisList);
			}
		}
	}
}

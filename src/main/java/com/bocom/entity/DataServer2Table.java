package com.bocom.entity;

import java.util.Date;

/**
 * 数据服务器与数据表的关联 bean
 * @author chenzz
 */
public class DataServer2Table {
	/** 主键
	 */
	private String id;
	/** 服务器ID
	 */
	private String serverId;
	/** 服务器名称
	 */
	private String serverName;
	/** 表ID
	 */
	private String tableId;
	/** 表名称
	 */
	private String tableName;
	/** 创建人
	 */
	private String createBy;
	/** 创建人所属部门
	 */
	private String createByOrg;
	/** 创建时间
	 */
	private Date createTime;
	/** 备用
	 */
	private String remark;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getServerId() {
		return serverId;
	}
	public void setServerId(String serverId) {
		this.serverId = serverId;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public String getTableId() {
		return tableId;
	}
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getCreateByOrg() {
		return createByOrg;
	}
	public void setCreateByOrg(String createByOrg) {
		this.createByOrg = createByOrg;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public DataServer2Table(){
		super();
	}
	public DataServer2Table(String serverId,String serverName,String tableId,String tableName,String createBy,String createByOrg,Date createTime,String remark){
		super();
		this.serverId=serverId;
		this.serverName=serverName;
		this.tableId=tableId;
		this.tableName=tableName;
		this.createBy=createBy;
		this.createByOrg=createByOrg;
		this.createTime=createTime;
		this.remark=remark;
	}
}

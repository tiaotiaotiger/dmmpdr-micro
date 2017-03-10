package com.bocom.entity;

import java.util.Date;

/**
 * 数据表 bean
 * 
 * @author chenzz
 */
public class DataTable {
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 数据名称
	 */
	private String dataName;
	/**
	 * 表名
	 */
	private String tableName;
	/**
	 * 数据量
	 */
	private String dataCount;
	/**
	 * 数据所属部门
	 */
	private String dataOrg;
	/**
	 * 当前状态 0：废止 1：有效
	 */
	private String status;
	/**
	 * 行政级别
	 */
	private String admLevel;
	/**
	 * 重要级别
	 */
	private String importantLevel;
	/**
	 * 所属区域
	 */
	private String areaType;
	/**
	 * 所属区域名称
	 */
	private String areaTypeName;
	/**
	 * 创建人
	 */
	private String createBy;

	/**
	 * 创建人
	 */
	private String createByName;
	/**
	 * 创建人所属部门
	 */
	private String createByOrg;
	/**
	 * 创建人所属部门名称
	 */
	private String createByOrgName;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 废止人
	 */
	private String abolishBy;
	/**
	 * 废止人组织
	 */
	private String abolishByOrg;
	/**
	 * 废止时间
	 */
	private Date abolishTime;
	/**
	 * 备用
	 */
	private String remark;
	/**
	 * 关联的数据服务器
	 */
	private String servers;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getDataCount() {
		return dataCount;
	}

	public void setDataCount(String dataCount) {
		this.dataCount = dataCount;
	}

	public String getDataOrg() {
		return dataOrg;
	}

	public void setDataOrg(String dataOrg) {
		this.dataOrg = dataOrg;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAdmLevel() {
		return admLevel;
	}

	public void setAdmLevel(String admLevel) {
		this.admLevel = admLevel;
	}

	public String getImportantLevel() {
		return importantLevel;
	}

	public void setImportantLevel(String importantLevel) {
		this.importantLevel = importantLevel;
	}

	public String getAreaType() {
		return areaType;
	}

	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}

	public String getAreaTypeName() {
		return areaTypeName;
	}

	public void setAreaTypeName(String areaTypeName) {
		this.areaTypeName = areaTypeName;
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

	public String getAbolishBy() {
		return abolishBy;
	}

	public void setAbolishBy(String abolishBy) {
		this.abolishBy = abolishBy;
	}

	public String getAbolishByOrg() {
		return abolishByOrg;
	}

	public void setAbolishByOrg(String abolishByOrg) {
		this.abolishByOrg = abolishByOrg;
	}

	public Date getAbolishTime() {
		return abolishTime;
	}

	public void setAbolishTime(Date abolishTime) {
		this.abolishTime = abolishTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getServers() {
		return servers;
	}

	public void setServers(String servers) {
		this.servers = servers;
	}

	public String getCreateByName() {
		return createByName;
	}

	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}

	public String getCreateByOrgName() {
		return createByOrgName;
	}

	public void setCreateByOrgName(String createByOrgName) {
		this.createByOrgName = createByOrgName;
	}

	public DataTable() {
		super();
	}

	public DataTable(String dataName, String tableName, String dataCount,
			String dataOrg, String status, String admLevel,
			String importantLevel, String areaType, String areaTypeName,
			String createBy, String createByOrg, Date createTime,
			String abolishBy, String abolishByOrg, Date abolishTime,
			String remark) {
		super();
		this.dataName = dataName;
		this.tableName = tableName;
		this.dataCount = dataCount;
		this.dataOrg = dataOrg;
		this.status = status;
		this.admLevel = admLevel;
		this.importantLevel = importantLevel;
		this.areaType = areaType;
		this.areaTypeName = areaTypeName;
		this.createBy = createBy;
		this.createByOrg = createByOrg;
		this.createTime = createTime;
		this.abolishBy = abolishBy;
		this.abolishByOrg = abolishByOrg;
		this.abolishTime = abolishTime;
		this.remark = remark;
	}

	public DataTable(String id, String dataName, String tableName,
			String dataCount, String dataOrg, String status, String admLevel,
			String importantLevel, String areaType, String areaTypeName,
			String createBy, String createByName, String createByOrg,
			String createByOrgName, Date createTime, String abolishBy,
			String abolishByOrg, Date abolishTime, String remark, String servers) {
		super();
		this.id = id;
		this.dataName = dataName;
		this.tableName = tableName;
		this.dataCount = dataCount;
		this.dataOrg = dataOrg;
		this.status = status;
		this.admLevel = admLevel;
		this.importantLevel = importantLevel;
		this.areaType = areaType;
		this.areaTypeName = areaTypeName;
		this.createBy = createBy;
		this.createByName = createByName;
		this.createByOrg = createByOrg;
		this.createByOrgName = createByOrgName;
		this.createTime = createTime;
		this.abolishBy = abolishBy;
		this.abolishByOrg = abolishByOrg;
		this.abolishTime = abolishTime;
		this.remark = remark;
		this.servers = servers;
	}

}

package com.bocom.entity;

import java.util.Date;

/**
 * 数据更新历史
 * 
 * @author chenzz
 *
 */
public class DataMonitorHis {
	private String id;
	/**
	 * 服务器ID
	 */
	private String refId;
	/**
	 * 服务器名称
	 */
	private String refName;
	/**
	 * 更新前状态
	 */
	private String beforeData;
	/**
	 * 更新后状态
	 */
	private String afterData;
	/**
	 * 更新类型 0：表及字段拉取 1：服务器状态更新 2：表数据量更新
	 */
	private Integer monType;
	/**
	 * 类别 1：自动更新 2：手动更新
	 */
	private Integer category;
	/**
	 * 更新人
	 */
	private Integer createUser;
	/**
	 * 更新人姓名
	 */
	private String createUserName;
	/**
	 * 更新时间
	 */
	private Date createTime;
	/**
	 * 备用
	 */
	private String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRefId() {
		return refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	public String getRefName() {
		return refName;
	}

	public void setRefName(String refName) {
		this.refName = refName;
	}

	public String getBeforeData() {
		return beforeData;
	}

	public void setBeforeData(String beforeData) {
		this.beforeData = beforeData;
	}

	public String getAfterData() {
		return afterData;
	}

	public void setAfterData(String afterData) {
		this.afterData = afterData;
	}

	public Integer getMonType() {
		return monType;
	}

	public void setMonType(Integer monType) {
		this.monType = monType;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
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

	public DataMonitorHis(String id, String refId, String refName,
			String beforeData, String afterData, Integer monType,
			Integer category, Integer createUser, String createUserName,
			Date createTime, String remark) {
		super();
		this.id = id;
		this.refId = refId;
		this.refName = refName;
		this.beforeData = beforeData;
		this.afterData = afterData;
		this.monType = monType;
		this.category = category;
		this.createUser = createUser;
		this.createUserName = createUserName;
		this.createTime = createTime;
		this.remark = remark;
	}

	public DataMonitorHis(String refId, String refName, String beforeData,
			String afterData, Integer monType, Integer category) {
		super();
		this.refId = refId;
		this.refName = refName;
		this.beforeData = beforeData;
		this.afterData = afterData;
		this.monType = monType;
		this.category = category;
	}

	public DataMonitorHis(String refId, String refName, String beforeData,
			String afterData, Integer monType, Integer category,
			Integer createUser, String createUserName, Date createTime) {
		super();
		this.refId = refId;
		this.refName = refName;
		this.beforeData = beforeData;
		this.afterData = afterData;
		this.monType = monType;
		this.category = category;
		this.createUser = createUser;
		this.createUserName = createUserName;
		this.createTime = createTime;
	}

	public DataMonitorHis() {
		super();
	}

}

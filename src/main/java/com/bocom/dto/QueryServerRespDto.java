package com.bocom.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 数据服务器 bean
 * 
 * @author chenzz
 */
public class QueryServerRespDto {
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 名称
	 */
	private String serverName;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * IP地址
	 */
	private String ipAddr;
	/**
	 * 端口
	 */
	private String port;
	/**
	 * 用户登录名
	 */
	private String loginName;
	/**
	 * 登录密码
	 */
	private String loginPwd;
	/**
	 * 网络协议
	 */
	private String netProtocol;
	/**
	 * 实例名
	 */
	private String instanceName;
	/**
	 * 模式名
	 */
	private String sechemaName;
	/**
	 * 接口实现类的名称
	 */
	private String implClassName;
	/**
	 * 行政级别
	 */
	private String admLevel;
	/**
	 * 所属区域(关联t_area_type)
	 */
	private String areaType;
	/**
	 * 所属区域名称( 采集来源区、数据集结区、标准库区、专题库区、警务云平台数据区、警务应用数据区、共享区、未定义区，及其他自定义区域)
	 */
	private String areaTypeName;
	/**
	 * 状态 0：离线 1：在线
	 */
	private String status;
	/**
	 * 最近一次离线时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastOfflineTime;
	/**
	 * 资源类别 1：主服务器 2：备用服务器
	 */
	private String dataType;
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
	 * 备用
	 */
	private String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public String getNetProtocol() {
		return netProtocol;
	}

	public void setNetProtocol(String netProtocol) {
		this.netProtocol = netProtocol;
	}

	public String getInstanceName() {
		return instanceName;
	}

	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}

	public String getSechemaName() {
		return sechemaName;
	}

	public void setSechemaName(String sechemaName) {
		this.sechemaName = sechemaName;
	}

	public String getImplClassName() {
		return implClassName;
	}

	public void setImplClassName(String implClassName) {
		this.implClassName = implClassName;
	}

	public String getAdmLevel() {
		return admLevel;
	}

	public void setAdmLevel(String admLevel) {
		this.admLevel = admLevel;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getLastOfflineTime() {
		return lastOfflineTime;
	}

	public void setLastOfflineTime(Date lastOfflineTime) {
		this.lastOfflineTime = lastOfflineTime;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCreateByName() {
		return createByName;
	}

	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}

	public String getCreateByOrg() {
		return createByOrg;
	}

	public void setCreateByOrg(String createByOrg) {
		this.createByOrg = createByOrg;
	}

	public String getCreateByOrgName() {
		return createByOrgName;
	}

	public void setCreateByOrgName(String createByOrgName) {
		this.createByOrgName = createByOrgName;
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

	public QueryServerRespDto(String id, String serverName, String description,
			String ipAddr, String port, String loginName, String loginPwd,
			String netProtocol, String instanceName, String sechemaName,
			String implClassName, String admLevel, String areaType,
			String areaTypeName, String status, Date lastOfflineTime,
			String dataType, String createBy, String createByName,
			String createByOrg, String createByOrgName, Date createTime,
			String remark) {
		super();
		this.id = id;
		this.serverName = serverName;
		this.description = description;
		this.ipAddr = ipAddr;
		this.port = port;
		this.loginName = loginName;
		this.loginPwd = loginPwd;
		this.netProtocol = netProtocol;
		this.instanceName = instanceName;
		this.sechemaName = sechemaName;
		this.implClassName = implClassName;
		this.admLevel = admLevel;
		this.areaType = areaType;
		this.areaTypeName = areaTypeName;
		this.status = status;
		this.lastOfflineTime = lastOfflineTime;
		this.dataType = dataType;
		this.createBy = createBy;
		this.createByName = createByName;
		this.createByOrg = createByOrg;
		this.createByOrgName = createByOrgName;
		this.createTime = createTime;
		this.remark = remark;
	}

	public QueryServerRespDto() {
		super();
	}

}

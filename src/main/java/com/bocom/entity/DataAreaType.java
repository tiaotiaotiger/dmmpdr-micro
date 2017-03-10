package com.bocom.entity;

import java.util.Date;

/**
 * 区域类别 bean
 * @author chenzz
 */
public class DataAreaType {
	/** 主键
	 */
	private String id;
	/** 名称
	 */
	private String areaName;
	/** 描述
	 */
	private String description;
	/** 类型 0：非通用 1：通用
	 */
	private String category;
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

	public String getAreaName() {
	return areaName;
	}
	public void setAreaName(String areaName) {
	this.areaName = areaName;
	}

	public String getCategory() {
	return category;
	}
	public void setCategory(String category) {
	this.category = category;
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
	public String getRemark() {
	return remark;
	}
	public void setRemark(String remark) {
	this.remark = remark;
	}
	public Date getCreateTime() {
	return createTime;
	}
	public void setCreateTime(Date createTime) {
	this.createTime = createTime;
	}

	public DataAreaType(){
	super();
	}
	public DataAreaType(String areaName,String description,String category,String createBy,String createByOrg,Date  createTime,String remark){
	super();
	this.areaName=areaName;
	this.description=description;
	this.category=category;
	this.createBy=createBy;
	this.createByOrg=createByOrg;
	this.createTime=createTime;
	this.remark=remark;
	}
	public String getDescription() {
	return description;
	}
	public void setDescription(String description) {
	this.description = description;
	}
	public DataAreaType(String areaName,String description,String category,String createBy,String createByOrg,Date createTime,String remark,String id){
	super();
	this.id=id;
	this.areaName=areaName;
	this.description=description;
	this.category=category;
	this.createBy=createBy;
	this.createByOrg=createByOrg;
	this.createTime=createTime;
	this.remark=remark;
	}
	}

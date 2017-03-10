package com.bocom.entity;

import java.util.Date;

/**
 * 数据表字段 bean
 * @author chenzz
 */
public class DataTableColumn {
	/** 主键
	 */
	private String id;
	/** 表ID
	 */
	private String tableId;
	/** 字段名
	 */
	private String columnName;
	/** 字段定义
	 */
	private String definition;
	/** 字段描述
	 */
	private String description;
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
	public String getTableId() {
		return tableId;
	}
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
	
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getDefinition() {
		return definition;
	}
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public DataTableColumn(){
		super();
	}
	public DataTableColumn(String id, String tableId, String columnName,
			String definition, String description, String createBy,
			String createByOrg, Date createTime, String remark) {
		super();
		this.id = id;
		this.tableId = tableId;
		this.columnName = columnName;
		this.definition = definition;
		this.description = description;
		this.createBy = createBy;
		this.createByOrg = createByOrg;
		this.createTime = createTime;
		this.remark = remark;
	}
	
}

package com.zl.mall.base.historyinfo.entity;


import java.io.Serializable;
/**
 * 
 * @author coolz
 *
*/
public class HistoryInfoEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 *
	 * 主键
	 *
	 */
	private String id;

	/**
	 *
	 * 表名
	 *
	 */
	private String tableName;

	/**
	 *
	 * 主键
	 *
	 */
	private String priKey;

	/**
	 *
	 * 主键值
	 *
	 */
	private String priValue;

	/**
	 *
	 * 修改的字段
	 *
	 */
	private String modKey;

	/**
	 *
	 * 修改字段名
	 *
	 */
	private String modName;

	/**
	 *
	 * 原数据
	 *
	 */
	private String oldValue;

	/**
	 *
	 * 新数据
	 *
	 */
	private String newValue;

	/**
	 *
	 * 修改时间
	 *
	 */
	private String updateTime;

	/**
	 *
	 * 修改人id
	 *
	 */
	private String updateUserId;

	/**
	 *
	 * 版本号
	 *
	 */
	private Integer version;
	
	public  void setId(String id){
		this.id = id;
	}
	public String getId(){
		return this.id;
	}
	public  void setTableName(String tableName){
		this.tableName = tableName;
	}
	public String getTableName(){
		return this.tableName;
	}
	public  void setPriKey(String priKey){
		this.priKey = priKey;
	}
	public String getPriKey(){
		return this.priKey;
	}
	public  void setPriValue(String priValue){
		this.priValue = priValue;
	}
	public String getPriValue(){
		return this.priValue;
	}
	public  void setModKey(String modKey){
		this.modKey = modKey;
	}
	public String getModKey(){
		return this.modKey;
	}
	public  void setModName(String modName){
		this.modName = modName;
	}
	public String getModName(){
		return this.modName;
	}
	public  void setOldValue(String oldValue){
		this.oldValue = oldValue;
	}
	public String getOldValue(){
		return this.oldValue;
	}
	public  void setNewValue(String newValue){
		this.newValue = newValue;
	}
	public String getNewValue(){
		return this.newValue;
	}
	public  void setUpdateTime(String updateTime){
		this.updateTime = updateTime;
	}
	public String getUpdateTime(){
		return this.updateTime;
	}
	public  void setUpdateUserId(String updateUserId){
		this.updateUserId = updateUserId;
	}
	public String getUpdateUserId(){
		return this.updateUserId;
	}
	public  void setVersion(Integer version){
		this.version = version;
	}
	public Integer getVersion(){
		return this.version;
	}
}
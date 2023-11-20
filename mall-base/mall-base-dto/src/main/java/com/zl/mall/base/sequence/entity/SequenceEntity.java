package com.zl.mall.base.sequence.entity;


import java.io.Serializable;
/**
 * 
 * @author coolz
 *
*/
public class SequenceEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 *
	 * 序列id
	 *
	 */
	private String seqId;

	/**
	 *
	 * 序列名
	 *
	 */
	private String name;

	/**
	 *
	 * 序列编号
	 *
	 */
	private Integer seqNo;

	/**
	 *
	 * 最小编号
	 *
	 */
	private Integer minNo;

	/**
	 *
	 * 最大编号
	 *
	 */
	private Integer maxNo;

	/**
	 *
	 * 长度
	 *
	 */
	private Integer len;

	/**
	 *
	 * 步长
	 *
	 */
	private Integer step;
	
	/**
	 * 版本号
	 */
	private Integer version;

	public  void setSeqId(String seqId){
		this.seqId = seqId;
	}
	public String getSeqId(){
		return this.seqId;
	}
	public  void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public  void setSeqNo(Integer seqNo){
		this.seqNo = seqNo;
	}
	public Integer getSeqNo(){
		return this.seqNo;
	}
	public  void setMinNo(Integer minNo){
		this.minNo = minNo;
	}
	public Integer getMinNo(){
		return this.minNo;
	}
	public  void setMaxNo(Integer maxNo){
		this.maxNo = maxNo;
	}
	public Integer getMaxNo(){
		return this.maxNo;
	}
	public  void setLen(Integer len){
		this.len = len;
	}
	public Integer getLen(){
		return this.len;
	}
	public  void setStep(Integer step){
		this.step = step;
	}
	public Integer getStep(){
		return this.step;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
}
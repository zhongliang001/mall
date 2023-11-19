package com.zl.mall.base.sequnce.entity;


import java.io.Serializable;
/**
 * 
 * @author coolz
 *
*/
public class SequnceEntity implements Serializable{
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
	 * 步长
	 *
	 */
	private Integer step;

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
	public  void setStep(Integer step){
		this.step = step;
	}
	public Integer getStep(){
		return this.step;
	}
}
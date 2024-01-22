package com.zl.mall.shopcenter.chargerecord.mapper;

import com.zl.mall.shopcenter.chargerecord.entity.ChargeRecordEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 * 
 * @author coolz
 *
*/
@Mapper
public interface ChargeRecordMapper{
	/**
	 * 查询充值记录列表
	 * @param condition 查询条件
	 * @return 返回充值记录列表
		 */
	List<ChargeRecordEntity> queryList(Map<String, Object> condition);
	
	/**
	 * 新增充值记录
	 * @param chargeRecordEntity 充值记录信息 
	 * @return 新增充值记录数量
	 */
	int add(ChargeRecordEntity chargeRecordEntity);
	
	/**
	 * 修改充值记录
	 * @param chargeRecordEntity 充值记录信息 
	 * @return 修改充值记录数量
	 */
	int update(ChargeRecordEntity chargeRecordEntity);
	
	/**
	* 删除充值记录
	* @param id 充值记录
	* @return 删除充值记录数量
	*/
	int delete(@Param("id") String id );
}
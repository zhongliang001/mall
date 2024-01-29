package com.zl.mall.shopcenter.chargerecord.service;

import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.shopcenter.chargerecord.entity.ChargeRecordEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author coolz
 *
*/
@Service
public interface ChargeRecordService{
		/**
	 * 查询充值记录列表
	 * @param queryCondition 查询条件
	 * @return 返回充值记录列表
		 */
	List<ChargeRecordEntity> queryList(QueryCondition queryCondition);
	
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
	 * @param map 充值记录
	 * @return 删除充值记录数量
	 */
	int delete(Map<String, String> map);
}
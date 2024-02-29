package com.zl.mall.shopcenter.ztcstatistics.service;

import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.shopcenter.ztcstatistics.entity.ZtcStatisticsEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
/**
 * 
 * @author coolz
 *
*/
@Service
public interface ZtcStatisticsService{
		/**
	 * 查询直通车关键词统计列表
	 * @param queryCondition 查询条件
	 * @return 返回直通车关键词统计列表
		 */
	List<ZtcStatisticsEntity> queryList(QueryCondition queryCondition);
	
	/**
	 * 新增直通车关键词统计
	 * @param ztcStatisticsEntity 直通车关键词统计信息 
	 * @return 新增直通车关键词统计数量
	 */
	int add(ZtcStatisticsEntity ztcStatisticsEntity);
	
	
	/**
	 * 修改直通车关键词统计
	 * @param ztcStatisticsEntity 直通车关键词统计信息 
	 * @return 修改直通车关键词统计数量
	 */
	int update(ZtcStatisticsEntity ztcStatisticsEntity);
	
	/**
	 * 删除直通车关键词统计
	 * @param id 直通车关键词统计
	 * @return 删除直通车关键词统计数量
	 */
	int delete(String id);

	/**
	 * 上传文件，并且将文件数据读取出来保存在数据库
	 * @param file 文件信息
	 * @param shopId 店铺id
	 * @return 返回数据结果；
	 */
	int  fileUpload(MultipartFile file, String shopId);
}
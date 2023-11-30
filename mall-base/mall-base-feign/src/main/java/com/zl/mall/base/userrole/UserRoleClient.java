package com.zl.mall.base.userrole;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zl.mall.base.userrole.entity.UserRoleEntity;
import com.zl.mall.common.dto.ResultDto;

/**
 * 
 * @author coolz
 *
 */
@FeignClient(name = "${service.base}", contextId="userRole")
@RequestMapping("/userRole")
public interface UserRoleClient {
	/**
	 * 新增用户角色
	 * @param userRoleEntity 用户角色对象
	 * @return 新增数量
	 */
	@PostMapping("/add")
	public ResultDto<Integer> add(@RequestBody UserRoleEntity userRoleEntity);
}

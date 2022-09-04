package com.zl.centric.user.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.zl.centric.user.dto.LoginDto;
import com.zl.centric.user.dto.UserDto;
import com.zl.centric.user.entity.UserEntity;
import com.zl.centric.user.service.UserService;
import com.zl.centric.user.vo.LoginVo;
import com.zl.centric.user.vo.UserVo;
import com.zl.common.dto.QueryCondition;
import com.zl.common.dto.ResultDto;
import com.zl.common.exception.ZlException;
import com.zl.common.utils.ResultUtil;
/**
 * 
 * @author coolz
 *
*/
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@PostMapping("/")
	public ResultDto<List<UserDto>> queryList(@RequestBody QueryCondition queryCondition){
		PageHelper.startPage(queryCondition.getPageNum(), queryCondition.getPageSize());
		List<UserEntity> list = userService.queryList(queryCondition);
		List<UserDto> result = list.stream().map(t -> {
			UserDto userDto = new UserDto();
            BeanUtils.copyProperties(t, userDto);
            return userDto;
        }).collect(Collectors.toList());		
		return  ResultUtil.genenrate(result, "查询成功");
	}
	
	@PostMapping("/add")
	public ResultDto<Integer> add(@Valid @RequestBody UserVo userVo) throws ZlException{
		String password = userVo.getPassword();
		String newPassword = userVo.getNewPassword();
		if(!password.equals(newPassword)) {
			throw new ZlException("两次输入的密码不一致");
		}
		UserEntity userEntity = new UserEntity();
		
		BeanUtils.copyProperties(userVo, userEntity);
		int num = userService.add(userEntity);
		return ResultUtil.genenrate(num, "新增成功");
	}
	@PostMapping("/update")
	public ResultDto<Integer> update(@RequestBody UserEntity userEntity){
		int num = userService.update(userEntity);
		return ResultUtil.genenrate(num, "修改成功");
	}
	@PostMapping("/delete")
	public ResultDto<Integer> delete(@RequestParam(value = "userId") String userId){
		int num = userService.delete(userId);
		return ResultUtil.genenrate(num, "删除成功");
	}
	
	@PostMapping("/login")
	public ResultDto<LoginDto> login(@RequestBody LoginVo loginVo) throws ZlException{
		UserEntity login = userService.login(loginVo);
		LoginDto loginDto = new LoginDto();
        BeanUtils.copyProperties(login, loginDto);
		return ResultUtil.genenrate(loginDto, "查询到用户");
	}
}
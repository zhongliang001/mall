package com.zl.uua.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.stereotype.Component;

import com.zl.centric.user.client.UserClient;
import com.zl.centric.user.dto.LoginDto;
import com.zl.centric.user.dto.UserDto;
import com.zl.common.dto.ResultDto;
import com.zl.uua.details.UserDts;

/**
 * @author zhongliang
 */
@Component
public class UserSecurityServiceImpl implements UserDetailsService {

    @Autowired
    private UserClient userClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	LoginDto loginDto = new LoginDto();
    	loginDto.setUserName(username);
        ResultDto<UserDto> query = userClient.login(loginDto);
        UserDto data = query.getData();
        UserDts userDomain = new UserDts();
        if(data == null){
            throw new UnauthorizedUserException("用户名或密码错误");
        }
        userDomain.setUserDto(data);
        return userDomain;
    }

    public void recodeErrerTimes(String username) {
//        User sysUser = new User();
//        sysUser.setUsername(username);
//        sysUser = sysUserService.selectForLogin(sysUser);
//        Integer errLoginTimes = sysUser.getErrLoginTimes();
//        errLoginTimes = errLoginTimes == null ? 0: errLoginTimes;
//        errLoginTimes ++;
//        sysUser.setErrLoginTimes(errLoginTimes);
//        sysUserService.updateSysUSer(sysUser);
    }
}

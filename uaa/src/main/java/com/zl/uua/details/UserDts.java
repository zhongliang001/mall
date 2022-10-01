package com.zl.uua.details;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import com.zl.centric.user.dto.LoginDto;
import com.zl.centric.user.dto.UserDto;
import com.zl.common.user.constant.UserConstant;

/**
 * 用户实体类
 *
 * @author zhongliang
 * @version 1.0.1
 */
public class UserDts implements UserDetails {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7374157530250076071L;
	private UserDto userDto;
    
    
    public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

	@Override
    public String getPassword() {
        return userDto.getPassword();
    }

    @Override
    public String getUsername() {
        return userDto.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
    	try {
    		 Integer errLoginTimes = userDto.getLoginErrorTimes();
    	        if (errLoginTimes == null) {
    	            return false;
    	        }
    	        return errLoginTimes < 3;
    	}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
       
    }

    @Override
    public boolean isCredentialsNonExpired() {
        if (StringUtils.isEmpty(userDto.getUserStatus())||!UserConstant.CUS_STATE_NOMAL.equals(userDto.getUserStatus())) {
           return false;
        }
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }


}

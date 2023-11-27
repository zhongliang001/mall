package com.zl.mall.user.userauth.dto;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 
 * @author coolz
 *
 */
public class UserAuthDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userId;

	@NotBlank
	private String username;
	@NotBlank
	private String password;
	
	private List<Map<String, String>> shops;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return null;
	}

	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Override
	public String getPassword() {
		
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getUsername() {
		return username;
	}
	
	public void setUserName(String userName) {
		this.username = userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	public List<Map<String, String>> getShops() {
		return shops;
	}

	public void setShops(List<Map<String, String>> shops) {
		this.shops = shops;
	}

}

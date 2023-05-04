package com.greatlearning.ems.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.greatlearning.ems.entity.Role;
import com.greatlearning.ems.entity.User;

public class EmsUserDetails implements UserDetails {

	private User userEntityObj;

	public EmsUserDetails(User userEntityObj) {
		this.userEntityObj = userEntityObj;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<Role> roles = userEntityObj.getRoles();

		List<SimpleGrantedAuthority> authorities = new ArrayList<>();

		for (Role role : roles) {

			SimpleGrantedAuthority sga = new SimpleGrantedAuthority(role.getName());
			authorities.add(sga);
		}

		return authorities;
	}

	@Override
	public String getPassword() {

		return userEntityObj.getPassword();
	}

	@Override
	public String getUsername() {

		return userEntityObj.getUsername();
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

}

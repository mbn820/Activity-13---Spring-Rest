package com.exist.ecc.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.exist.ecc.core.model.dto.UsersDto;
import com.exist.ecc.core.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		UsersDto user = userService.getUserByName(username);

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add( new SimpleGrantedAuthority(user.getUserRole()) );

		return new User( user.getUserName(), user.getPassword(), true, true, true, true, authorities );
	}

}

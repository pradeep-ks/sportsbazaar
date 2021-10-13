package com.sportsbazaar.web.jsp.domain;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.sportsbazaar.persistence.model.User;

public class UserPrincipal implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String email;

    private String password;

    private boolean enabled;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal() {
    }

    public UserPrincipal(Long id, String username, String email, String password, boolean enabled,
	    Collection<? extends GrantedAuthority> authorities) {
	super();
	this.id = id;
	this.username = username;
	this.email = email;
	this.password = password;
	this.enabled = enabled;
	this.authorities = authorities;
    }

    public static UserPrincipal mapUserToUserDetails(User user) {
	List<? extends GrantedAuthority> authorities = user.getRoles().stream()
		.map(role -> new SimpleGrantedAuthority(role.getRoleName().name())).collect(Collectors.toList());

	return new UserPrincipal(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(),
		user.isEnabled(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
	return this.authorities;
    }

    @Override
    public String getPassword() {
	return this.password;
    }

    @Override
    public String getUsername() {
	return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
	return this.enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
	return this.enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
	return this.enabled;
    }

    @Override
    public boolean isEnabled() {
	return this.enabled;
    }

    public Long getId() {
	return id;
    }

    public String getEmail() {
	return email;
    }

}

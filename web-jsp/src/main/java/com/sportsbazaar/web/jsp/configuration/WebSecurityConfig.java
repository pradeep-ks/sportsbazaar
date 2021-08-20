package com.sportsbazaar.web.jsp.configuration;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String SALT = "Sp0rT$B@zaAr";

    public static final String[] PUBLIC_MATCHERS = { "/", "/about", "/contact", "/**/*.css", "/**/*.js", "/**/*.jpg",
	    "/**/*.png", "/**/*.svg", "/**/*.gif", "/categories", "/products", "/productDetails", "/login",
	    "/register" };

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder(12, new SecureRandom(SALT.getBytes()));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
	/*
	 * auth.inMemoryAuthentication().withUser("user01").password(passwordEncoder().
	 * encode("secret")).roles("USER")
	 * .and().withUser("admin").password(passwordEncoder().encode("password")).roles
	 * ("ADMIN", "USER");
	 */
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
	http.authorizeRequests().antMatchers(PUBLIC_MATCHERS).permitAll().antMatchers("/admin/**/*").hasRole("ADMIN")
		.and().formLogin().loginPage("/login").defaultSuccessUrl("/");
    }

}

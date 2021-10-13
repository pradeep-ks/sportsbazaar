package com.sportsbazaar.persistence.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sportsbazaar.persistence.converter.LocalDateTimeAttributeConverter;

@Entity
public class PasswordResetToken extends BaseEntity {

    private static final long serialVersionUID = 2144845834560312789L;

    private static final int DEFAULT_TOKEN_EXPIRY_IN_MINUTES = 15;

    @Column(unique = true)
    private String token;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "token_expiry")
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime tokenExpiry;

    public PasswordResetToken() {
    }

    public PasswordResetToken(String token, User user, LocalDateTime tokenCreationTime, int tokenExpiryInMinutes) {
	if (token == null || user == null || tokenCreationTime == null) {
	    throw new IllegalArgumentException("The user, token and token expiry time cannot be null");
	}
	if (tokenExpiryInMinutes == 0) {
	    tokenExpiryInMinutes = DEFAULT_TOKEN_EXPIRY_IN_MINUTES;
	}
	this.token = token;
	this.user = user;
	this.tokenExpiry = tokenCreationTime.plusMinutes(tokenExpiryInMinutes);
    }

    public String getToken() {
	return token;
    }

    public void setToken(String token) {
	this.token = token;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public LocalDateTime getTokenExpiry() {
	return tokenExpiry;
    }

    public void setTokenExpiry(LocalDateTime tokenExpiry) {
	this.tokenExpiry = tokenExpiry;
    }

}

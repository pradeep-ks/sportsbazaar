package com.sportsbazaar.web.jsp.service;

import com.sportsbazaar.persistence.model.PasswordResetToken;

public interface PasswordResetTokenService {

    PasswordResetToken findByToken(String token);
    
    PasswordResetToken generatePasswordResetTokenFromEmail(String email);
}

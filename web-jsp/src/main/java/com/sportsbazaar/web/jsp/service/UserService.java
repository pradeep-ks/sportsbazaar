package com.sportsbazaar.web.jsp.service;

import com.sportsbazaar.persistence.model.User;
import com.sportsbazaar.web.jsp.dto.SignUpDTO;

public interface UserService {

    User save(SignUpDTO payload);
    
    boolean existsByUsername(String username);
    
    boolean existsByEmail(String email);
    
    void updatePassword(long userId, String password);

    User findByUsername(String name);
    
    User update(User user);
}

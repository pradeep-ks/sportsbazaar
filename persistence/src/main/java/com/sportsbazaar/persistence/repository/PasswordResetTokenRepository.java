package com.sportsbazaar.persistence.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sportsbazaar.persistence.model.PasswordResetToken;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    Optional<PasswordResetToken> findByToken(String token);
    
    @Query("SELECT prt FROM PasswordResetToken prt INNER JOIN prt.user u WHERE prt.user.id = ?1")
    Set<PasswordResetToken> findAllByUserId(Long id);
}

package com.tenpo.calculator.security.refreshtoken.model;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import com.tenpo.calculator.security.JwtTool;
import com.tenpo.calculator.security.user.application.UserTokenDto;
import com.tenpo.calculator.security.user.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RefreshTokenService {
    @Value("${refreshToken.durationMs}")
    private Long refreshTokenDurationMs;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTool jwtTool;

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(String username) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser(userRepository.findByUsername(username).get());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());

        return refreshTokenRepository.save(refreshToken);
    }

    public String refresh(String refreshToken) {
        return findByToken(refreshToken)
                .map(this::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    return jwtTool.createJWT(user.getUsername());
                })
                .orElseThrow(() -> new TokenRefreshFailedException(refreshToken,
                        "Couldn't get token from refresh"));
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshFailedException(token.getToken(), "Refresh token expired, new login required.");
        }

        return token;
    }

    @Transactional
    public int deleteByUserId(String username) {
        return refreshTokenRepository.deleteByUser(userRepository.findByUsername(username).get());
    }
}

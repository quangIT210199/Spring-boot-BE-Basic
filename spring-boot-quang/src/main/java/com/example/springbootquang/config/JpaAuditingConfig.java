package com.example.springbootquang.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfig {
//Để các field trong BaseEntity như createdBy, createdDate,.. lưu tự động thì thêm cái authen này.
// Nó lấy chính ở tk AuditorAwareImpl
    //Nhớ phải fix cái lỗi authoriation ở SpringBootQuangApplication


    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }

    public static class AuditorAwareImpl implements AuditorAware<String> {
        @Override
        public Optional<String> getCurrentAuditor() {
            //authentication chính là cái cờ để kiểm tra nếu khác null và đã đăng nhập rồi
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || !authentication.isAuthenticated()) {
                return null; // chưa đăng nhập thì return null
            }

            return Optional.of(authentication.getName());
//            return authentication.getName();
        }
    }
}
package com.thc.sprbasic2025fall.domain;

import com.thc.sprbasic2025fall.dto.DefaultDto;
import com.thc.sprbasic2025fall.dto.UserDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class RefreshToken extends AuditingFileds{
    @Setter Long userId;
    @Setter String content;

    protected RefreshToken(){}
    private RefreshToken(Long userId, String content) {
        this.userId = userId;
        this.content = content;
    }
    public static RefreshToken of(Long userId, String content) {
        return new RefreshToken(userId, content);
    }
}

package com.intoThe.entities;

import com.intoThe.enums.TokenExpirationUnit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Entity
@Table(name = "INTO_ENTITY_VERIFICATION_TOKEN_DATA")
public class EntityVerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long verificationId;
    private long userId;
    private String verificationToken;
    private String tokenHashType;
    private Boolean isVerified;
    @CreationTimestamp
    private LocalDateTime tokenGeneratedAt;
    private int tokenValidDuration;
    private TokenExpirationUnit tokenValidDurationUnit;

    public EntityVerificationToken() {
        this(false);
    }

    public EntityVerificationToken(Boolean isVerified) {
        this.isVerified = isVerified;
    }

    public static EntityVerificationToken createEntityVerificationToken(){
        return new EntityVerificationToken();
    }

    public EntityVerificationToken setVerificationId(long verificationId) {
        this.verificationId = verificationId;
        return this;
    }

    public EntityVerificationToken setUserId(long user_id) {
        this.userId = user_id;
        return this;
    }

    public EntityVerificationToken setVerificationToken(String verificationToken) {
        this.verificationToken = verificationToken;
        return this;
    }

    public EntityVerificationToken setTokenHashType(String tokenHashType) {
        this.tokenHashType = tokenHashType;
        return this;
    }

    public EntityVerificationToken setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
        return this;
    }

    public EntityVerificationToken setTokenGeneratedAt(LocalDateTime tokenGeneratedAt) {
        this.tokenGeneratedAt = tokenGeneratedAt;
        return this;
    }

    public EntityVerificationToken setTokenValidDuration(int tokenValidDuration) {
        this.tokenValidDuration = tokenValidDuration;
        return this;
    }

    public EntityVerificationToken setTokenValidDurationUnit(TokenExpirationUnit tokenValidDurationUnit) {
        this.tokenValidDurationUnit = tokenValidDurationUnit;
        return this;
    }
}

package com.codeone.socialLogin.jwt;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import lombok.Getter;
import lombok.Setter;


@Component
@Setter
@Getter
public class JwtProperties {
    @Value("${jwt.secret}")
    private String secret;

}
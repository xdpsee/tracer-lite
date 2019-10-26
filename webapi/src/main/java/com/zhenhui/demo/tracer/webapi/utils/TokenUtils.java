package com.zhenhui.demo.tracer.webapi.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.fasterxml.jackson.core.type.TypeReference;
import com.zhenhui.demo.tracer.domain.utils.JsonUtils;
import com.zhenhui.demo.tracer.webapi.restful.exception.TokenException;
import com.zhenhui.demo.tracer.webapi.security.UserPrincipal;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class TokenUtils {

    private static final String KEY_CLAIM_PRINCIPAL = "principal";

    private static Algorithm algorithm = Algorithm.HMAC512("8znpkQ7lYr");

    public String generate(UserPrincipal user) {

        final String principal = JsonUtils.toJsonString(user);
        return JWT.create()
                .withIssuer("service@tracer")
                .withIssuedAt(new Date())
                .withAudience("user@tracer")
                .withExpiresAt(new Date(System.currentTimeMillis() + 7 * 24 * 3600 * 1000))
                .withSubject(user.getUsername())
                .withClaim(KEY_CLAIM_PRINCIPAL, principal)
                .sign(algorithm);
    }

    public UserPrincipal parse(String token) throws TokenException {

        try {
            final Claim principal = JWT.decode(token).getClaim(KEY_CLAIM_PRINCIPAL);
            return JsonUtils.fromJsonString(principal.asString(), new TypeReference<UserPrincipal>() {
            });
        } catch (Exception e) {
            if (e instanceof TokenExpiredException) {
                throw new TokenException("令牌已过期", e);
            }

            throw new TokenException("令牌无效", e);
        }
    }

    public String extractToken(HttpServletRequest request) {

        String header = request.getHeader("Authorization");
        if (StringUtils.isEmpty(header) || !header.startsWith("Bearer ")) {
            return null;
        }

        String[] components = header.split("\\s+");
        if (components.length != 2) {
            return null;
        }

        return components[1];
    }
}



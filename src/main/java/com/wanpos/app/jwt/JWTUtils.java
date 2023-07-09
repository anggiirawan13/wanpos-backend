package com.wanpos.app.jwt;

import com.wanpos.app.dtos.responses.BaseResponse;
import com.wanpos.app.entities.UsersEntity;
import com.wanpos.app.impls.UsersServiceImpl;
import com.wanpos.app.repositories.UsersRepository;
import com.wanpos.handler.InternalServerError;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

import static com.wanpos.app.configuration.ConstantaVariableConfiguration.EXPIRATION_TOKEN;
import static com.wanpos.app.configuration.ConstantaVariableConfiguration.SECRET_KEY_GENERATE_TOKEN;

@Service
public class JWTUtils {

    @Autowired
    private UsersRepository usersRepository;

    public BaseResponse generateToken(String username) {
        UsersEntity user = usersRepository.findByUsername(username);

        HashMap<String, Object> claims = new HashMap<>();
        claims.put("uuid", user.getUuid());
        claims.put("name", user.getUsername());

        return createToken(claims, username);
    }

    private BaseResponse createToken(HashMap<String, Object> claims, String subject) {
        try {
            Date loginTime = new Date(System.currentTimeMillis());

            String token = Jwts.builder()
                    .setClaims(claims)
                    .setSubject(subject)
                    .setIssuedAt(loginTime)
                    .setAudience(String.valueOf(claims.get("name")))
                    .setExpiration(new Date(loginTime.getTime() + EXPIRATION_TOKEN))
                    .signWith(SignatureAlgorithm.HS256, SECRET_KEY_GENERATE_TOKEN)
                    .compact();

            return new BaseResponse(HttpStatus.OK.value(), true, "LOGIN SUCCESS", token);
        } catch (Exception e) {
            return InternalServerError.InternalServerError(e);
        }
    }

    protected boolean isValidToken(String token, UserDetails userDetails, HttpServletRequest request) {
        String username = extractUsername(token, request);

        return (username.equals(userDetails.getUsername()) && !isExpiredToken(token, request));
    }

    protected String extractUsername(String token, HttpServletRequest request) {
        return extractClaims(token, Claims::getSubject, request);
    }

    private boolean isExpiredToken(String token, HttpServletRequest request) {
        return extractExpiration(token, request).before(new Date());
    }

    private Date extractExpiration(String token, HttpServletRequest request) {
        return extractClaims(token, Claims::getExpiration, request);
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsTFunction, HttpServletRequest request) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY_GENERATE_TOKEN)
                    .parseClaimsJws(token)
                    .getBody();

            return claimsTFunction.apply(claims);
        } catch (SignatureException ex) {
            System.out.println("Invalid JWT Signature");
            request.setAttribute("invalid", ex.getMessage());
        } catch (MalformedJwtException ex) {
            System.out.println("Invalid JWT token");
            request.setAttribute("invalid", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            System.out.println("Expired JWT token");
            request.setAttribute("expired", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            System.out.println("Unsupported JWT exception");
            request.setAttribute("invalid", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            System.out.println("Jwt claims string is empty");
            request.setAttribute("invalid", ex.getMessage());
        }

        return null;
    }

}

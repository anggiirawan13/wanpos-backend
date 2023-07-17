package com.wanpos.jwt;

import com.wanpos.app.impl.UserDetailLoginServiceImpl;
import com.wanpos.helper.NullEmptyChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.wanpos.constanta.JWTConst.HEADER_AUTH;
import static com.wanpos.constanta.JWTConst.TOKEN_PREFIX;

@Service
public class JWTFilterUserLogin extends OncePerRequestFilter {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserDetailLoginServiceImpl userDetailLoginServiceImpl;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String headerAuth = request.getHeader(HEADER_AUTH);
        String username = "";
        String token = "";

        if (NullEmptyChecker.isNotNullOrEmpty(headerAuth) && headerAuth.startsWith(TOKEN_PREFIX)) {
            token = headerAuth.substring(7);
            username = jwtUtil.extractUsername(token, request);
        }

        if (NullEmptyChecker.isNotNullOrEmpty(username) && NullEmptyChecker.isNullOrEmpty(SecurityContextHolder.getContext().getAuthentication())) {
            UserDetails userDetails = userDetailLoginServiceImpl.loadUserByUsername(username);

            if (jwtUtil.isValidToken(token, userDetails, request)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}

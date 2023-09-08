package com.wanpos.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wanpos.app.dto.response.BaseResponse;
import com.wanpos.helper.NullEmptyChecker;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);

        BaseResponse baseResponse;

        if (NullEmptyChecker.isNullOrEmpty(httpServletRequest.getHeader("Authorization"))) {
            baseResponse = new BaseResponse(HttpStatus.UNAUTHORIZED.value(), false, "TOKEN_REQUIRED", null);
        } else {
            String expired = String.valueOf(httpServletRequest.getAttribute("expired"));
            if (NullEmptyChecker.isNotNullOrEmpty(expired)) {
                baseResponse = new BaseResponse(HttpStatus.UNAUTHORIZED.value(), false, "EXPIRED_TOKEN", null);
            } else {
                baseResponse = new BaseResponse(HttpStatus.FORBIDDEN.value(), false, "INVALID_TOKEN", null);
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(httpServletResponse.getOutputStream(), baseResponse);
    }
}

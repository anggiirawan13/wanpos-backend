package com.wanpos.app.impl;

import com.wanpos.app.dto.request.UserLoginRequest;
import com.wanpos.app.dto.request.UserRegisterRequest;
import com.wanpos.app.dto.response.BaseResponse;
import com.wanpos.app.entity.UserEntity;
import com.wanpos.app.repository.UserRepository;
import com.wanpos.app.service.UserService;
import com.wanpos.constanta.ResponseMessagesConst;
import com.wanpos.constanta.RoleConst;
import com.wanpos.constanta.StatusConst;
import com.wanpos.handler.InternalServerErrorHandler;
import com.wanpos.helper.DateHelper;
import com.wanpos.jwt.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtil jwtUtil;

    private Logger logger;

    @Override
    public BaseResponse userRegister(UserRegisterRequest request) {
        try {
            UserEntity newUser = new UserEntity();
            newUser.setUsername(request.getUsername());
            newUser.setFullname(request.getFullname());
            newUser.setEmail(request.getEmail());

            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String passEncode = bCryptPasswordEncoder.encode(request.getPassword());

            newUser.setPassword(passEncode);
            newUser.setRole(RoleConst.ADMIN.toString());
            newUser.setUuid(UUID.randomUUID().toString());
            newUser.setStatus(StatusConst.ACTIVE.toString());

            Timestamp dateNow = DateHelper.getTimestampNow();

            newUser.setCreatedAt(dateNow);
            newUser.setCreatedBy("admin");
            newUser.setModifiedAt(dateNow);
            newUser.setModifiedBy("admin");

            UserEntity user = userRepository.save(newUser);

            return new BaseResponse(HttpStatus.CREATED.value(), true, ResponseMessagesConst.INSERT_SUCCESS.toString(), user);
        } catch (Exception e) {
            return InternalServerErrorHandler.InternalServerError(e);
        }
    }

    @Override
    public BaseResponse userLogin(UserLoginRequest request) {
        try {


            HashMap<String, Object> dataLogin = new HashMap<>();
            dataLogin.put("access_token", jwtUtil.generateToken(request.getUsername()));
            dataLogin.put("refresh_token", "");
            dataLogin.put("fullname", request.getUsername());

            return new BaseResponse(HttpStatus.OK.value(), true, "LOGIN_SUCCESS", dataLogin);
        } catch (Exception e) {
            return InternalServerErrorHandler.InternalServerError(e);
        }
    }

}

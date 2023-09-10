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
import com.wanpos.helper.NullEmptyChecker;
import com.wanpos.jwt.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public BaseResponse userRegister(UserRegisterRequest request) {
        try {
            UserEntity newUser = new UserEntity();
            newUser.setUserCode(request.getUser_code());
            newUser.setUsername(request.getUsername());
            newUser.setFullName(request.getFullName());
            newUser.setEmail(request.getEmail());

            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String passEncode = bCryptPasswordEncoder.encode(request.getPassword());

            newUser.setPassword(passEncode);
            newUser.setUuid(UUID.randomUUID().toString());

            if (request.getRole().equalsIgnoreCase("admin")) {
                newUser.setRole(RoleConst.ADMIN.toString());
            } else {
                newUser.setRole(RoleConst.CASHIER.toString());
            }

            if (request.getStatus().equalsIgnoreCase("active")) {
                newUser.setStatus(StatusConst.ACTIVE.toString());
            } else {
                newUser.setStatus(StatusConst.INACTIVE.toString());
            }

            Timestamp dateNow = DateHelper.getTimestampNow();

            newUser.setCreatedAt(dateNow);
            newUser.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
            newUser.setModifiedAt(dateNow);
            newUser.setModifiedBy(SecurityContextHolder.getContext().getAuthentication().getName());

            userRepository.save(newUser);

            return new BaseResponse(HttpStatus.CREATED.value(), true, ResponseMessagesConst.INSERT_SUCCESS.toString());
        } catch (Exception e) {
            return InternalServerErrorHandler.InternalServerError(e);
        }
    }

    @Override
    public BaseResponse userLogin(UserLoginRequest request) {
        try {
            UserEntity user = userRepository.findByUsername(request.getUsername());
            HashMap<String, Object> dataLogin = new HashMap<>();
            dataLogin.put("access_token", jwtUtil.generateToken(request.getUsername()));
            dataLogin.put("refresh_token", "");
            dataLogin.put("fullName", user.getFullName());

            if (NullEmptyChecker.isNotNullOrEmpty(dataLogin)) {
                return new BaseResponse(HttpStatus.OK.value(), true, "LOGIN_SUCCESS", dataLogin);
            }

            return new BaseResponse(HttpStatus.NOT_FOUND.value(), false, "LOGIN_FAILED");
        } catch (Exception e) {
            return InternalServerErrorHandler.InternalServerError(e);
        }
    }

    @Override
    public BaseResponse findAll(int page, int limit, String search) {
        try {
            List<UserEntity> listUser;
            HashMap<String, Object> addEntity = new HashMap<>();
            if (page < 0 || NullEmptyChecker.isNullOrEmpty(limit)) {
                listUser = userRepository.findAll();
            } else {
                Pageable pageable = PageRequest.of(page, limit);
                Page<UserEntity> pageUser = userRepository.findAll(pageable);
                listUser = pageUser.toList();

                addEntity.put("totalPage", pageUser.getTotalPages());
                addEntity.put("totalData", pageUser.getTotalElements());
                addEntity.put("numberOfData", pageUser.getNumberOfElements());
                addEntity.put("number", pageUser.getNumber());
            }

            if (NullEmptyChecker.isNotNullOrEmpty(listUser)) {
                return new BaseResponse(HttpStatus.FOUND.value(), true, ResponseMessagesConst.DATA_FOUND.toString(), listUser, addEntity);
            }

            return new BaseResponse(HttpStatus.NOT_FOUND.value(), false, ResponseMessagesConst.DATA_NOT_FOUND.toString());
        } catch (Exception e) {
            return InternalServerErrorHandler.InternalServerError(e);
        }
    }

    @Override
    public BaseResponse findByUuid(String uuid) {
        try {
            UserEntity listUser = userRepository.findByUuid(uuid);

            if (NullEmptyChecker.isNotNullOrEmpty(listUser)) {
                return new BaseResponse(HttpStatus.FOUND.value(), true, ResponseMessagesConst.DATA_FOUND.toString(), listUser);
            }

            return new BaseResponse(HttpStatus.NOT_FOUND.value(), false, ResponseMessagesConst.DATA_NOT_FOUND.toString());
        } catch (Exception e) {
            return InternalServerErrorHandler.InternalServerError(e);
        }
    }
}

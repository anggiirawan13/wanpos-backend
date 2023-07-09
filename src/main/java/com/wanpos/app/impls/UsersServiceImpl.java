package com.wanpos.app.impls;

import com.wanpos.app.dtos.requests.UsersLoginRequest;
import com.wanpos.app.jwt.JWTUtils;
import com.wanpos.app.repositories.UsersRepository;
import com.wanpos.app.dtos.requests.UsersRegisterRequest;
import com.wanpos.app.dtos.responses.BaseResponse;
import com.wanpos.app.entities.UsersEntity;
import com.wanpos.app.services.UsersService;
import com.wanpos.constanta.ResponseMessagesConst;
import com.wanpos.constanta.RoleConst;
import com.wanpos.constanta.StatusConst;
import com.wanpos.handler.InternalServerError;
import com.wanpos.helper.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private JWTUtils jwtUtils;

    private Logger logger;

    @Override
    public BaseResponse usersRegister(UsersRegisterRequest request) {
        try {
            UsersEntity newUser = new UsersEntity();
            newUser.setUsername(request.getUsername());
            newUser.setFullname(request.getFullname());
            newUser.setEmail(request.getEmail());
            newUser.setPassword(request.getPassword());
            newUser.setRole(RoleConst.ADMIN.toString());
            newUser.setUuid(UUID.randomUUID().toString());
            newUser.setStatus(StatusConst.ACTIVE.toString());

            Timestamp dateNow = DateHelper.getTimestampNow();

            newUser.setCreatedAt(dateNow);
            newUser.setCreatedBy("admin");
            newUser.setModifiedAt(dateNow);
            newUser.setModifiedBy("admin");

            UsersEntity user = usersRepository.save(newUser);

            return new BaseResponse(HttpStatus.CREATED.value(), true, ResponseMessagesConst.INSERT_SUCCESS.toString(), user);
        } catch (Exception e) {
            return InternalServerError.InternalServerError(e);
        }
    }

    @Override
    public BaseResponse usersLogin(UsersLoginRequest request) {
        return new BaseResponse(HttpStatus.OK.value(), true, "LOGIN_SUCCESS", jwtUtils.generateToken(request.getUsername()));
    }

}

package com.wanpos.app.controller;

import com.wanpos.app.dto.response.BaseResponse;
import com.wanpos.app.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User")
@CrossOrigin
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    private BaseResponse findAll(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "limit", required = false, defaultValue = "0") int limit,
            @RequestParam(value = "search", required = false, defaultValue = "") String search) {
        return userService.findAll(page, limit, search);
    }

    @GetMapping("/{uuid}")
    private BaseResponse findByUuid(@PathVariable(value = "uuid") String uuid) {
        return userService.findByUuid(uuid);
    }

}

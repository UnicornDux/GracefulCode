package com.zheye.column.rest;

import com.zheye.column.domain.application.UserService;
import com.zheye.column.domain.model.ZheYeUser;
import com.zheye.column.rest.request.LoginRequest;
import com.zheye.column.rest.request.RegisterRequest;
import com.zheye.column.rest.request.UpdateUserRequest;
import com.zheye.column.rest.response.BaseResponse;
import com.zheye.column.rest.response.LoginTokenResponse;
import com.zheye.column.rest.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Objects;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public BaseResponse<LoginTokenResponse> token(@RequestBody LoginRequest loginRequest) {
        return BaseResponse.success(new LoginTokenResponse(userService.login(loginRequest)));
    }

    @GetMapping("/current")
    public BaseResponse<UserResponse> getCurrentUser(Authentication authentication){
        UserResponse user =  userService.queryCurrentUser(authentication.getName());
        if (Objects.isNull(user)) {
           return BaseResponse.fail(1, "用户不存在");
        }
        return BaseResponse.success(user);

    }

    @PostMapping("create")
    public BaseResponse<ZheYeUser> register(@RequestBody RegisterRequest registerRequest) {
        if (registerRequest == null
          || registerRequest.email() == null || !StringUtils.hasLength(registerRequest.email().trim())
          || registerRequest.password() == null || !StringUtils.hasLength(registerRequest.password().trim())
        ) {
            return BaseResponse.fail(1, "缺少关键参数");
        }
        ZheYeUser user = userService.queryUserByEmail(registerRequest.email());
        if (!Objects.isNull(user)){
            return BaseResponse.fail(1, "邮箱账号已被使用");
        }

        user = new ZheYeUser();
        user.setPassword(passwordEncoder.encode(registerRequest.password()));
        user.setNickname(registerRequest.nickname());
        user.setEmail(registerRequest.email());
        user.setCreatedAt(new Date());
        user = userService.save(user);
        user.setPassword(null);
        return BaseResponse.success(user);
    }

    @PatchMapping("/{id}")
    public BaseResponse<ZheYeUser> updateUser(
            @PathVariable("id") String id,
            @RequestBody UpdateUserRequest updateUserRequest,
            Authentication authentication
    ) {
        ZheYeUser user =  userService.queryUserByEmail(authentication.getName());
        if (!user.get_id().equals(id)) {
           return BaseResponse.fail(1, "无权限修改其他用户的信息");
        }
        user = userService.updateUser(id, updateUserRequest);
        return BaseResponse.success(user);
    }

}

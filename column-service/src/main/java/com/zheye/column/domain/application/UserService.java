package com.zheye.column.domain.application;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zheye.column.domain.mapper.ColumnsMapper;
import com.zheye.column.domain.mapper.ImageMapper;
import com.zheye.column.domain.mapper.UserMapper;
import com.zheye.column.domain.model.Columns;
import com.zheye.column.domain.model.ZheYeUser;
import com.zheye.column.rest.request.LoginRequest;
import com.zheye.column.rest.request.UpdateUserRequest;
import com.zheye.column.rest.response.UserResponse;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.stereotype.Service;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

import java.time.Instant;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtEncoder encoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ImageMapper imageMapper;

    @Autowired
    private ColumnsMapper columnsMapper;


    // 登录接口
    public String login(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (Objects.isNull(authentication)) {
            throw new RuntimeException("登录失败");
        }
        // 认证通过了，生成 token
        Instant now = Instant.now();
        long expiry = 36000L;
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("zheYeBlog")            // 证书签发的机构
                .issuedAt(now)             // 证书签发时间
                .expiresAt(now.plusSeconds(expiry)) // 证书有效期
                .subject(authentication.getName()) // 携带的业务数据信息, 可以传入一个 json 格式的字符串
                .claim("scope", "app") // 面向的用户主题
                .build();
        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public ZheYeUser queryUserByEmail(String email) {
        return userMapper.selectUserDetailByEmail(email);
    }

    public ZheYeUser save(ZheYeUser zheYeUser) {
        // 新增一个用户
        userMapper.insert(zheYeUser);
        Columns columns =  new Columns();
        columns.setAuthor(zheYeUser.get_id());
        columns.setTitle("");
        // 为用户分配一个专栏
        columnsMapper.insert(columns);
        zheYeUser.setColumn(columns.get_id());

        // 将用户分配的专栏更新到用户的信息中
        userMapper.updateById(zheYeUser);
        return userMapper.selectById(zheYeUser.get_id());
    }

    public ZheYeUser updateUser(String id, UpdateUserRequest updateUserRequest) {

        // 更新数据
        ZheYeUser user = new ZheYeUser();
        user.set_id(updateUserRequest._id());
        user.setNickname(updateUserRequest.nickname());
        user.setAvatar(updateUserRequest.avatar());
        user.setDescription(updateUserRequest.description());
        userMapper.updateById(user);

        // 返回更新后的数据
        return userMapper.selectById(id);
    }

    public UserResponse queryCurrentUser(String email) {
        ZheYeUser user = userMapper.selectUserDetailByEmail(email);
        return new UserResponse(
                user.get_id(),
                user.getNickname(),
                user.getEmail(),
                user.getColumn(),
                user.getDescription(),
                imageMapper.selectById(user.getAvatar())
        );
    }
}

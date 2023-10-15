package com.zheye.column.domain.application;

import com.zheye.column.domain.mapper.UserMapper;
import com.zheye.column.domain.model.ZheYeUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ZheYeUserServiceDetail implements UserDetailsService {

    private final UserMapper userMapper;

    @Autowired
    public ZheYeUserServiceDetail(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("---------------------进入系统查找用户 >>" + email + "<<-----------------------");
        ZheYeUser user = userMapper.selectUserDetailByEmail(email);
        if (user == null) {
           throw new UsernameNotFoundException("用户不存在");
        }

        return User.withUsername(user.getEmail())
                        .password(user.getPassword())
                        .authorities("app")
                        .build();
    }
}

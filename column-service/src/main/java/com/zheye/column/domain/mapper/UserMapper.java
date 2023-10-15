package com.zheye.column.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zheye.column.domain.model.ZheYeUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends BaseMapper<ZheYeUser> {


    ZheYeUser selectUserDetailByEmail(@Param("email") String email);
}

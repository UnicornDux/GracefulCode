package com.zheye.column.rest.response;

import com.zheye.column.domain.model.Image;

public record UserResponse(
        String _id,
        String nickname,
        String email,
        String column,
        String description,
        Image avatar
) { }

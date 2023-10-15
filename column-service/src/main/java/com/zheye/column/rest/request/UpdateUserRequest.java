package com.zheye.column.rest.request;

public record UpdateUserRequest(
       String _id,
       String nickname,
       String avatar,
       String description
) { }

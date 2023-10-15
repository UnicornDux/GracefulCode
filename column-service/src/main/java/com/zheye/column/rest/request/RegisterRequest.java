package com.zheye.column.rest.request;

public record RegisterRequest (
         String email,
         String password,
         String nickname
){ }

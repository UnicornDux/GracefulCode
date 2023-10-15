package com.zheye.column.rest.request;

public record PageQuery(
        Long currentPage,
        Long pageSize
) { }

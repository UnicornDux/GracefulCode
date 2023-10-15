package com.zheye.column.rest.response;

import java.util.List;

public record PageResponse<T>(
        Long count,
        Long pageSize,
        Long currentPage,
        List<T> list
) { }

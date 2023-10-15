package com.zheye.column.rest.response;

import com.zheye.column.domain.model.Image;

import java.util.Date;

public record ColumnsResponse(
       String _id,
       String title,
       String description,
       Image avatar,
       String author,
       Date createdAt
) {
    public ColumnsResponse {}
}

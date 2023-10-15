package com.zheye.column.rest.response;

import com.zheye.column.domain.model.Image;
import com.zheye.column.domain.model.ZheYeUser;

import java.util.Date;

public record QueryPostResponse(
        String _id,
        String title,
        String content,
        String excerpt,
        String column,
        Date createdAt,
        ZheYeUser author,
        Image image

) {

}

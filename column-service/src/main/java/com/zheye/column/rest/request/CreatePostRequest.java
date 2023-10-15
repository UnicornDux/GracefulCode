package com.zheye.column.rest.request;

public record CreatePostRequest(
        String title,
        String content,
        String image,
        String column,
        String author

) {
}

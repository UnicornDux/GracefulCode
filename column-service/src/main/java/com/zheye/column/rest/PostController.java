package com.zheye.column.rest;

import com.zheye.column.domain.application.PostService;
import com.zheye.column.rest.request.CreatePostRequest;
import com.zheye.column.rest.response.BaseResponse;
import com.zheye.column.rest.response.QueryPostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("posts")
public class PostController {

    @Autowired
    private PostService postService;
    @GetMapping("/{id}")
    public BaseResponse<QueryPostResponse> getPostsById(@PathVariable("id")String id) {
        return BaseResponse.success(postService.queryById(id));
    }

    @PostMapping("")
    public BaseResponse<QueryPostResponse> createPosts(
            @RequestBody CreatePostRequest queryPosts,
            Authentication authentication
    ){
        QueryPostResponse  responsePost = postService.createPosts(queryPosts, authentication);
        return BaseResponse.success(responsePost);
    }

    @PatchMapping("/{id}")
    public BaseResponse<QueryPostResponse> updatePosts(
            @RequestBody CreatePostRequest createPostRequest,
            @PathVariable("id") String id
    ) {
        return BaseResponse.success(postService.updatePosts(createPostRequest,id));
    }

    @DeleteMapping("/{id}")
    public BaseResponse<QueryPostResponse> deletePosts(@PathVariable("id")String id) {
        return BaseResponse.success(postService.deletePosts(id));
    }





}

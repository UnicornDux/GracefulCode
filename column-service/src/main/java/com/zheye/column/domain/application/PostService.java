package com.zheye.column.domain.application;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zheye.column.domain.mapper.ImageMapper;
import com.zheye.column.domain.mapper.PostMapper;
import com.zheye.column.domain.mapper.UserMapper;
import com.zheye.column.domain.model.Image;
import com.zheye.column.domain.model.Posts;
import com.zheye.column.domain.model.ZheYeUser;
import com.zheye.column.rest.request.CreatePostRequest;
import com.zheye.column.rest.request.PageQuery;
import com.zheye.column.rest.response.PageResponse;
import com.zheye.column.rest.response.QueryPostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ImageMapper imageMapper;

    public QueryPostResponse queryById(String id) {
        Posts posts = postMapper.selectById(id);
        return new QueryPostResponse(
                posts.get_id(),
                posts.getTitle(),
                posts.getContent(),
                posts.getExcerpt(),
                posts.getColumn(),
                posts.getCreatedAt(),
                userMapper.selectById(posts.getAuthorId()),
                imageMapper.selectById(posts.getImageId())
        );
    }


    public QueryPostResponse createPosts(
            CreatePostRequest queryPosts, Authentication authentication
    ) {
        // 插入数据
        Posts post = new Posts(
                queryPosts.title(),
                queryPosts.content(),
                queryPosts.column(),
                new Date(),
                queryPosts.author(),
                queryPosts.image()
        );
        postMapper.insert(post);
        return new QueryPostResponse(
                post.get_id(),
                post.getTitle(),
                post.getContent(),
                post.getExcerpt(),
                post.getColumn(),
                post.getCreatedAt(),
                userMapper.selectById(post.getAuthorId()),
                imageMapper.selectById(post.getImageId())
        );
    }

    public QueryPostResponse updatePosts(CreatePostRequest createPostRequest, String id) {
        Posts post = postMapper.selectById(id);
        if (!StringUtils.isEmpty(createPostRequest.title())) {
            post.setTitle(createPostRequest.title());
        }
        if (!StringUtils.isEmpty(createPostRequest.content())) {
            post.setContent(createPostRequest.content());
        }
        if (!StringUtils.isEmpty(createPostRequest.image()))  {
            post.setImageId(createPostRequest.image());
        }
        postMapper.updateById(post);

        return new QueryPostResponse (
                post.get_id(),
                post.getTitle(),
                post.getContent(),
                post.getExcerpt(),
                post.getColumn(),
                post.getCreatedAt(),
                userMapper.selectById(post.getAuthorId()),
                imageMapper.selectById(post.getImageId())
        );
    }

    public QueryPostResponse deletePosts(String id) {
        Posts post = postMapper.selectById(id);
        Image image = null;
        if (post.getImageId() != null) {
            image = imageMapper.selectById(post.getImageId());
            imageMapper.deleteById(image);
        }
        postMapper.deleteById(post);
        return new QueryPostResponse(
                post.get_id(),
                post.getTitle(),
                post.getContent(),
                post.getExcerpt(),
                post.getColumn(),
                post.getCreatedAt(),
                userMapper.selectById(post.getAuthorId()),
                image
        );
    }

    public PageResponse<QueryPostResponse> queryPagePosts(PageQuery pageQuery, String columnId) {
        Page<Posts> page = new Page<>(pageQuery.currentPage(),pageQuery.pageSize());
        QueryWrapper<Posts>  queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("`column`",columnId);
        Page<Posts> postsPage = postMapper.selectPage(page, queryWrapper);
        List<QueryPostResponse> records = postsPage.getRecords()
                .stream()
                .map(posts -> new QueryPostResponse(
                        posts.get_id(),
                        posts.getTitle(),
                        posts.getContent(),
                        posts.getExcerpt(),
                        posts.getColumn(),
                        posts.getCreatedAt(),
                        userMapper.selectById(posts.getAuthorId()),
                        imageMapper.selectById(posts.getImageId())
                )).toList();
        return new PageResponse<>(
                postsPage.getTotal(),
                postsPage.getSize(),
                postsPage.getCurrent(),
                records
        );
    }
}

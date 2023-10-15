package com.zheye.column.rest;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.zheye.column.domain.application.ColumnsService;
import com.zheye.column.domain.application.PostService;
import com.zheye.column.rest.request.PageQuery;
import com.zheye.column.rest.request.UpdateColumn;
import com.zheye.column.rest.response.BaseResponse;
import com.zheye.column.rest.response.ColumnsResponse;
import com.zheye.column.rest.response.PageResponse;
import com.zheye.column.rest.response.QueryPostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/columns")
public class ColumnController {

    @Autowired
    private ColumnsService columnsService;

    @Autowired
    private PostService postService;

    @GetMapping("/public")
    public BaseResponse<PageResponse<ColumnsResponse>> getColumns(
            @RequestParam("currentPage")Long currentPage,
            @RequestParam("pageSize")Long pageSize
    ) {
        // 查询数据
        PageResponse<ColumnsResponse> pageResult = columnsService.queryPageColumns(
                new PageQuery(
                    currentPage,
                    pageSize
                )
        );
        return BaseResponse.success(pageResult);
    }

    @GetMapping("/{id}")
    public BaseResponse<ColumnsResponse> getColumnById(@PathVariable("id")String id) {
       return BaseResponse.success(columnsService.queryById(id));
    }

    @PatchMapping("/{id}")
    public BaseResponse<ColumnsResponse> updateColumn(
            @PathVariable("id") String id,
            @RequestBody UpdateColumn updateColumn
    ){
        ColumnsResponse columns = columnsService.queryById(id);
        if (Objects.isNull(columns)) {
            return BaseResponse.fail(1, "专栏已不存在，请刷新后重试");
        }
        return BaseResponse.success(columnsService.updateColumn(updateColumn, id));
    }

    @GetMapping("/{id}/posts")
    public BaseResponse<PageResponse<QueryPostResponse>> getColumnsPagePosts(
           @PathVariable("id") String columnId,
           @RequestParam("currentPage") Long currentPage,
           @RequestParam("pageSize") Long pageSize
    ) {
        // 查询数据
        PageResponse<QueryPostResponse> pageResult = postService.queryPagePosts(
                new PageQuery(
                        currentPage,
                        pageSize
                ),
                columnId
        );
        return BaseResponse.success(pageResult);
    }

}

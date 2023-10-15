package com.zheye.column.domain.application;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zheye.column.domain.mapper.ColumnsMapper;
import com.zheye.column.domain.mapper.ImageMapper;
import com.zheye.column.domain.model.Columns;
import com.zheye.column.rest.request.PageQuery;
import com.zheye.column.rest.request.UpdateColumn;
import com.zheye.column.rest.response.ColumnsResponse;
import com.zheye.column.rest.response.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ColumnsService {

    @Autowired
    private ColumnsMapper columnsMapper;

    @Autowired
    private ImageMapper imageMapper;

    public PageResponse<ColumnsResponse> queryPageColumns(PageQuery pageQuery) {
        Page<Columns> page = new Page<>(pageQuery.currentPage(),pageQuery.pageSize());
        QueryWrapper<Columns>  queryWrapper = new QueryWrapper<>();
        Page<Columns> columnsPage = columnsMapper.selectPage(page, queryWrapper);
        List<ColumnsResponse> records = columnsPage.getRecords()
            .stream()
            .map(column -> new ColumnsResponse(
                column.get_id(),
                column.getTitle(),
                column.getDescription(),
                imageMapper.selectById(column.getAvatar()),
                column.getAuthor(),
                column.getCreatedAt()
        )).toList();
        return new PageResponse<>(
                columnsPage.getTotal(),
                columnsPage.getSize(),
                columnsPage.getCurrent(),
                records
        );
    }

    public ColumnsResponse queryById(String id) {
        Columns column = columnsMapper.selectById(id);
        return new ColumnsResponse(
                column.get_id(),
                column.getTitle(),
                column.getDescription(),
                imageMapper.selectById(column.getAvatar()),
                column.getAuthor(),
                column.getCreatedAt()
        );
    }

    public ColumnsResponse updateColumn(UpdateColumn updateColumn, String id) {
        Columns columns = columnsMapper.selectById(id);
        String title = ! StringUtils.isEmpty(updateColumn.title()) ? updateColumn.title() : columns.getTitle();
        String avatar = ! StringUtils.isEmpty(updateColumn.avatar()) ? updateColumn.avatar() : columns.getAvatar();
        String description = ! StringUtils.isEmpty(updateColumn.description()) ? updateColumn.description() : columns.getDescription();
        Columns col = new Columns(columns.get_id(), title, description, columns.getAuthor(), avatar, columns.getCreatedAt());
        columnsMapper.updateById(col);

        return new ColumnsResponse(
                col.get_id(),
                col.getTitle(),
                col.getDescription(),
                imageMapper.selectById(col.getAvatar()),
                col.getAuthor(),
                col.getCreatedAt()
        );
    }
}

package com.zheye.column.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("posts")
public class Posts{

       @TableId(value = "_id", type = IdType.ASSIGN_ID)
       private String _id;
       private String title;
       private String content;
       private String excerpt;
       @TableField("`column`")

       private String column;
       @TableField("created_at")
       private Date createdAt;

       @TableField("author_id")
       private String authorId;
       @TableField("image_id")
       private String imageId;

       public Posts(){}
        public Posts(String title, String content, String column, Date createdAt, String authorId, String imageId) {
                this.title = title;
                this.content = content;
                this.column = column;
                this.createdAt = createdAt;
                this.authorId = authorId;
                this.imageId = imageId;
        }

        public String get_id() {
                return _id;
        }

        public void set_id(String _id) {
                this._id = _id;
        }

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public String getContent() {
                return content;
        }

        public void setContent(String content) {
                this.content = content;
        }

        public String getExcerpt() {
                return excerpt;
        }

        public void setExcerpt(String excerpt) {
                this.excerpt = excerpt;
        }

        public String getColumn() {
                return column;
        }

        public void setColumn(String column) {
                this.column = column;
        }

        public Date getCreatedAt() {
                return createdAt;
        }

        public void setCreatedAt(Date createdAt) {
                this.createdAt = createdAt;
        }

        public String getAuthorId() {
                return authorId;
        }

        public void setAuthorId(String authorId) {
                this.authorId = authorId;
        }

        public String getImageId() {
                return imageId;
        }

        public void setImageId(String imageId) {
                this.imageId = imageId;
        }
}

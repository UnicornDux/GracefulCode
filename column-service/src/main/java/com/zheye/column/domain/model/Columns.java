package com.zheye.column.domain.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("columns")
public class Columns {

        @TableId("_id")
        private String _id;
        private String title;
        private String description;
        private String author;
        private String avatar;
        @TableField("created_at")
        private Date createdAt;

        public Columns() {}
        public Columns(String _id, String title, String description, String author, String avatar, Date createdAt) {
                this._id = _id;
                this.title = title;
                this.description = description;
                this.author = author;
                this.avatar = avatar;
                this.createdAt = createdAt;
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

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public String getAuthor() {
                return author;
        }

        public void setAuthor(String author) {
                this.author = author;
        }

        public String getAvatar() {
                return avatar;
        }

        public void setAvatar(String avatar) {
                this.avatar = avatar;
        }

        public Date getCreatedAt() {
                return createdAt;
        }

        public void setCreatedAt(Date createdAt) {
                this.createdAt = createdAt;
        }
}

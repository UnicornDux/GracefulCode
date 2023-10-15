package com.zheye.column.domain.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("images")
public class Image{

   @TableId("_id")
   private String  _id;
   private String url;
   @TableField("created_at")
   private Date createdAt;

   public Image(){}
   public Image(String url, Date createdAt) {
      this.url = url;
      this.createdAt = createdAt;
   }

   public String get_id() {
      return _id;
   }

   public void set_id(String _id) {
      this._id = _id;
   }

   public String getUrl() {
      return url;
   }

   public void setUrl(String url) {
      this.url = url;
   }

   public Date getCreatedAt() {
      return createdAt;
   }

   public void setCreatedAt(Date createdAt) {
      this.createdAt = createdAt;
   }
}

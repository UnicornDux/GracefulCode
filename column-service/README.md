

## 项目部署

### 前端项目

- 该项目部署前端开源项目地址 [zheye-column](https://github.com/UnicornUI/zheye-column.git/)

### 当前项目
    
- 当前项目中使用到了图片，但是没有配置图片服务器的地址, 图片存储在本地, 可参考配置

```yml
file:
  image:
    path: /home/raabe/images
```
- 配置好文件存储的地址之后需要使用 nginx 代理这个图片地址，(数据库存储的相对路径地址，因此需要使用与前端项目相同的nginx部署,参考配置)

```nginx
location /images { 
    alias /home/raabe/images;
}
```

#### 配置项目需要的数据库

- `sql` 文件夹中提供了项目中使用的 MySQL `init.sql` 语句
  - 先创建一个数据库, 示例中使用的是 (zheye), 在数据中执行 `init.sql`
  - 启动 column-service 项目即可




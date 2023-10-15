package com.zheye.column.rest;

import com.zheye.column.domain.application.ImageService;
import com.zheye.column.domain.model.Image;
import com.zheye.column.rest.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.DigestUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("upload")
public class FileController {

    @Value("${file.image.path}")
    private String imagePath;

    @Autowired
    private ImageService imageService;

    @PostMapping
    public BaseResponse<Image> uploadFile(MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        String extName = filename.substring(filename.lastIndexOf("."));
        String md5 =  DigestUtils.md5DigestAsHex(file.getBytes());

        File path = new File(imagePath);
        if (!path.exists()) {
            path.mkdirs();
        }
        FileCopyUtils.copy(file.getBytes(), new File(imagePath + "/" + md5 + extName));
        String url = "/images/" + md5 + extName;
        Image image = new Image(
             url,
             new Date()
        );
        imageService.savaImage(image);
        return  BaseResponse.success(image);
    }

}

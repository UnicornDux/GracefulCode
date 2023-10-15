package com.zheye.column.domain.application;

import com.zheye.column.domain.mapper.ImageMapper;
import com.zheye.column.domain.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    @Autowired
    private ImageMapper imageMapper;

    public Image savaImage(Image image) {
       imageMapper.insert(image);
       return image;
    }

}

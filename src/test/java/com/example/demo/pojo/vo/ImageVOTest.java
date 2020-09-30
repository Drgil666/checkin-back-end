package com.example.demo.pojo.vo;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Gilbert
 * @date 2020/9/30 9:01
 */
class ImageVOTest {

    @Test
    void Image() {
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("test1","111");
        hashMap.put("test2","222");
        String content= JSON.toJSONString(hashMap);
        String imageString=ImageVO.writeToBase64(content,200,200);
        System.out.println(imageString);
        //根据业务具体需求来看,可能需要+22(意义是去掉开头的"data:image/png;base64,")
    }
}
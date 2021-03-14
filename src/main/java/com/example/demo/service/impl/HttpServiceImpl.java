package com.example.demo.service.impl;

import com.example.demo.service.HttpService;
import com.sun.istack.internal.NotNull;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author Gilbert
 * @date 2021/1/15 14:25
 */
@Service
public class HttpServiceImpl implements HttpService {
    @Resource
    private RestTemplate restTemplate;

    /**
     * 获取openId
     *
     * @param jsCode 对应参数
     * @return 对应的返回值
     */
    @Override
    public Object getOpenId(@NotNull String jsCode) {
        List<HttpMessageConverter<?>> converterList = restTemplate.getMessageConverters();
        converterList.remove(1);
        // 移除原来的转换器
        // 设置字符编码为utf-8
        HttpMessageConverter<?> converter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        converterList.add(1, converter);
        // 添加新的转换器(注:convert顺序错误会导致失败)
        restTemplate.setMessageConverters(converterList);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        URI uri = URI.create("https://api.weixin.qq.com/sns/jscode2session?appid=wx3ed951293baeadc9" +
                "&secret=0d75d409db2e94ce3bed3a611b23ac25&grant_type=authorization_code&js_code=" + jsCode);
        ResponseEntity<String> result = restTemplate.postForEntity(uri, httpEntity, String.class);
        return result.getBody();
    }
}

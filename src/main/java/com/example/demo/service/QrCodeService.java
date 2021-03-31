package com.example.demo.service;

import org.jetbrains.annotations.NotNull;

import java.awt.image.BufferedImage;

/**
 * @author yutao
 */
public interface QrCodeService {
    /**
     * 创建二维码
     * 1、如果高度和宽度都有指定，使用指定的高宽
     * 2、如果高宽都未指定，使用默认的高宽300x300
     * 3、只指定了高宽中的一个，则高宽相等
     *
     * @param content 二维码封装内容
     * @param weight  宽度
     * @param height  高度
     * @return 生成的二维码图片
     */
    BufferedImage createImage(@NotNull String content, @NotNull Integer weight, @NotNull Integer height);

    /**
     * 以base64形式输出二维码
     *
     * @param content 要转换的字符串
     * @param weight  宽度
     * @param height  高度
     * @return 转换好的字符串
     */
    String writeToBase64(@NotNull String content, @NotNull Integer weight, @NotNull Integer height);

    /**
     * 生成二维码
     *
     * @param vo 二维码存储信息
     * @return 生成的base64串
     */
    String createQr(@NotNull String vo);
}

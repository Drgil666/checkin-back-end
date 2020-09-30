package com.example.demo.pojo.vo;

import com.alibaba.fastjson.JSON;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.bson.internal.Base64;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author Gilbert
 * @date 2020/9/30 8:51
 * 进行图片相关的生成,转化
 */
@Slf4j
@Data
public class ImageVO {
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
    public static BufferedImage createImage(String content, Integer weight, Integer height) {

        if (weight == null && height == null) {
            weight = 300;
            height = 300;
        } else {
            weight = (weight == null ? height : weight);
            height = (height == null ? weight : height);
        }
        // 相关设置
        HashMap<EncodeHintType, Comparable> hints = new HashMap<>();
        // L(7%) M(15%) Q(25%) H(30%)
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 二维码边界空白大小1,2,3,4，默认4，最大
        hints.put(EncodeHintType.MARGIN, 1);
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        try {
            QRCodeWriter writer = new QRCodeWriter();
            BitMatrix bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, weight, height, hints);
            return MatrixToImageWriter.toBufferedImage(bitMatrix);

        } catch (WriterException e) {
            log.error("生成二维码失败, 错误信息:{}", JSON.toJSONString(e));
            throw new RuntimeException("生成二维码失败");
        }
    }

    /**
     * 以base64形式输出二维码
     *
     * @param content 要转换的字符串
     * @param weight  宽度
     * @param height  高度
     * @return 转换好的字符串
     */
    public static String writeToBase64(String content, Integer weight, Integer height) {
        try {
            BufferedImage image = createImage(content, weight, height);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            // 输出图片为png格式
            ImageIO.write(image, "png", bos);
            return "data:image/png;base64," + Base64.encode(bos.toByteArray());
        } catch (IOException e) {
            log.error("生成二维码失败, 二维码转base64失败，错误信息:{}", JSON.toJSONString(e));
            throw new RuntimeException("生成二维码失败");
        }
    }
}

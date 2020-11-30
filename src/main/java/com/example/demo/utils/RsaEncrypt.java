package com.example.demo.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author DrGilbert
 */
public class RsaEncrypt {
    private static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCh2CtfrNi9urkD2kEIbAuU3b7KcrV+C1DxQ5pFU9F2sltCdA9SCaHF/PC3/lZMcNosTDM1yxH72e9L5rNBtvvrDrQgBcNRURTT+dhah0IaM/RKnZJTZ3theg94jw/+HZjvauPkdoRvK3ij7rwlLLMtVNsKFecoI5EqByn5iEwx4QIDAQAB";
    private static String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKHYK1+s2L26uQPaQQhsC5TdvspytX4LUPFDmkVT0XayW0J0D1IJocX88Lf+Vkxw2ixMMzXLEfvZ70vms0G2++sOtCAFw1FRFNP52FqHQhoz9EqdklNne2F6D3iPD/4dmO9q4+R2hG8reKPuvCUssy1U2woV5ygjkSoHKfmITDHhAgMBAAECgYAvvZhtzIrSzLWu0T7FwTlZXF/fUB8BY00pHgvvz13Gaa5J1/p89KJnyMkpPTkvq0pwLroccB6J8CuKdC8Ef3mn0LDf7AffBcQF72jMloUTF+nmTsSlHqroUbtyX/hS5ZVt+qBlzhagugQC0secKdbysUEw/KDw/VJN5CnK2fLCEQJBANLph/TDGTKjMLSJlctDQL+wxjTUeuQZcEO7uQtW4oMWoG5dlw9hqajo5+ew1VpBFZzsB4wGveidjkpx9rZfjYcCQQDEcVU1E1qG9vcTUlFFRQMgt9xvYGjdAY1APEgGGPGt/1JSnvrzLjqCqFqiGw7BuBxgiTn5I6oUxSoxCxRowl9XAkA6Y1o3AVfVc+YE5N3qMokXSDnm4DfRM9Wneq2knP4CKl5pj1FFedT9JNUmiAbYgJIaKiGAu1ERt6RvqZMSM+u7AkAUvgLJP+nnJlygZai/L7JM2h0khYGNFITdq3/PZQLf5H3Ey5Sj6NW0xiILy9+bBN26M8MzU1i6KLdzxj+5cAyxAkAt1jKZeHclYy+bJ0xyG0sewNoo9BFcrDiPEAJwwpCbjn5g8TCps4JdxnGA8IviFDX/WxIRt70ezV8SS7Hf84M6";


    /**
     * RSA公钥加密
     *
     * @param str 加密字符串
     * @return 密文
     * @throws Exception 加密过程中的异常信息
     */
    public static String encrypt(String str) throws Exception {
        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        return Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));
    }

    /**
     * RSA私钥解密
     *
     * @param str 加密字符串
     * @return 铭文
     * @throws Exception 解密过程中的异常信息
     */
    public static String decrypt(String str) throws Exception {
        //64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        return new String(cipher.doFinal(inputByte));
    }
}


package com.yxj.practise.test.encode.SM4;

import com.yxj.practise.test.encode.RadixUtil;
import com.yxj.practise.test.encode.StringUtils;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author: LT
 * @date: 2018/2/26
 */
public class SM4Utils {
    private static Pattern PATTERN_SM4 = Pattern.compile("\\s*|\t|\r|\n");

    private String secretKey = "";
    private String base64Key = "";
    private String iv = "";
    private boolean isHexString = false;

    public SM4Utils(String secretKey, String iv, Boolean isHexString) {
        this.secretKey = secretKey;
        this.iv = iv;
        this.isHexString = isHexString;
    }

    public SM4Utils(String secretKey, String base64Key , String iv, Boolean isHexString) {
        this.base64Key = base64Key;
        this.secretKey = secretKey;
        this.iv = iv;
        this.isHexString = isHexString;
    }


    public SM4Utils() {
    }



    public String encryptData_ECB(String plainText) {
        try {
            SM4_Context ctx = new SM4_Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_ENCRYPT;

            byte[] keyBytes;
            if (isHexString) {
                keyBytes = RadixUtil.hexStringToBytes(secretKey);
            } else {
                if(StringUtils.isNotBlank(base64Key)){
                    keyBytes = new BASE64Decoder().decodeBuffer(base64Key);
                }else {
                    keyBytes = secretKey.getBytes("UTF-8");
                }
            }

            SM4 sm4 = new SM4();
            sm4.sm4_setkey_enc(ctx, keyBytes);
            byte[] encrypted = sm4.sm4_crypt_ecb(ctx, plainText.getBytes("UTF-8"));
            String cipherText = new BASE64Encoder().encode(encrypted);
            if (cipherText != null && cipherText.trim().length() > 0) {
                Matcher m = PATTERN_SM4.matcher(cipherText);
                cipherText = m.replaceAll("");
            }
            return cipherText;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String decryptData_ECB(String cipherText) {
        try {
            SM4_Context ctx = new SM4_Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_DECRYPT;

            byte[] keyBytes;
            if (isHexString) {
                keyBytes = RadixUtil.hexStringToBytes(secretKey);
            } else {
                if(StringUtils.isNotBlank(base64Key)){
                    keyBytes = new BASE64Decoder().decodeBuffer(base64Key);
                }else {
                    keyBytes = secretKey.getBytes("UTF-8");
                }
            }

            SM4 sm4 = new SM4();
            sm4.sm4_setkey_dec(ctx, keyBytes);
            byte[] decrypted = sm4.sm4_crypt_ecb(ctx, new BASE64Decoder().decodeBuffer(cipherText));
            return new String(decrypted, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String encryptData_CBC(String plainText) {
        try {
            SM4_Context ctx = new SM4_Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_ENCRYPT;

            byte[] keyBytes;
            byte[] ivBytes;
            if (isHexString) {
                keyBytes = RadixUtil.hexStringToBytes(secretKey);
                ivBytes = RadixUtil.hexStringToBytes(iv);
            } else {
                keyBytes = secretKey.getBytes();
                ivBytes = iv.getBytes();
            }

            SM4 sm4 = new SM4();
            sm4.sm4_setkey_enc(ctx, keyBytes);
            byte[] encrypted = sm4.sm4_crypt_cbc(ctx, ivBytes, plainText.getBytes("UTF-8"));
            String cipherText = new BASE64Encoder().encode(encrypted);
            if (cipherText != null && cipherText.trim().length() > 0) {
                Matcher m = PATTERN_SM4.matcher(cipherText);
                cipherText = m.replaceAll("");
            }
            return cipherText;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String decryptData_CBC(String cipherText) {
        try {
            SM4_Context ctx = new SM4_Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_DECRYPT;

            byte[] keyBytes;
            byte[] ivBytes;
            if (isHexString) {
                keyBytes = RadixUtil.hexStringToBytes(secretKey);
                ivBytes = RadixUtil.hexStringToBytes(iv);
            } else {
                keyBytes = secretKey.getBytes("UTF-8");
                ivBytes = iv.getBytes("UTF-8");
            }

            SM4 sm4 = new SM4();
            sm4.sm4_setkey_dec(ctx, keyBytes);
            byte[] decrypted = sm4.sm4_crypt_cbc(ctx, ivBytes, new BASE64Decoder().decodeBuffer(cipherText));
            return new String(decrypted, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws IOException {
        /*String plainText = "ererfeiisgod";

        SM4Utils sm4 = new SM4Utils();
        sm4.secretKey = "JeF8U9wHFOMfs2Y8";
        sm4.isHexString = false;

        System.out.println("ECB模式");
        String cipherText = sm4.encryptData_ECB(plainText);
        System.out.println("密文: " + cipherText);
        System.out.println("");

        plainText = sm4.decryptData_ECB(cipherText);
        System.out.println("明文: " + plainText);
        System.out.println("");

        System.out.println("CBC模式");
        sm4.iv = "UISwD9fW6cFh9SNS";
        cipherText = sm4.encryptData_CBC(plainText);
        System.out.println("密文: " + cipherText);
        System.out.println("");

        plainText = sm4.decryptData_CBC(cipherText);
        System.out.println("明文: " + plainText);*/

        String rootKey = "1231231230123123";
        SM4Utils sm4Utils = new SM4Utils(rootKey , "" ,false);
        String key = String.valueOf(sm4Utils.encryptData_ECB("1234567890123456"));

        //2. 通过用户身份认证密钥和SM4算法对用户证件类型和证件号码加密生成电子居民健康卡ID
        //TODO 生成的用户身份认证密钥(key)44位 , 而密钥要32位???
        SM4Utils sm4Utils2 = new SM4Utils( "",key  , "" ,false);
        String healthCardId = String.valueOf(sm4Utils2.encryptData_ECB("210103199508302222:2"));
        System.out.println("key的base64编码:" + healthCardId);
        System.out.println("密文:" + healthCardId);
        String info =String.valueOf( sm4Utils2.decryptData_ECB(healthCardId));
        System.out.println("明文:" + info);
    }
}

package com.yxj.practise.test.encode;


import com.yxj.practise.test.encode.SM2.SM2;
import com.yxj.practise.test.encode.SM3.SM3Digest;

import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.math.ec.ECPoint;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;

/**
 *
 *
 * @author: LT
 * @date: 2018/6/30
 */
public class CipherUtils {

    private int ct;
    private ECPoint p2;
    private SM3Digest sm3keybase;
    private SM3Digest sm3c3;
    private byte key[];
    private byte keyOff;

    public CipherUtils() {
        this.ct = 1;
        this.key = new byte[32];
        this.keyOff = 0;
    }

    private void Reset() {
        this.sm3keybase = new SM3Digest();
        this.sm3c3 = new SM3Digest();

        byte p[] = RadixUtil.byteConvert32Bytes(p2.getX().toBigInteger());
        this.sm3keybase.update(p, 0, p.length);
        this.sm3c3.update(p, 0, p.length);

        p = RadixUtil.byteConvert32Bytes(p2.getY().toBigInteger());
        this.sm3keybase.update(p, 0, p.length);
        this.ct = 1;
        NextKey();
    }

    private void NextKey() {
        SM3Digest sm3keycur = new SM3Digest(this.sm3keybase);
        sm3keycur.update((byte) (ct >> 24 & 0xff));
        sm3keycur.update((byte) (ct >> 16 & 0xff));
        sm3keycur.update((byte) (ct >> 8 & 0xff));
        sm3keycur.update((byte) (ct & 0xff));
        sm3keycur.doFinal(key, 0);
        this.keyOff = 0;
        this.ct++;
    }

    public ECPoint Init_enc(SM2 sm2, ECPoint userKey) {
        AsymmetricCipherKeyPair key = sm2.ecc_key_pair_generator.generateKeyPair();
        ECPrivateKeyParameters ecpriv = (ECPrivateKeyParameters) key.getPrivate();
        ECPublicKeyParameters ecpub = (ECPublicKeyParameters) key.getPublic();
        BigInteger k = ecpriv.getD();
        ECPoint c1 = ecpub.getQ();
        this.p2 = userKey.multiply(k);
        Reset();
        return c1;
    }

    public void Encrypt(byte data[]) {
        this.sm3c3.update(data, 0, data.length);
        for (int i = 0; i < data.length; i++) {
            if (keyOff == key.length) {
                NextKey();
            }
            data[i] ^= key[keyOff++];
        }
    }

    public void Init_dec(BigInteger userD, ECPoint c1) {
        this.p2 = c1.multiply(userD);
        Reset();
    }

    public void Decrypt(byte data[]) {
        for (int i = 0; i < data.length; i++) {
            if (keyOff == key.length) {
                NextKey();
            }
            data[i] ^= key[keyOff++];
        }

        this.sm3c3.update(data, 0, data.length);
    }

    public void Dofinal(byte c3[]) {
        byte p[] = RadixUtil.byteConvert32Bytes(p2.getY().toBigInteger());
        this.sm3c3.update(p, 0, p.length);
        this.sm3c3.doFinal(c3, 0);
        Reset();
    }


    /**
     * 将String转化为十六进制String
     *
     * @param source
     * @return
     */
    public static String stringToHexString(String source) {
        if (StringUtils.isEmpty(source)) {
            throw new IllegalArgumentException("The source string：'" + source + "' is invalid.");
        }

        String result = "";
        for (int i = 0; i < source.length(); i++) {
            int ch = (int) source.charAt(i);
            result = result + Integer.toHexString(ch);
        }

        return result;
    }


    /**
     * 中文字符转十六进制字符  可过滤空格
     *
     * @param cnSource
     * @return
     */
    public static String cnStringToHexString(String cnSource) {
        if (StringUtils.isEmpty(cnSource.trim())) {
            throw new IllegalArgumentException("The cnSource string：'" + cnSource + "' is invalid.");
        }

        try {
            String source = URLEncoder.encode(cnSource.trim(), "UTF-8");
            return source.replace("%", "");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("The Chine string：'" + cnSource + "' is unsupported encoding.");
        }
    }

    public static String sm3(String source) {
        if (StringUtils.isEmpty(source.trim())) {
            throw new IllegalArgumentException("The source string：'" + source + "' is invalid.");
        }

        return "";
    }


    public static void main(String[] args) {
        String idType = "01";
        String idNo = "11010020171225006X";
        String name = "电子健康卡";

        System.out.println("姓名：" + cnStringToHexString(name));
        System.out.println("最终结果：" + stringToHexString(idType + idNo) + cnStringToHexString(name));


    }
}

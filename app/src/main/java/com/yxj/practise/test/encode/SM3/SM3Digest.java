package com.yxj.practise.test.encode.SM3;

import com.yxj.practise.test.encode.RadixUtil;
import com.yxj.practise.test.encode.SM4.SM4;
import com.yxj.practise.test.encode.SM4.SM4_Context;

import java.math.BigInteger;

/**
 * @author: LT
 * @date: 2018/6/30
 */
public class SM3Digest {
    /** SM3值的长度 */
    private static final int BYTE_LENGTH = 32;

    /** SM3分组长度 */
    private static final int BLOCK_LENGTH = 64;

    /** 缓冲区长度 */
    private static final int BUFFER_LENGTH = BLOCK_LENGTH * 1;

    /** 缓冲区 */
    private byte[] xBuf = new byte[BUFFER_LENGTH];

    /** 缓冲区偏移量 */
    private int xBufOff;

    /** 初始向量 */
    private byte[] V = SM3.iv.clone();

    private int cntBlock = 0;

    public SM3Digest() {
    }

    public SM3Digest(SM3Digest t)
    {
        System.arraycopy(t.xBuf, 0, this.xBuf, 0, t.xBuf.length);
        this.xBufOff = t.xBufOff;
        System.arraycopy(t.V, 0, this.V, 0, t.V.length);
    }

    /**
     * SM3结果输出
     *
     * @param out 保存SM3结构的缓冲区
     * @param outOff 缓冲区偏移量
     * @return
     */
    public int doFinal(byte[] out, int outOff)
    {
        byte[] tmp = doFinal();
        System.arraycopy(tmp, 0, out, 0, tmp.length);
        return BYTE_LENGTH;
    }

    public void reset()
    {
        xBufOff = 0;
        cntBlock = 0;
        V = SM3.iv.clone();
    }

    /**
     * 明文输入
     *
     * @param in
     *            明文输入缓冲区
     * @param inOff
     *            缓冲区偏移量
     * @param len
     *            明文长度
     */
    public void update(byte[] in, int inOff, int len)
    {
        int partLen = BUFFER_LENGTH - xBufOff;
        int inputLen = len;
        int dPos = inOff;
        if (partLen < inputLen)
        {
            System.arraycopy(in, dPos, xBuf, xBufOff, partLen);
            inputLen -= partLen;
            dPos += partLen;
            doUpdate();
            while (inputLen > BUFFER_LENGTH)
            {
                System.arraycopy(in, dPos, xBuf, 0, BUFFER_LENGTH);
                inputLen -= BUFFER_LENGTH;
                dPos += BUFFER_LENGTH;
                doUpdate();
            }
        }

        System.arraycopy(in, dPos, xBuf, xBufOff, inputLen);
        xBufOff += inputLen;
    }

    private void doUpdate()
    {
        byte[] B = new byte[BLOCK_LENGTH];
        for (int i = 0; i < BUFFER_LENGTH; i += BLOCK_LENGTH)
        {
            System.arraycopy(xBuf, i, B, 0, B.length);
            doHash(B);
        }
        xBufOff = 0;
    }

    private void doHash(byte[] B)
    {
        byte[] tmp = SM3.CF(V, B);
        System.arraycopy(tmp, 0, V, 0, V.length);
        cntBlock++;
    }

    private byte[] doFinal()
    {
        byte[] B = new byte[BLOCK_LENGTH];
        byte[] buffer = new byte[xBufOff];
        System.arraycopy(xBuf, 0, buffer, 0, buffer.length);
        byte[] tmp = SM3.padding(buffer, cntBlock);
        for (int i = 0; i < tmp.length; i += BLOCK_LENGTH)
        {
            System.arraycopy(tmp, i, B, 0, B.length);
            doHash(B);
        }
        return V;
    }

    public void update(byte in)
    {
        byte[] buffer = new byte[] { in };
        update(buffer, 0, 1);
    }

    public int getDigestSize()
    {
        return BYTE_LENGTH;
    }

    public static String getSM3HexString(String data){
        byte[] md = new byte[32];
        byte[] msg1 = data.getBytes();
        SM3Digest sm3 = new SM3Digest();
        sm3.update(msg1, 0, msg1.length);
        sm3.doFinal(md, 0);
        return RadixUtil.getHexString(md);
    }

    public static void main(String[] args)
    {
        byte[] md = new byte[32];
        byte[] msg1 = "0111010020171225006X电子健康卡".getBytes();
        SM3Digest sm3 = new SM3Digest();
        sm3.update(msg1, 0, msg1.length);
        sm3.doFinal(md, 0);
        String s = RadixUtil.getHexString(md);
        System.out.println(s.toUpperCase());
        //主索引id
        String id = s.toUpperCase();
        BigInteger ioxold =new BigInteger("ABD17E7ED399EF68", 16);
        for( int i = 0 ; i< id.length() ; i=i+16 ){
            String sox = id.substring(i, i+16);
            System.out.println(sox);
            BigInteger iox = new BigInteger(sox,16);
            if( i != 0 ) {
                ioxold = ioxold.xor(iox);
            }
        }
        //分散因子
        String fsyz = ioxold.toString(16).toUpperCase();
        System.out.println(fsyz);
        byte[] _A = RadixUtil.hexStringToBytes(fsyz);
        byte[] _B=new byte[_A.length ];
        for(int i=0;i<_A.length ;i++)
        {
            _B[i]=(byte)~_A[i];
        }
        //分散因子取反
        String fsyz2 = null;
        try {
            fsyz2 = RadixUtil.getHexString(_B);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(fsyz2);
        System.out.println(fsyz+fsyz2);
        //安全密钥
        String rootKey = "0123456789ABCDEFFEDCBA9876543210";
        SM4 sm4 = new SM4();
        SM4_Context ctx = new SM4_Context();
        ctx.isPadding = true;
        ctx.mode = SM4.SM4_ENCRYPT;
        try {
            sm4.sm4_setkey_enc(ctx, RadixUtil.hexStringToBytes(rootKey));
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] encrypted = new byte[0];
        try {
            encrypted = sm4.sm4_crypt_ecb(ctx, RadixUtil.hexStringToBytes(fsyz+fsyz2));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //用户身份认证密钥
        String usersecret = RadixUtil.getHexString(encrypted);
        System.out.println(usersecret);

        //生成 电子健康卡ID
        SM4 sm41 = new SM4();
        SM4_Context ctx1 = new SM4_Context();
        ctx1.isPadding = true;
        ctx1.mode = SM4.SM4_ENCRYPT;
        try {
            sm41.sm4_setkey_enc(ctx1, RadixUtil.hexStringToBytes(usersecret));
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] encrypted1 = new byte[0];
        try {
            encrypted1 = sm41.sm4_crypt_ecb(ctx1, RadixUtil.hexStringToBytes("3031313130313030323031373132323530303658000000000000000000000000"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(RadixUtil.getHexString(encrypted1));


        //时效性
        SM4 sm42 = new SM4();
        SM4_Context ctx2 = new SM4_Context();
        ctx2.isPadding = true;
        ctx2.mode = SM4.SM4_ENCRYPT;
        try {
            sm42.sm4_setkey_enc(ctx2, RadixUtil.hexStringToBytes("11223344556677888877665544332211"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] encrypted2 = new byte[0];
        try {
            encrypted2= sm42.sm4_crypt_ecb(ctx2, RadixUtil.hexStringToBytes("20180117115003"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(RadixUtil.getHexString(encrypted2));
    }


}
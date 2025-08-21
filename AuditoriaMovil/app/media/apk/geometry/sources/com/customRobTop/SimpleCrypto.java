package com.customRobTop;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
/* loaded from: classes.dex */
public class SimpleCrypto {
    private static final String HEX = "0123456789ABCDEF";

    private static void appendHex(StringBuffer stringBuffer, byte b) {
        stringBuffer.append(HEX.charAt((b >> 4) & 15)).append(HEX.charAt(b & 15));
    }

    public static String decrypt(String str, byte[] bArr) throws Exception {
        byte[] decrypt = decrypt(getRawKey(str.getBytes()), bArr);
        return decrypt != null ? new String(decrypt) : "";
    }

    private static byte[] decrypt(byte[] bArr, byte[] bArr2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES/ECB/PKCS5Padding");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(2, secretKeySpec);
            return cipher.doFinal(bArr2);
        } catch (Exception unused) {
            return null;
        }
    }

    private static String eK() {
        return "y27vyZlpIJk2C8wd";
    }

    public static byte[] encrypt(String str, String str2) throws Exception {
        return encrypt(getRawKey(str.getBytes()), str2.getBytes());
    }

    private static byte[] encrypt(byte[] bArr, byte[] bArr2) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES/ECB/PKCS5Padding");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(1, secretKeySpec);
        return cipher.doFinal(bArr2);
    }

    public static String fromHex(String str) {
        return new String(toByte(str));
    }

    private static byte[] getRawKey(byte[] bArr) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(bArr);
        keyGenerator.init(128, secureRandom);
        return keyGenerator.generateKey().getEncoded();
    }

    public static String loadAndDecryptFileToString(String str) throws Exception {
        File file = new File(str);
        if (!file.exists()) {
            return "";
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = fileInputStream.read(bArr);
            if (read == -1) {
                String decrypt = decrypt(eK(), byteArrayOutputStream.toByteArray());
                fileInputStream.close();
                return decrypt;
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

    public static void saveAndEncryptStringToFile(String str, String str2) throws Exception {
        byte[] encrypt = encrypt(eK(), str);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str2));
        bufferedOutputStream.write(encrypt);
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
    }

    public static byte[] toByte(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = Integer.valueOf(str.substring(i2, i2 + 2), 16).byteValue();
        }
        return bArr;
    }

    public static String toHex(String str) {
        return toHex(str.getBytes());
    }

    public static String toHex(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (byte b : bArr) {
            appendHex(stringBuffer, b);
        }
        return stringBuffer.toString();
    }
}

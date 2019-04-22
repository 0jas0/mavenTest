package com.jas.test;

public class RC4Util {

    public static String decry_RC4(String data, String key) {
        if (data == null || key == null) {
            return null;
        }
        return new String(RC4Base(HexString2Bytes(data), key));
    }


    private static int[] initKey(String aKey) {
        int[] s = new int[256];
        for (int i = 0; i < 256; i++){
            s[i] = i;
        }
        int j = 0;
        for (int i = 0; i< 256; i++){
            j = (j + s[i] + aKey.charAt(i % aKey.length())) % 256;
            int x = s[i];
            s[i] = s[j];
            s[j] = x;
        }
        return s;
    }

    private static byte[] HexString2Bytes(String src) {
        int size = src.length();
        byte[] ret = new byte[size / 2];
        byte[] tmp = src.getBytes();
        for (int i = 0; i < size / 2; i++) {
            ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
        }
        return ret;
    }

    private static byte uniteBytes(byte src0, byte src1) {
        char _b0 = (char)Byte.decode("0x" + new String(new byte[] { src0 }))
                .byteValue();
        _b0 = (char) (_b0 << 4);
        char _b1 = (char)Byte.decode("0x" + new String(new byte[] { src1 }))
                .byteValue();
        byte ret = (byte) (_b0 ^ _b1);
        return ret;
    }

    private static byte[] RC4Base (byte [] input, String mKkey) {
        int x = 0;
        int y = 0;
        int key[] = initKey(mKkey);
        byte[] result = new byte[input.length];
        for (int i = 0; i < input.length; i++) {
            x  = (x + 1) % 256;
            y = (y + key[x]) % 256;
            int z = key[x];
            key[x] = key[y];
            key[y] = z;
            int charAt = input[i];
            int index = key[(key[x] + key[y]) % 256];
            int aa = charAt ^ index;
            result[i] = (byte) aa;
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "9c5ef9d470de4342";
        String tS = decry_RC4(s, "kI49uyKIWBl9ohZAADPr9MCWyXBTczD0");
        System.out.println(tS);
    }
}
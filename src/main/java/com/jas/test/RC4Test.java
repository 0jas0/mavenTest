package com.jas.test;

/**
 * <一句话描述>,
 * <详细介绍>,
 *
 * @author 琚安生
 * @since 设计wiki | 需求wiki
 */
public class RC4Test {
    public static String encrypt(String password, String key){
        int[] s = new int[256];
        char[] chars = new char[password.length()];
        for (int i = 0; i < 256; i++){
            s[i] = i;
        }
        int j = 0;
        for (int i = 0; i< 256; i++){
            j = (j + s[i] + key.charAt(i % key.length())) % 256;
            int x = s[i];
            s[i] = s[j];
            s[j] = x;
        }
        int i = 0;
        j = 0;
        for (int k = 0; k < password.length(); k++){
            i  = (i + 1) % 256;
            j = (j + s[i]) % 256;
            int x = s[i];
            s[i] = s[j];
            s[j] = x;
            int charAt = password.charAt(k);
            int index = s[(s[i] + s[j]) % 256];
            int aa = charAt ^ index;
            chars[k] = (char) aa;
        }
        StringBuffer res = new StringBuffer();
        for (int k = 0; k < chars.length; k++){
            int charAt = chars[k];
            if (charAt < 16){
                res.append("0");
            }
            res.append(Integer.toHexString(charAt));
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String encrypt = encrypt("asdf1234", "RHskCZjwouw7Jr3E9IBJ0eyLFm0lHN5t");
        System.out.println(encrypt);
    }

}

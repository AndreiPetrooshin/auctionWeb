package com.petrushin.service.encode;

import com.petrushin.service.exception.MD5EncodingServiceException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5EncodingService {

    private static final String MD_5 = "MD5";

    public static String encode(String password) throws MD5EncodingServiceException {

        if(password== null){
            return null;
        }
        MessageDigest md ;
        String result;
        try {
            md = MessageDigest.getInstance(MD_5);
            md.update(password.getBytes());
            byte byteData[] = md.digest();

            StringBuilder hexString = new StringBuilder();
            for (byte aByteData : byteData) {
                String hex = Integer.toHexString(0xff & aByteData);
                int length = hex.length();
                if (length == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            result = hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new MD5EncodingServiceException(e.getMessage());
        }
        return result;
    }


}

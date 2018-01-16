package com.petrushin.epam.auction.services.encode;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class for encode password to MD5 representation
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class MD5EncodingService {

    private static final String MD_5 = "MD5";
    private static final int HEX_MAX = 0xff;
    private static final char ZERO_CHAR = '0';


    public static String encode(String password) {

        if (password == null) {
            return null;
        }
        MessageDigest md;
        try {
            md = MessageDigest.getInstance(MD_5);
        } catch (NoSuchAlgorithmException e) {
            throw new ClassCastException(e.getMessage());
        }
        md.update(password.getBytes());
        byte byteData[] = md.digest();
        StringBuilder hexString = new StringBuilder();
        for (byte aByteData : byteData) {
            String hex = Integer.toHexString(HEX_MAX & aByteData);
            int length = hex.length();
            if (length == 1) {
                hexString.append(ZERO_CHAR);
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}

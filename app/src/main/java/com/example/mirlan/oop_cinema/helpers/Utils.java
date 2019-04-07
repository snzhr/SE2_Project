package com.example.mirlan.oop_cinema.helpers;

import android.content.res.Resources;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */

public class Utils {

    public static String format(String name){
        return "[" + name + "]";
    }

    public static boolean hasSpecialChars(String s) {
        Pattern p = Pattern.compile("[^a-z0-9_ ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(s);
        return m.find() && s.contains(" ");
    }

    public static int convertDPtoPX(Resources resources, int dp) {
        // Get the screen's density scale
        float scale = resources.getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (dp * scale + 0.5f);
    }

    public static boolean isOk(String[] s) {
        boolean res = true;
        if (s.length == 0)
            res = false;
        for (String value : s) {
            if (value == null || value.trim().isEmpty()) {
                res = false;
                break;
            }
        }
        return res;
    }

    /**
     * format validation
     *
     * This class encodes/decodes hexadecimal data
     *
     * @xerces.internal
     *
     * @author Jeffrey Rodriguez
     * */
    static final private char[] lookUpHexAlphabet = new char[16];

    static {
        for (int i = 0; i < 10; i++) {
            lookUpHexAlphabet[i] = (char) ('0' + i);
        }
        for (int i = 10; i <= 15; i++) {
            lookUpHexAlphabet[i] = (char) ('A' + i - 10);
        }
    }

    public static String getSHA256Hash(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes("UTF-8"));
            return bytesToHex(hash); // make it printable
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Encode a byte array to hex string
     *
     * @param binaryData array of byte to encode
     * @return return encoded string
     */
    private static String bytesToHex(byte[] binaryData) {
        if (binaryData == null)
            return null;
        int lengthData = binaryData.length;
        int lengthEncode = lengthData * 2;
        char[] encodedData = new char[lengthEncode];
        int temp;
        for (int i = 0; i < lengthData; i++) {
            temp = binaryData[i];
            if (temp < 0)
                temp += 256;
            encodedData[i * 2] = lookUpHexAlphabet[temp >> 4];
            encodedData[i * 2 + 1] = lookUpHexAlphabet[temp & 0xf];
        }
        return new String(encodedData);
    }

}

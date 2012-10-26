package ru.lighttms.tms.utils;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.apache.commons.codec.binary.Hex;

/**
 * Created by IntelliJ IDEA.
 * User: azee
 * Date: 7/9/12
 * Time: 3:40 PM
 */

public class StringUtils {

    public static String emptyIfNull(String input){
        if (input == null) {
            return "";
        }
        else return input;
    }

    public static String listAsString(List<String> stringList){
        String result = "";
        for (String oneString : stringList){
            result = result + oneString + ";";
        }

        //Removing last ';'
        if (result.endsWith(";"))
        {
          result = result.substring(0, result.length() - 1);
        }
        return result;
    }

    public static String getMd5String(String input) throws NoSuchAlgorithmException {
        final MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.reset();
        messageDigest.update(input.getBytes(Charset.forName("UTF8")));
        final byte[] resultByte = messageDigest.digest();
        final String result = Hex.encodeHexString(resultByte);
        return result;
    }
}

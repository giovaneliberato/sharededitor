package utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Security {

    public static String cryptMD5(String str){
        byte[] bytesOfMessage = null;
        try {
            try {
                bytesOfMessage = str.getBytes("UTF-8");
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Security.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            MessageDigest md = MessageDigest.getInstance("MD5");
            return md.digest(bytesOfMessage).toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Security.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

class Crypto {
    private static char[] HEX_CHARS = "0123456789ABCDEF".toCharArray();
    private static final String UTF_8 = "UTF-8";
    private static final String HMAC_SHA1 = "HmacSHA1";

    /**
     This is a method to encode in hexadecimal format.
     **/
    static char[] encodeToHex(byte[] toEncode) {
        Integer len =  toEncode.length;
        char[] hexChars = new char[len * 2];
        int charIndex = 0;
        int startIndex = 0;
        while (charIndex < hexChars.length) {
            int bite = toEncode[startIndex] & 0xff;
            startIndex ++;
            hexChars[charIndex] = HEX_CHARS[bite >> 4];
            charIndex ++;
            hexChars[charIndex] = HEX_CHARS[bite & 0xf];
            charIndex ++;
        }
        return hexChars;
    }

    /**
     This is a method to encode in base 64.
     **/
    static String encodeBase64(String s) throws UnsupportedEncodingException {
        Base64.Encoder e = Base64.getEncoder();
        byte[] b_array = e.encode(s.getBytes(UTF_8));
        return new String(b_array, UTF_8);
    }

    static byte[] sign(byte[] toSign, byte[] secret) throws NoSuchAlgorithmException, InvalidKeyException {
        if(toSign.length == 0 || secret.length == 0) {
            return new byte[0];
        }
        else {
            Mac mac = Mac.getInstance(HMAC_SHA1);
            SecretKeySpec signingKey = new SecretKeySpec(secret, HMAC_SHA1);
            mac.init(signingKey);
            return mac.doFinal(toSign);
        }
    }
}

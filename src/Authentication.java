import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Authentication {
    static String buildAuthorizationHeader(String date, String apiKey, String apiSecret) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException {

        String toSign = apiSecret + apiKey + date;
        byte[] messageBytes = toSign.getBytes("UTF-8");
        byte[] secretBytes = apiSecret.getBytes("UTF-8");
        byte[] result = Crypto.sign(messageBytes, secretBytes);
        char[] hexChars = Crypto.encodeToHex(result);
        String sign = new String(hexChars).toLowerCase();
        String signature = Crypto.encodeBase64(sign);
        return "PTP:" + apiKey + ":" + signature;
    }
}
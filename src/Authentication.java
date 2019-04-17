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

    public static void main(String[] args) {
        try {
            System.out.println(buildAuthorizationHeader("2019-04-16T11:19:33Z", "ce657875-613a-4636-ad53-a2f127f7811e", "1c61cb5a-294e-4085-845a-282dc479990d"));
            // The result is "PTP:ce657875-613a-4636-ad53-a2f127f7811e:ZDhhZWMyZDU0NzRmMjBhMWUxYzY2OGFmZWJlOGE4ZmZiNWM2MjZiMQ=="

        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
package pos.core.util;

import org.apache.commons.codec.digest.DigestUtils;

public class CryptoUtil {

    public static String hash(String value) {
        try {
            return DigestUtils.sha1Hex(value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

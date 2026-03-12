import java.nio.charset.StandardCharsets;

public class HashFunction {

    public static long hash(String value) {
        byte[] data = value.getBytes(StandardCharsets.UTF_8);

        long hash = 1125899906842597L;

        for (byte b : data) {
            hash = 31 * hash + b;
        }

        return hash;
    }
}

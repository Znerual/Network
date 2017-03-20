/**
 * Created by laurenz on 25.01.17.
 */
public class ConnectionProtocol {
    public static String encode(String data) {
        return data + "\n";
    }
    public static String decode(String data) {
        return data;
    }
}

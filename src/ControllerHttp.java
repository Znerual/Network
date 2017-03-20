import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by laurenz on 06.02.17.
 */
public class ControllerHttp {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://127.0.0.1:2222");
            HttpRequest request = new HttpRequest();
            System.out.println(request.sendGet(url, null));
            System.out.println(request.sendPost(url, null, ""));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

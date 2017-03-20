/**
 * Created by laurenz on 25.01.17.
 */
public class ControllerServer {
    public static void main(String[] args) {
        try {
            Server server = new Server(2222);

        } catch (ConnectionException e) {
            e.printStackTrace();
        }

    }
}

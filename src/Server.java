import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * Created by laurenz on 25.01.17.
 */
//A client closese Event could be handy
public class Server {
    private int portNumber;
    private boolean serverIsRunning = true;
    public Server(int portNumber) throws ConnectionException {
        this.portNumber = portNumber;
        try (
                ServerSocket serverSocket = new ServerSocket(portNumber);
        )
        {
            System.out.println("Server started at " + new Date().getTime());
            while (serverIsRunning) {
                ServerThread thread = new ServerThread(serverSocket.accept());
                System.out.println("New Client connected");
                thread.start();
            }
        } catch (IOException exc) {
            throw  new ConnectionException("Fehler beim Verbinden! Portnummer bitte überprüfen");
        }
    }
}

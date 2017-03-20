import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by laurenz on 25.01.17.
 */
public class ServerThread extends Thread {
    PrintWriter out;
    BufferedReader in;
    Socket clientSocket;
    volatile boolean threadAlive = true;
    volatile String errorMessage = "";
    public ServerThread(Socket clientSocket) {
        this.clientSocket = clientSocket;

    }
    @Override
    public void run() {
        try (
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {
            System.out.println("Thread gestartet");
            out.write(ConnectionProtocol.encode("Wilcommen Client, Sie sind verbunden mit mir "));
            out.flush();
            while(threadAlive) {
                String cmd;
                while ((cmd = in.readLine()) != null) {
                    System.out.println("Received a command " +  cmd);
                    if (cmd.equalsIgnoreCase("quit")) break;
                    out.write(ConnectionProtocol.encode(cmd));
                    out.flush();
                    System.out.println("Send a command back");
                }
            }
        } catch (IOException exc) {
            errorMessage = "Fehler im Server Thread " + clientSocket.getPort();
        }
    }
}

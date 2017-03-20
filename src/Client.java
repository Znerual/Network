import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Created by laurenz on 25.01.17.
 */
public class Client {
    Socket socket;
    PrintWriter out;
    BufferedReader in;
    private boolean clientAlive = true;
    public Client(int port) {
        try (
            Socket socket = new Socket(InetAddress.getLocalHost(), port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            )
        {
            System.out.println("Client gestartet");
            while (clientAlive) {
                String cmd = in.readLine();
                System.out.println(ConnectionProtocol.decode(cmd));

                Scanner input = new Scanner(System.in);
                String output = input.nextLine();
                if (output.equalsIgnoreCase("q")) clientAlive = false;
                System.out.println("Send data");
                out.write(ConnectionProtocol.encode(output));
                out.flush();


            }


        } catch (IOException ex) {

        }


    }
}

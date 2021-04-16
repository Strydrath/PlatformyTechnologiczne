
import java.io.IOException;
import java.net.ServerSocket;

public class Main {

    private static ServerSocket serverSocket;

    public static void main(String[] args) throws IOException {
        // otwarcie portu 6666
        serverSocket = new ServerSocket(9797);
        while(true) {
            new Server(serverSocket.accept()).start();
        }
    }
    public void stop() throws IOException {
        serverSocket.close();
    }

}
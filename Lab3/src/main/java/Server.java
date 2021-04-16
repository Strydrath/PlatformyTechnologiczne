import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Server extends Thread{
    private ServerSocket serverSocket;
    private Socket clientSocket;
    public static ObjectOutputStream out;
    private ObjectInputStream in;
    public Server() {

    }

    public Server(Socket socket) {
        this.clientSocket = socket;
    }


    public void run() {

        try {
            out = new ObjectOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream inputToServer = null;
        try {
            in = new ObjectInputStream(clientSocket.getInputStream());
            inputToServer = clientSocket.getInputStream();
            String inputLine;
            Scanner scanner = new Scanner(inputToServer, "UTF-8");
            String tmp = (String)in.readObject();
            out.writeObject("ready");
            boolean done=false;
            while(!done) {
                int n = (int)in.readObject();
                out.writeObject("ready for messages");
                for (int i = 0; i < n; i++){
                    Message msg = ((Message)in.readObject());
                    System.out.println(msg.getNumber());
                    System.out.println(msg.getContent());
                }
                out.writeObject("finish");
                done=true;

            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}

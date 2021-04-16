import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

class Client {
    private static String userName;
    public static Socket clientSocket;
    public static ObjectOutputStream out;
    public static ObjectInputStream in;


    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new ObjectOutputStream(clientSocket.getOutputStream());
        in = new ObjectInputStream(clientSocket.getInputStream());
    }

    public static String sendNumber(int n) throws IOException, ClassNotFoundException {
        String m = "";
        m+=n;
        out.writeObject(n);
        String resp = (String)in.readObject();
        System.out.println(resp);
        return resp;
    }
    public static void  sendMessage(String c, int n) throws IOException {
        Message m = new Message();
        m.setContent(c);
        m.setNumber(n);
        out.writeObject(m);
    }


    public static void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

    public static String start( ) throws IOException, ClassNotFoundException {
        Client client = new Client();
        client.startConnection("127.0.0.1", 9797);
        out.writeObject("pls");
        String resp = (String)in.readObject();
        System.out.println(resp);
        return resp;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> lista = new ArrayList<String>();
        int n;
        String message;
        boolean exit=false;
        do {

            System.out.println("press 1 to send messages: ");
            n = sc.nextInt();
            if(n==1) {
                if(start().equals("ready")) {
                    System.out.println("how many messages?: ");
                    n = sc.nextInt();
                    String a = sendNumber(n);
                    String tmp = sc.nextLine();
                    if (a.equals("ready for messages")) {
                        for (int i = 0; i < n; i++) {
                            System.out.println("Chose your message: ");
                            message = sc.nextLine();
                            lista.add(message);
                        }
                        for (int i = 0; i < n; i++) {
                            sendMessage(lista.get(i), i);
                        }
                        String resp = (String)in.readObject();
                        System.out.print(resp);
                    }
                    stopConnection();
                }
            }


        }while(!exit);
    }

}
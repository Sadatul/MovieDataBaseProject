package Client;
import util.MovieNotFoundException;
import util.SocketWrapper;

import java.util.Scanner;

public class Client {
    private SocketWrapper socketWrapper;
    private String name;

    private boolean gotClient;
    public Client()
    {
        gotClient = false;
    }
    public SocketWrapper getSocketWrapper() {
        return socketWrapper;
    }

    public void setSocketWrapper(SocketWrapper socketWrapper) {
        this.socketWrapper = socketWrapper;
    }

    public boolean isGotClient() {
        return gotClient;
    }

    public void setGotClient(boolean gotClient) {
        this.gotClient = gotClient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //
//    public Client(String serverAddress, int serverPort, String clientName) {
//        try {
////            boolean tmp = true;
////            while(tmp) {
////            System.out.print("Enter name of the client: ");
////                Scanner scanner = new Scanner(System.in);
////                String clientName = scanner.nextLine();
//            SocketWrapper socketWrapper = new SocketWrapper(serverAddress, serverPort);
//            socketWrapper.write(clientName);
//            Object s = socketWrapper.read();
//            if (!(s instanceof MovieNotFoundException)) {
////                System.out.println(s);
////                    continue;
//                new ReadThread(socketWrapper);
//                new WriteThreadClient(socketWrapper, clientName);
//            }
////            new ReadThread(socketWrapper);
////            new WriteThreadClient(socketWrapper, clientName);
////            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
    }

//    public static void main(String args[]) {
//        String serverAddress = "127.0.0.1";
//        int serverPort = 33333;
//        new Client(serverAddress, serverPort);
//    }



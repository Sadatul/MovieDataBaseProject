package Client;
import util.SocketWrapper;

import java.io.IOException;
import java.util.Scanner;

public class WriteThreadClient implements Runnable {

    Thread thr;
    private SocketWrapper socketWrapper;
    String name;

    public WriteThreadClient(SocketWrapper socketWrapper, String name) {
        this.socketWrapper = socketWrapper;
        this.name = name;
        this.thr = new Thread(this);
        thr.start();
    }
    public void run() {
        try {
            Scanner input = new Scanner(System.in);
            while (true) {
                String s = input.nextLine();
                int i = Integer.parseInt(s);
                if(i > 0 && i <= 5)
                {
                    socketWrapper.write(s);
                }
                if(i == 5)
                {
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                socketWrapper.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}




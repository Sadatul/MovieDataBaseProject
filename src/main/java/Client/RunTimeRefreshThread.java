package Client;

import util.SocketWrapper;

import java.io.IOException;

public class RunTimeRefreshThread implements Runnable{
    private Thread thr;
    private SocketWrapper socketWrapper;
    Object classObject;
    public RunTimeRefreshThread(SocketWrapper socketWrapper, Object classObject) {
        this.socketWrapper = socketWrapper;
        this.classObject = classObject;
        this.thr = new Thread(this);
        thr.start();
    }

    @Override
    public void run() {
        try {
            while (true)
            {
                Object s = socketWrapper.read();
                if(s instanceof String)
                {
                    String tmp = (String) s;

                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

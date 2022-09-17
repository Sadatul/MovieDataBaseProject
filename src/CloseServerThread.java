import util.SocketWrapper;
import java.net.ServerSocket;
import java.util.HashMap;


class CloseServerThread implements Runnable{

    private Thread t;
    private ServerSocket serverSocket;
    private MovieDataSet movieDataSet;
    private HashMap<String, SocketWrapper> clientMap;
    public CloseServerThread(HashMap<String, SocketWrapper> clientMap, ServerSocket serverSocket, MovieDataSet movieDataSet)
    {
        this.serverSocket = serverSocket;
        this.movieDataSet = movieDataSet;
        this.clientMap = clientMap;
    }
    @Override
    public void run() {
        try {
            movieDataSet.close();
            System.out.println("MovieDataSet Closed.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
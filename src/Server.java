import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import util.MovieNotFoundException;
import util.SocketWrapper;

public class Server {

    private ServerSocket serverSocket;
    public HashMap<String, SocketWrapper> clientMap; // HashMap of client's name and socket information
    public  MovieDataSet movieDataSet;
    Server() {
        try {
            movieDataSet = new MovieDataSet("movies.txt");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        clientMap = new HashMap<>();
        try {
            serverSocket = new ServerSocket(33333);
            Runnable r = new CloseServerThread(clientMap, serverSocket, movieDataSet);
            Thread t = new Thread(r);
            Runtime.getRuntime().addShutdownHook(t);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }

    public void exit()
    {
        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            movieDataSet.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void serve(Socket clientSocket) throws IOException, ClassNotFoundException {
        SocketWrapper socketWrapper = new SocketWrapper(clientSocket);
        String clientName = (String) socketWrapper.read();
        String tmp = movieDataSet.searchExactCompanyName(clientName);
        if(tmp == null)
        {
            socketWrapper.write(new MovieNotFoundException());
            socketWrapper.closeConnection();
            return;
        }
        else if(clientMap.containsKey(tmp))
        {
            socketWrapper.write("#LOGGEDIN#");
            socketWrapper.closeConnection();
            return;
        }
        else
        {
            socketWrapper.write(tmp);
        }
        clientMap.put(tmp, socketWrapper);
        System.out.println(clientMap.size());
        new WriteThreadServer(clientMap, tmp, socketWrapper, movieDataSet);
    }

    public static void main(String args[]) {
        Server s = new Server();

    }
}

package Client;
import com.example.main_project.Movie;
import com.example.main_project.RecentMovies;
import javafx.application.Platform;
import util.SocketWrapper;

import java.util.ArrayList;
import java.util.Map;

public class ReadThread implements Runnable {
    private Thread thr;
    private SocketWrapper socketWrapper;
    private Object data;

    private RecentMovies releaseYearFxml;
    public ReadThread(SocketWrapper socketWrapper, Object data, RecentMovies releaseYearFxml) {
        this.socketWrapper = socketWrapper;
        this.data = data;
        this.releaseYearFxml = releaseYearFxml;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
//            while (true) {
            Object s = socketWrapper.read();
            if (s instanceof ArrayList) {
                ArrayList<Movie> movies = (ArrayList<Movie>) data;
                movies.addAll((ArrayList<Movie>) s);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        releaseYearFxml.print();
                    }
                });

            }

            else if(s instanceof Long)
            {
                long value = (long) s;
                long[] arr = (long[]) data;
                arr[0] = value;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        releaseYearFxml.print();
                    }
                });
            }
            else if (s instanceof Map) {
                Map<String, Integer> objectMap = (Map<String, Integer>) s;
                Map<String, Integer> map = (Map<String, Integer>) data;
                for(Map.Entry<String, Integer> entry: objectMap.entrySet())
                {
                    map.put(entry.getKey(), entry.getValue());
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        releaseYearFxml.print();
                    }
                });

            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {

        }
    }
}




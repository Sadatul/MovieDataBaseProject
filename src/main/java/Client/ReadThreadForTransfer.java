package Client;

import com.example.main_project.*;
import javafx.application.Platform;
import util.SocketWrapper;

import java.io.IOException;
import java.util.ArrayList;

public class ReadThreadForTransfer implements Runnable{
    private Thread thr;
    private SocketWrapper socketWrapper;
    private Object data;

    private Object classObject;
    public ReadThreadForTransfer(SocketWrapper socketWrapper, Object data, Object classObject) {
        this.socketWrapper = socketWrapper;
        this.data = data;
        this.classObject = classObject;
        this.thr = new Thread(this);
        thr.start();
    }

    @Override
    public void run() {
        try {
            Object s = socketWrapper.read();
            if(classObject instanceof TransferWindow)
            {
                TransferWindow tWindow = (TransferWindow) classObject;
                if(data instanceof Movie)
                {
                    if(s == null)
                    {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                tWindow.noMovieFound();
                            }
                        });
                    }
                    else
                    {  System.out.println("1");
                       socketWrapper.write("$$" + tWindow.returnCompanyName());
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                tWindow.launchCompanyReadThread();
                            }
                        });
                    }
                }
                else if (data instanceof ArrayList) {
                    ArrayList<Movie> movies = (ArrayList<Movie>) data;
                    movies.addAll((ArrayList<Movie>) s);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("3");
                            tWindow.companyCheck();
                        }
                    });
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

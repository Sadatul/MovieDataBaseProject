package Client;
import com.example.main_project.*;
import javafx.application.Platform;
import util.SocketWrapper;

import java.util.ArrayList;
import java.util.Map;

public class ReadThread implements Runnable {
    public Thread thr;
    private SocketWrapper socketWrapper;
    private Object data;

    private Object releaseYearFxml;

    private MainMenuController mainMenuController;
    public ReadThread(SocketWrapper socketWrapper, Object data, Object releaseYearFxml, MainMenuController mainMenuController) {
        this.socketWrapper = socketWrapper;
        this.data = data;
        this.releaseYearFxml = releaseYearFxml;
        this.mainMenuController = mainMenuController;
        this.thr = new Thread(this);
        thr.start();
    }
    public void setData(Object o)
    {
//        System.out.println("Setted Data");
        data = o;
    }
    public void setReleaseYearFxml(Object releaseYearFxml)
    {
//        System.out.println("Setted Class");
        this.releaseYearFxml = releaseYearFxml;
    }
    public void run() {
        try {
            while (true) {
//                System.out.println("YES");
                Object s = socketWrapper.read();
//                System.out.println("YES");
                if(releaseYearFxml instanceof TransferWindow) {
                    TransferWindow tWindow = (TransferWindow) releaseYearFxml;
                    if (s == null) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                tWindow.noMovieFound();
                            }
                        });
                    }
                    else if (s instanceof String) {
                        System.out.println("YES");
                        String tmp = (String) s;
                        if (tmp.equals("#NOTALLOWEDTOTRANSFER#")) {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    tWindow.movieTransferNotAllowed();
                                }
                            });
                        }
                        else if (tmp.equals(("#NOCOMPANYFOUND#"))) {
                            System.out.println("Company Not Found");
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    tWindow.companyCheck();
                                }
                            });
                        }

                        else if (tmp.equals(("#MOVIEEXIST#"))) {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    tWindow.movieExists();
                                }
                            });
                        }
                        else if(tmp.equals("#TRANSFERRED#"))
                        {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    tWindow.transferComplete();
                                    mainMenuController.refresh();
                                }
                            });
                        }


                    }
                }
                else if(releaseYearFxml instanceof DashBoard)
                {
                    String str = (String) s;
                    if(str.charAt(0) == 'D' && str.charAt(1) == 'S')
                    {
                        String newStr = str.substring(2);
                        String[] arr = (String[]) data;
                        arr[0] = newStr;

                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                ((DashBoard)releaseYearFxml).setData();
                                ((DashBoard)releaseYearFxml).print();
                            }
                        });
                    }
                    else if(((String)s).equals("#MOVIETRANSFERRED#"))
                    {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
//                                tWindow.transferComplete();
                                mainMenuController.refresh();
                            }
                        });
                    }
                }
                else if (s instanceof ArrayList) {
                    ArrayList<Movie> movies = (ArrayList<Movie>) data;
                    movies.addAll((ArrayList<Movie>) s);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            ((RecentMovies)releaseYearFxml).print();
                        }
                    });

                } else if (s instanceof Long) {
                    long value = (long) s;
                    long[] arr = (long[]) data;
                    arr[0] = value;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            ((RecentMovies)releaseYearFxml).print();
                        }
                    });
                } else if (s instanceof Map) {
                    Map<String, Integer> objectMap = (Map<String, Integer>) s;
                    Map<String, Integer> map = (Map<String, Integer>) data;
                    for (Map.Entry<String, Integer> entry : objectMap.entrySet()) {
                        map.put(entry.getKey(), entry.getValue());
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            ((RecentMovies)releaseYearFxml).print();
                        }
                    });
                } else if (releaseYearFxml instanceof AddMovie) {
                    if (s == null) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                ((AddMovie) releaseYearFxml).checkComplete();
                            }
                        });

                    } else if (s instanceof String) {
                        if (((String) s).equals("##DONE")) {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    ((AddMovie) releaseYearFxml).showMovie();
                                }
                            });
                        }
                    } else {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                ((AddMovie) releaseYearFxml).setMovie((Movie) s);
                                ((AddMovie) releaseYearFxml).movieAlreadyIn();
                            }
                        });
                    }
                }
                else if(s instanceof String)
                {
                    if(((String)s).equals("#MOVIETRANSFERRED#"))
                    {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
//                                tWindow.transferComplete();
                                mainMenuController.refresh();
                            }
                        });
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {

        }
    }
}





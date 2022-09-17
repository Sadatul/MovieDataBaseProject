import java.util.*;

import com.example.main_project.Movie;
import util.SocketWrapper;

public class WriteThreadServer implements Runnable {

    private Thread thr;
    String name;
    public HashMap<String, SocketWrapper> clientMap;
    MovieDataSet movieDataSet;

    SocketWrapper clientSocket;
    public WriteThreadServer(HashMap<String, SocketWrapper> map, String name, SocketWrapper clientSocket, MovieDataSet movieDataSet) {
        this.movieDataSet = movieDataSet;
        this.clientSocket = clientSocket;
        this.clientMap = map;
        this.name = name;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                String option = (String) clientSocket.read();
                if(option.equals("1"))
                {
                    List<Movie> movieList = movieDataSet.recentMoviesByCompany(name);
                    clientSocket.write(movieList);
                }
                else if (option.equals("2"))
                {
                    List<Movie> movieList = movieDataSet.maximumRevenueByCompany(name);
                    clientSocket.write(movieList);
                }
                else if (option.equals("3"))
                {
                    long totalProfit = movieDataSet.totalProfitByCompany(name);
                    clientSocket.write(totalProfit);
                }
                else if (option.equals("4"))
                {
                    Map<String, Integer> map = movieDataSet.companyToMovieNumber();
                    clientSocket.write(map);
                }
                else if(option.equals("5"))
                {
                    List<Movie> movieList = movieDataSet.searchByProductionCompany(name);
                    clientSocket.write(movieList);
                }
                else if (option.equals("6"))
                {
                    clientMap.remove(name);
                    clientSocket.closeConnection();
                    break;
                }
                else if (option.equals("DASH"))
                {
                    List<Movie> movieList = movieDataSet.searchByProductionCompany(name);
                    long companyRevenue = 0;
                    for (Movie x: movieList)
                    {
                        companyRevenue += (x.getRevenue());
                    }
                    long industryRevenue = movieDataSet.getTotalIndustryRevenue();
                    int movieListSize = movieList.size();
                    int size = movieDataSet.getSize();

                    double marketShare = (companyRevenue / (double)industryRevenue);
                    double marketSharebymc = (movieListSize / (double) size);
                    String s = "DS" + Long.toString(companyRevenue) + "," + Integer.toString(movieListSize) + "," + Double.toString(marketShare) + "," + Double.toString(marketSharebymc);
                    clientSocket.write(s);

                }
                else if(option.charAt(0) == '$' && option.charAt(1) == '$')// Asking for data of another production company
                {
                    String newName = option.substring(2);
                    List<Movie> movieList = movieDataSet.searchByProductionCompany(newName);
                    clientSocket.write(movieList);
                }
                else if(option.charAt(0) == '*' && option.charAt(1) == '*') // Asking for data of a Movie
                {
                    String newName = option.substring(2);
                    Movie movie = movieDataSet.searchByName(newName);
                    if(movie == null)
                    {
                        clientSocket.write(null);
                    }
                    else{
                        clientSocket.write(movie);
                    }

                }
                else if(option.charAt(0) == '#' && option.charAt(1) == '#') // Add Movie Request
                {
                    String newName = option.substring(2);
                    Movie movie = Movie.stringToObject(newName);
                    Movie tmp =  movieDataSet.searchByName(movie.getTitle());
                    if(tmp != null)
                    {
                        clientSocket.write(tmp);
                    }
                    else {
                        movieDataSet.addMovie(movie);
                        clientSocket.write("##DONE");
                    }
                }
                else if(option.charAt(0) == '$' && option.charAt(1) == '*') // Transfer Request
                {
                    String newName = option.substring(2);
                    String[] arr = newName.split(",");
                    Movie movie = movieDataSet.searchByName(arr[0]);
                    if (movie == null)
                    {
                        clientSocket.write(movie);
                        continue;
                    }
                    if(arr.length == 1)
                    {
                        clientSocket.write("#MOVIEEXIST#");
                        continue;
                    }
                    String companyName = movieDataSet.searchExactCompanyName(arr[1]);
                    if(arr.length > 2)
                    {
                        if(!(arr[2].equalsIgnoreCase(movie.getProduction_company())))
                        {
                            clientSocket.write("#NOTALLOWEDTOTRANSFER#");
                            continue;
                        }
                    }
                    else
                    {
                        if(!("".equalsIgnoreCase(movie.getProduction_company())))
                        {
                            clientSocket.write("#NOTALLOWEDTOTRANSFER#");
                            continue;
                        }
                    }

                    if((arr[1].equalsIgnoreCase(movie.getProduction_company())))
                    {
                        clientSocket.write("#MOVIEEXIST#");
                        continue;
                    }
                    if(companyName == null)
                    {
                        clientSocket.write("#NOCOMPANYFOUND#");
                        continue;
                    }

                    movie.setProduction_company(companyName);
                    clientSocket.write("#TRANSFERRED#");
                    if(clientMap.containsKey(companyName))
                    {
                        clientMap.get(companyName).write("#MOVIETRANSFERRED#");
                    }
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}




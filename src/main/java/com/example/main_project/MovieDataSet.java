package com.example.main_project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class MovieDataSet {
    private final List<Movie> addedMovieList;
    private final List<Movie> moviesListByName;// This list is sorted in decreasing order. Z Y X W ....D C B A numbers. To print alphabetically start from the end.
    private final List<Movie> moviesListByProfit;
    private final List<Movie> moviesByTime;
    private final List<Movie> moviesByRevenue;
    private final MovieNameComparator nameComparator = new MovieNameComparator();
    private final MovieProfitComparator profitComparator = new MovieProfitComparator();
    private final MovieYearComparator yearComparator = new MovieYearComparator();
    private final MovieRevenueComparator revenueComparator = new MovieRevenueComparator();
    private int size;

    private final String fileName;
    MovieDataSet(String inputFile) throws Exception {
        this.moviesListByName = new ArrayList<>();
        this.moviesListByProfit = new ArrayList<>();
        this.moviesByRevenue = new ArrayList<>();
        this.moviesByTime = new ArrayList<>();
        this.addedMovieList = new ArrayList<>();
        size = 0;
        readFile(inputFile);
        fileName = inputFile;
    }
    Movie searchByName(String name)
    {
        Movie movie = new Movie(name);
        int index = binarySearch(moviesListByName, 0, size - 1, movie, nameComparator);
        if (index >= moviesListByName.size() || index < 0)
        {
            return null;
        }
        if(moviesListByName.get(index).getTitle().equalsIgnoreCase(name))
        {
            return moviesListByName.get(index);
        }
        else
        {
            return null;
        }
    }
    List<Movie> searchByReleaseyear(int year)
    {
        List<Movie> movies = new ArrayList<>();
        for (Movie x: moviesListByName)
        {
            if(x.getYear_of_release() == year)
            {
                movies.add(x);
            }
        }
        return movies;
    }
    List<Movie> searchByGenre(String genre)
    {
        List<Movie> movies = new ArrayList<>();
        for (Movie x: moviesListByName)
        {
            for(String i: x.getGenre())
            {
                if(i.equalsIgnoreCase(genre))
                {
                    movies.add(x);
                    break;
                }
            }
        }
        return movies;
    }
    List<Movie> searchByRunTime(int runTimeBegin, int runTimeEnd)
    {
        List<Movie> movies = new ArrayList<>();
        for (Movie x: moviesListByName)
        {
            int runTime = x.getRunning_time();
            if(runTime >= runTimeBegin && runTime <= runTimeEnd)
            {
                movies.add(x);
            }
        }
        return movies;
    }

    List<Movie> searchByProductionCompany(String production_company)
    {
        List<Movie> movies = new ArrayList<>();
        for (Movie x: moviesListByName)
        {
            if(x.getProduction_company().equalsIgnoreCase(production_company))
            {
                movies.add(x);
            }
        }
        return movies;
    }

    void addMovie(Movie movie)
    {
        addedMovieList.add(movie);
        int index = binarySearch(moviesListByProfit, 0, size - 1, movie, profitComparator);
        if(index >= size)
        {
            moviesListByProfit.add(movie);
        }
        else
        {
            moviesListByProfit.add(index, movie);
        }

        index = binarySearch(moviesListByName, 0, size - 1, movie, nameComparator);
        if(index >= size)
        {
            moviesListByName.add(movie);
        }
        else
        {
            moviesListByName.add(index, movie);
        }
        index = binarySearch(moviesByRevenue, 0, size - 1, movie, revenueComparator);
        if(index >= size)
        {
            moviesByRevenue.add(movie);
        }
        else
        {
            moviesByRevenue.add(index, movie);
        }
        index = binarySearch(moviesByTime, 0, size - 1, movie, yearComparator);
        if(index >= size)
        {
            moviesByTime.add(movie);
        }
        else
        {
            moviesByTime.add(index, movie);
        }
        size++;
    }
    List<Movie> getTop10Movies()
    {
        return moviesListByProfit.subList(0, 10);
    }

    List<Movie> recentMoviesByCompany(String company)
    {
        List<Movie> movies = new ArrayList<>();
        for (Movie x: moviesByTime)
        {
            if(x.getProduction_company().equalsIgnoreCase(company))
            {
                if(movies.size() >= 1)
                {
                    if(movies.get(0).getYear_of_release() > x.getYear_of_release())
                    {
                        break;
                    }
                }
                movies.add(x);
            }
        }
        return movies;
    }

    List<Movie> maximumRevenueByCompany(String company)
    {
        List<Movie> movies = new ArrayList<>();
        for (Movie x: moviesByRevenue)
        {
            if(x.getProduction_company().equalsIgnoreCase(company))
            {
                if(movies.size() >= 1)
                {
                    if(movies.get(0).getRevenue() > x.getRevenue())
                    {
                        break;
                    }
                }
                movies.add(x);
            }
        }
        return movies;
    }
    long totalProfitByCompany(String company)
    {
        long total = 0;
        List<Movie> movies = searchByProductionCompany(company);
        if(movies.size() == 0) throw new RuntimeException();
        for (Movie x: movies)
        {
            total += (x.getProfit());
        }
        return total;
    }
    Map<String, Integer> companyToMovieNumber()
    {
        Map<String, Integer> map = new TreeMap<>();
        for (Movie x: moviesListByName)
        {
            if(map.containsKey(x.getProduction_company()))
            {
                map.put(x.getProduction_company(), map.get(x.getProduction_company()) + 1);
            }
            else{
                map.put(x.getProduction_company(), 1);
            }
        }
        return map;
    }

    static public int binarySearch(List<Movie> movies, int l, int r, Movie movie, Comparator<Movie> cmp)
    {
        if (r >= l) {
            int mid = l + (r - l) / 2;

            // If the element is present at the
            // middle itself
            if (cmp.compare(movie, movies.get(mid)) == 0)
                return mid;

            // If element is smaller than mid, then
            // it can only be present in left sub-array
            if (cmp.compare(movie, movies.get(mid)) < 0)
                return binarySearch(movies, l, mid - 1, movie, cmp);

            // Else the element can only be present
            // in right sub-array
            return binarySearch(movies, mid + 1, r, movie, cmp);
        }
        return l;
    }
    void readFile(String file_name) throws Exception
    {
        BufferedReader br = new BufferedReader(new FileReader(file_name));
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            Movie movie = Movie.stringToObject(line);
            moviesListByName.add(movie);
            moviesListByProfit.add(movie);
            moviesByTime.add(movie);
            moviesByRevenue.add(movie);
            size++;
        }
        moviesByRevenue.sort(revenueComparator);
        moviesListByName.sort(nameComparator);
        moviesByTime.sort(yearComparator);
        moviesListByProfit.sort(profitComparator);
        br.close();
    }

    void close() throws Exception
    {
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true));

        for (Movie movie: addedMovieList)
        {
            bw.write(movie.objectToString());
            bw.write(System.lineSeparator());
            bw.close();
        }
    }
}


class MovieProfitComparator implements Comparator<Movie> {

    @Override
    public int compare(Movie firstMovie, Movie secondMovie) {
        return Long.compare(secondMovie.getProfit(), firstMovie.getProfit());
    }

}
class MovieYearComparator implements Comparator<Movie> {

    @Override
    public int compare(Movie firstMovie, Movie secondPlayer) {
        return Integer.compare(secondPlayer.getYear_of_release(), firstMovie.getYear_of_release());
    }

}

class MovieNameComparator implements Comparator<Movie>
{
    @Override
    public int compare(Movie firstMovie, Movie secondMovie)
    {
        return String.CASE_INSENSITIVE_ORDER.compare(secondMovie.getTitle(), firstMovie.getTitle());
    }
}

class MovieRevenueComparator implements Comparator<Movie>
{
    @Override
    public int compare(Movie firstMovie, Movie secondMovie)
    {
        return Long.compare(secondMovie.getRevenue(), firstMovie.getRevenue());
    }
}
package com.example.main_project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Movie implements Serializable {
    private final String title;
    private final int year_of_release;
    private final List<String> genre;
    private final int running_time;
    private final String production_company;
    private final long budget;
    private final long revenue;
    private final long profit;

    public Movie(String title)
    {
        // This was created so we can use this for binarySearch
        this.title = title;
        year_of_release = -1;
        genre = null;
        running_time = -1;
        production_company = null;
        budget = -1;
        revenue = -1;
        profit = -1;
    }
    public Movie(String title, int year_of_release, List<String> genre, int running_time, String production_company, long budget, long revenue) {
        this.title = title;
        this.year_of_release = year_of_release;
        this.genre = genre;
        this.running_time = running_time;
        this.production_company = production_company;
        this.budget = budget;
        this.revenue = revenue;
        profit = revenue - budget;
    }
    public String objectToString()
    {
        String text = "";
        text += getTitle() + "," + getYear_of_release()+",";
        for (int i = 1; i <= 3; i++)
        {
            if(getGenre().size() >= i)
            {
                text += (getGenre().get(i - 1) + ",");
            }
            else
            {
                text += ",";
            }
        }
        text += getRunning_time() + "," + getProduction_company() + "," + getBudget() + "," + getRevenue();
        return text;
    }

    static public Movie stringToObject(String specText)
    {
        String[] tokens = specText.split(",");
        List<String> genres = new ArrayList<>();
        if(!tokens[2].equals("")) genres.add(tokens[2]);
        if(!tokens[3].equals("")) genres.add(tokens[3]);
        if(!tokens[4].equals("")) genres.add(tokens[4]);
        return new Movie(tokens[0], Integer.parseInt(tokens[1]), genres, Integer.parseInt(tokens[5]), tokens[6], Long.parseLong(tokens[7]), Long.parseLong(tokens[8]));
    }

    public String getTitle() {
        return title;
    }

    public long getProfit() {
        return profit;
    }


    public int getYear_of_release() {
        return year_of_release;
    }

    public List<String> getGenre() {
        return genre;
    }
    public int getRunning_time() {
        return running_time;
    }
    public String getProduction_company() {
        return production_company;
    }
    public long getBudget() {
        return budget;
    }
    public long getRevenue() {
        return revenue;
    }

}

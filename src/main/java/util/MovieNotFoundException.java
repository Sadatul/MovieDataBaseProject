package util;

public class MovieNotFoundException extends Exception{
    @Override
    public String toString()
    {
        return "Movie Not Found";
    }
}

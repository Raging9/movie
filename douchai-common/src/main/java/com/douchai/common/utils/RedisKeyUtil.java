package com.douchai.common.utils;

public class RedisKeyUtil {

    private static final String SPLIT = ":";
    private static final String PREFIX_MOVIE = "movie";
    private static final String MOVIE_AREA = "movie:area";
    private static final String MOVIE_AGE = "movie:age";
    private static final String MOVIE_CATEGORY = "movie:category";
    private static final String CINEMA_BRAND = "cinema:brand";
    private static final String HALL_CATEGORY = "hall:category";
    private static final String CINEMA_AREA = "cinema:area";

    public static String getMovieAreaKey(){
        return MOVIE_AREA;
    }

    public static String getMovieAgeKey(){
        return MOVIE_AGE;
    }

    public static String getMovieCategoryKey(){
        return MOVIE_CATEGORY;
    }

    public static String getMovieKey(Integer movieId){
        return PREFIX_MOVIE + SPLIT + movieId;
    }

    public static String getCinemaBrandKey(){
        return CINEMA_BRAND;
    }
    public static String getHallCategoryKey(){
        return HALL_CATEGORY;
    }
    public static String getCinemaAreaKey(){
        return CINEMA_AREA;
    }
}

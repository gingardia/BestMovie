package it.unimib.disco.sal.bestmovie.utils;

import retrofit2.http.PUT;

public class Constants {
    public static final String MOVIE_API_BASE_URL = "https://api.themoviedb.org/3/";
    public static final String MOVIE_API_KEY = "14fb13c7faf3a25a9eafec50d55ee519";
    public static final String MOVIE_API_READ_ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxNGZiMTNjN2ZhZjNhMjVhOWVhZmVjNTBkNTVlZTUxOSIsInN1YiI6IjVmZDQ5ZDk4ZTljMGRjMDAzYzViYzVhZSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.BfxhdNVNuB7aNMsHjl3EUHiQeg7lrX7QcQINgO9A5Aw";
    public static final String LANGUAGE = "it";
    public static final String region = "IT";
    public static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/";
    public static final String IMAGE_API_URL = "https://image.tmdb.org/t/p/w300/";
    public static String VIDEOS = "videos";
    public static final String BUNDLE_ID_MOVIE_ID = "movie_id";
    public static final String IS_FROM_HOME = "IS_FROM_HOME";
    public static final String YOUTUBE_PLAYER_API_KEY = "AIzaSyCB9iTJ6jK1uKZABP7og4vDk7mwtXoSgBg";
    public static final String QUERY_PARAM_POPULARITY_SORT_BY_ASCENDING = "popularity.asc";
    public static final String QUERY_PARAM_POPULARITY_SORT_BY_DESCENDING = "popularity.desc";
    public static final String QUERY_PARAM_VOTE_COUNT_BY_ASCENDING = "vote_count.asc";
    public static final String QUERY_PARAM_VOTE_COUNT_BY_DESCENDING = "vote_count.desc";
}

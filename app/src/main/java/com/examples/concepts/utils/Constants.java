package com.examples.concepts.utils;

import android.net.Uri;

public class Constants {
    public static final String TMDB_BASE_URL = "https://api.themoviedb.org/3/movie/";
    public static final String TMDB_API_KEY = "ab46c2f2722af43d6069d8a1e9b381cd";
    public static final String TMDB_IMAGES_BASE_URL = "https://image.tmdb.org/t/p/original/";

    public static final String MOVIE_TYPE_POPULAR = "popular";
    public static final String MOVIE_TYPE_UPCOMING = "upcoming";
    public static final String MOVIE_TYPE_TOP_RATED = "top_rated";

    public static final String GITHUB_BASE_URL = "https://api.github.com/users/";

    public static String CONTENT_AUTHORITY = "com.examples.concepts";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String DATABASE_NAME = "productList.db";


    public static final String TABLE_NAME = "productTable";
    public static int DATABASE_VERSION = 1;
    public static final String COLUMN_NAME = "productName";
    public static final String COLUMN_CATEGORY = "productCategory";
    public static final String COLUMN_PRICE = "productPrice";

    public static final int URI_CODE = 1;
}

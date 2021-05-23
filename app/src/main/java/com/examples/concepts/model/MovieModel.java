package com.examples.concepts.model;

import java.util.List;

public class MovieModel
{
    private String page;
    private String total_pages;
    private List<Results> results;
    private String total_results;

    public String getPage () {
        return page;
    }

    public String getTotal_pages () {
        return total_pages;
    }

    public List<Results> getResults() {
        return results;
    }

    public String getTotal_results () {
        return total_results;
    }

    public static class Results
    {
        private String overview;
        private String original_language;
        private String original_title;
        private String video;
        private String title;
        private String[] genre_ids;
        private String poster_path;
        private String backdrop_path;
        private String release_date;
        private String popularity;
        private String vote_average;
        private String id;
        private String adult;
        private String vote_count;

        public String getOverview () {
            return overview;
        }

        public String getOriginal_language () {
            return original_language;
        }

        public String getOriginal_title () {
            return original_title;
        }

        public String getVideo () {
            return video;
        }

        public String getTitle () {
            return title;
        }

        public String[] getGenre_ids () {
            return genre_ids;
        }

        public String getPoster_path () {
            return poster_path;
        }

        public String getBackdrop_path () {
            return backdrop_path;
        }

        public String getRelease_date () {
            return release_date;
        }

        public String getPopularity () {
            return popularity;
        }

        public String getVote_average () {
            return vote_average;
        }

        public String getId () {
            return id;
        }

        public String getAdult () {
            return adult;
        }

        public String getVote_count () {
            return vote_count;
        }
    }
}
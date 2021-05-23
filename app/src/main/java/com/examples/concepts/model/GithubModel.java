package com.examples.concepts.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GithubModel {
    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("node_id")
    @Expose
    private String nodeId;
    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;
    @SerializedName("repos_url")
    @Expose
    private String reposUrl;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("bio")
    @Expose
    private String bio;
    @SerializedName("public_repos")
    @Expose
    private Integer publicRepos;
    @SerializedName("followers")
    @Expose
    private int followers;
    @SerializedName("following")
    @Expose
    private int following;

    public String getLogin() {
        return login;
    }

    public Integer getId() {
        return id;
    }

    public String getNodeId() {
        return nodeId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getReposUrl() {
        return reposUrl;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getBio() {
        return bio;
    }

    public Integer getPublicRepos() {
        return publicRepos;
    }

    public int getFollowers() {
        return followers;
    }

    public int getFollowing() {
        return following;
    }
}
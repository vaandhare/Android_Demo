package com.examples.concepts.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GithubRepoModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("node_id")
    @Expose
    private String nodeId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("html_url")
    @Expose
    private String htmlUrl;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("language")
    @Expose
    private String language;

    public Integer getId() {
        return id;
    }

    public String getNodeId() {
        return nodeId;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getDescription() {
        return description;
    }

    public String getLanguage() {
        return language;
    }
}


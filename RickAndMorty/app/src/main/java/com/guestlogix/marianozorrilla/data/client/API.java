package com.guestlogix.marianozorrilla.data.client;


public enum  API {
    EPISODES("api/episode?page="),
    CHARACTERS("api/character?page=");

    private String url;

    API(String value) {
        this.url = value;
    }

    public String getUrl() {
        return url;
    }
}

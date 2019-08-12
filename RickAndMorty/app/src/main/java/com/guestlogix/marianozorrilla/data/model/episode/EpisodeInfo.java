package com.guestlogix.marianozorrilla.data.model.episode;

public class EpisodeInfo {

    private Integer count;

    private Integer pages;

    private String next;

    private String prev;

    public EpisodeInfo() {

    }

    public EpisodeInfo(Integer count, Integer pages, String next, String prev) {
        this.count = count;
        this.pages = pages;
        this.next = next;
        this.prev = prev;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrev() {
        return prev;
    }

    public void setPrev(String prev) {
        this.prev = prev;
    }
}

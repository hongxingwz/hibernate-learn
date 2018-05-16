package com.jianglei.entity;

import java.util.Date;

/**
 * @author jianglei
 * @since 2018/5/16
 */
public class Event {

    private Long id;

    private String title;

    private Date date;

    public Event() {
    }

    public Event(Long id, String title, Date date) {
        this.id = id;
        this.title = title;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "com.jianglei.entity.Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", date=" + date +
                '}';
    }
}

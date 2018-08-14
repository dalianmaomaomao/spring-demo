package com.example.form.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by cj on 2018/6/22.
 */
@Entity
public class Form {
    @Id
    @GeneratedValue
    private int id;
    private String title;
    private String node;
    @Column(name="start_time")
    private Date startTime;
    @Column(name="end_time")
    private Date endTime;

    public Form() {
    }

    public Form(String title, String node, Date startTime, Date endTime) {
        this.title = title;
        this.node = node;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}

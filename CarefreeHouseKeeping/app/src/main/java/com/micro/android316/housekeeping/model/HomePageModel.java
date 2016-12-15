package com.micro.android316.housekeeping.model;

/**
 * Created by Administrator on 2016/12/12.
 */
public class HomePageModel {
    private int picture;
    private String name,workRanges,briefInduction;
    private int age,workYears,browseTimes,commentTimes;

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkRanges() {
        return workRanges;
    }

    public void setWorkRanges(String workRanges) {
        this.workRanges = workRanges;
    }

    public String getBriefInduction() {
        return briefInduction;
    }

    public void setBriefInduction(String briefInduction) {
        this.briefInduction = briefInduction;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWorkYears() {
        return workYears;
    }

    public void setWorkYears(int workYears) {
        this.workYears = workYears;
    }

    public int getBrowseTimes() {
        return browseTimes;
    }

    public void setBrowseTimes(int browseTimes) {
        this.browseTimes = browseTimes;
    }

    public int getCommentTimes() {
        return commentTimes;
    }

    public void setCommentTimes(int commentTimes) {
        this.commentTimes = commentTimes;
    }
}

package com.prompt.entities.prompt_entity.entity;


public class Tutorial {
    private String title;
    private String content;
    private String currentDate;

    public Tutorial(String title, String content,String currentDate) {
        this.title = title;
        this.content = content;
        this.currentDate = currentDate;
    }

    public Tutorial() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }
}

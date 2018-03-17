package com.sonhoai.sonho.lab5.B1.B2;

/**
 * Created by sonho on 3/13/2018.
 */

public class GhiChu {
    private int ID;
    private String Content;
    private String Date;

    public GhiChu(int ID, String content, String date) {
        this.ID = ID;
        Content = content;
        Date = date;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}

package com.maradroid.routeapp;

import java.util.ArrayList;

/**
 * Created by mara on 15.05.15..
 */
public class RouteObject {

    String id, duration, title, content, slika_id;
    ArrayList<String> arrayList;

    public RouteObject(String id, String duration, String title, String content, String slika_id, ArrayList<String> arrayList){
        this.id = id;
        this.duration = duration;
        this.title = title;
        this.content = content;
        this.slika_id = slika_id;
        this.arrayList = arrayList;
    }

    public String getContent() {
        return content;
    }

    public String getDuration() {
        return duration;
    }

    public String getId() {
        return id;
    }

    public String getSlikaId() {
        return slika_id;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<String> getArrayList() {
        return arrayList;
    }
}

package com.devi.mycataloguemovie;

import org.json.JSONObject;

public class MovieItems  {
    private int id;
    private String poster_path;
    private String judul;
    private String description;
    private String tanggal;

    public MovieItems(JSONObject object){
        try {
            int id = object.getInt("id");

            String judul = object.getString("title");
            String description = object.getString("overview");
            String tanggal = object.getString("release_date");
            String poster_path = object.getString("poster_path");

            this.id = id;
            this.poster_path = poster_path;
            this.judul = judul;
            this.description = description;
            this.tanggal = tanggal;

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getJudul(){
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }
}

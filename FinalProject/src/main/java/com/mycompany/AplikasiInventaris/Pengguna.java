package com.mycompany.AplikasiInventaris;

public class Pengguna {
    private String nama;
    private String id;

    public Pengguna(String nama, String id) {
        this.nama = nama;
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public String getID() {
        return id;
    }

    public String getRole() {
        return "Pengguna";
    }
}

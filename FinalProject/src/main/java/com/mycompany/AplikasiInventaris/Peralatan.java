package com.mycompany.AplikasiInventaris;

public class Peralatan {
    protected String nama;
    protected String id;
    protected int jumlah;
    protected String kategori;

    public Peralatan(String nama, String id, int jumlah, String kategori) {
        this.nama = nama;
        this.id = id;
        this.jumlah = jumlah;
        this.kategori = kategori;
    }

    public String getNama() {
        return nama;
    }

    public String getID() {
        return id;
    }

    public int getJumlah() {
        return jumlah;
    }

    public String getKategori() {
        return kategori;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    @Override
    public String toString() {
        return nama + " (ID: " + id + ", Jumlah: " + jumlah + ", Kategori: " + kategori + ")";
    }
}

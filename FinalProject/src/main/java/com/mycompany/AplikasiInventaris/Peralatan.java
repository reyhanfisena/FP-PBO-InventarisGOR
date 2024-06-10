package com.mycompany.AplikasiInventaris;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template.
 */

/**
 *
 * @author REYHANFISENA
 */
public class Peralatan {
    protected String nama;
    protected String id;
    protected int jumlah;

    public Peralatan(String nama, String id, int jumlah) {
        this.nama = nama;
        this.id = id;
        this.jumlah = jumlah;
    }

    public String getNama() {
        return nama;
    }

    public String getId() {
        return id;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    @Override
    public String toString() {
        return nama + " (ID: " + id + ", Jumlah: " + jumlah + ")";
    }

    Object getID() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    Object getKategori() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

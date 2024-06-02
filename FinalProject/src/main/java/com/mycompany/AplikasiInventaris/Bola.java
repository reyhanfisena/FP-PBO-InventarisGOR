package com.mycompany.AplikasiInventaris;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author REYHANFISENA
 */

public class Bola extends Peralatan {
    private String jenis;

    public Bola(String nama, String id, int jumlah, String jenis) {
        super(nama, id, jumlah);
        this.jenis = jenis;
    }

    public String getJenis() {
        return jenis;
    }

    @Override
    public String toString() {
        return super.toString() + " - Jenis: " + jenis;
    }
}

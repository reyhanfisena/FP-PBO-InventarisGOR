/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.AplikasiInventaris;

/**
 *
 * @author REYHANFISENA
 */

import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

public class Inventaris {
    private List<Peralatan> peralatan;
    private List<Pengguna> pengguna;

    public Inventaris() {
        peralatan = new ArrayList<>();
        pengguna = new ArrayList<>();
    }

    public void tambahPeralatan(Peralatan peralatan) {
        this.peralatan.add(peralatan);
    }

    public void tambahPengguna(Pengguna pengguna) {
        this.pengguna.add(pengguna);
    }

    public String keJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public void dariJson(String json) {
        Gson gson = new Gson();
        Inventaris inventaris = gson.fromJson(json, Inventaris.class);
        this.peralatan = inventaris.peralatan;
        this.pengguna = inventaris.pengguna;
    }

    public void tampilkanPeralatan() {
        for (Peralatan p : peralatan) {
            System.out.println(p);
        }
    }

    public void tampilkanPengguna() {
        for (Pengguna p : pengguna) {
            p.tampilkanInfoPengguna();
        }
    }
}

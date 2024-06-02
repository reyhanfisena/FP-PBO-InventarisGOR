/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.AplikasiInventaris;

/**
 *
 * @author REYHANFISENA
 */

public abstract class Pengguna {
    protected String nama;
    protected String userId;

    public Pengguna(String nama, String userId) {
        this.nama = nama;
        this.userId = userId;
    }

    public String getNama() {
        return nama;
    }

    public String getUserId() {
        return userId;
    }

    public abstract void tampilkanInfoPengguna();
}


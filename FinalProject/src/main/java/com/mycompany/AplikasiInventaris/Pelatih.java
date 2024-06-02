/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.AplikasiInventaris;

/**
 *
 * @author REYHANFISENA
 */

public class Pelatih extends Pengguna {
    private String olahraga;

    public Pelatih(String nama, String userId, String olahraga) {
        super(nama, userId);
        this.olahraga = olahraga;
    }

    public String getOlahraga() {
        return olahraga;
    }

    @Override
    public void tampilkanInfoPengguna() {
        System.out.println("Pelatih: " + nama + " (ID: " + userId + ") - Olahraga: " + olahraga);
    }
}

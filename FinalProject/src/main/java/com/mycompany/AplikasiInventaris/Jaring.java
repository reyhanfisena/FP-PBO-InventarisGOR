/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.AplikasiInventaris;

/**
 *
 * @author REYHANFISENA
 */

public class Jaring extends Peralatan {
    private String olahraga;

    public Jaring(String nama, String id, int jumlah, String olahraga) {
        super(nama, id, jumlah);
        this.olahraga = olahraga;
    }

    public String getOlahraga() {
        return olahraga;
    }

    @Override
    public String toString() {
        return super.toString() + " - Olahraga: " + olahraga;
    }
}


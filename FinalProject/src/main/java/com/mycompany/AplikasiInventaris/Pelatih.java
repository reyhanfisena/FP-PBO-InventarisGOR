package com.mycompany.AplikasiInventaris;

public class Pelatih extends Pengguna {
    private String olahraga;

    public Pelatih(String nama, String id, String olahraga) {
        super(nama, id);
        this.olahraga = olahraga;
    }

    public String getOlahraga() {
        return olahraga;
    }

    @Override
    public String toString() {
        return super.toString() + " (Olahraga: " + olahraga + ")";
    }
}
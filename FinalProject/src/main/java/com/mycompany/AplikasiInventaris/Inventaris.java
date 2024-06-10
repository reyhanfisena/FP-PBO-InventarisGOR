package com.mycompany.AplikasiInventaris;

import java.util.ArrayList;
import java.util.List;

public class Inventaris {
    private final List<Peralatan> daftarPeralatan;
    private final List<Pengguna> daftarPengguna;

    public Inventaris() {
        daftarPeralatan = new ArrayList<>();
        daftarPengguna = new ArrayList<>();
    }

    public void tambahPeralatan(Peralatan peralatan) {
        daftarPeralatan.add(peralatan);
    }

    public List<Peralatan> getPeralatan() {
        return daftarPeralatan;
    }

    public void tambahPengguna(Pengguna pengguna) {
        daftarPengguna.add(pengguna);
    }

    public List<Pengguna> getPengguna() {
        return daftarPengguna;
    }

    public Pengguna cariPengguna(String id) {
        for (Pengguna pengguna : daftarPengguna) {
            if (pengguna.getID().equals(id)) {
                return pengguna;
            }
        }
        return null;
    }

    public void hapusPengguna(Pengguna pengguna) {
        daftarPengguna.remove(pengguna);
    }

    public Peralatan cariPeralatan(String id) {
        for (Peralatan peralatan : daftarPeralatan) {
            if (peralatan.getID().equals(id)) {
                return peralatan;
            }
        }
        return null;
    }

    public void hapusPeralatan(Peralatan peralatan) {
        daftarPeralatan.remove(peralatan);
    }
}

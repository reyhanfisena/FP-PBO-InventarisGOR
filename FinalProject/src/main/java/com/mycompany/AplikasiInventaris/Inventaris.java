package com.mycompany.AplikasiInventaris;

import java.util.ArrayList;
import java.util.List;

public class Inventaris {
    private List<Peralatan> peralatanList;
    private List<Pengguna> penggunaList;

    public Inventaris() {
        peralatanList = new ArrayList<>();
        penggunaList = new ArrayList<>();
    }

    public void tambahPeralatan(Peralatan peralatan) {
        peralatanList.add(peralatan);
    }

    public void tambahPengguna(Pengguna pengguna) {
        penggunaList.add(pengguna);
    }

    public List<Peralatan> getPeralatan() {
        return peralatanList;
    }

    public List<Pengguna> getPengguna() {
        return penggunaList;
    }
}
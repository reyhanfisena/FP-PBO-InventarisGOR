package com.mycompany.AplikasiInventaris;

public class Admin extends Pengguna {
    public Admin(String nama, String id) {
        super(nama, id);
    }

    @Override
    public String getRole() {
        return "Admin";
    }
}

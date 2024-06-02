/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.AplikasiInventaris;

/**
 *
 * @author REYHANFISENA
 */

public class Admin extends Pengguna {

    public Admin(String nama, String userId) {
        super(nama, userId);
    }

    @Override
    public void tampilkanInfoPengguna() {
        System.out.println("Admin: " + nama + " (ID: " + userId + ")");
    }
}

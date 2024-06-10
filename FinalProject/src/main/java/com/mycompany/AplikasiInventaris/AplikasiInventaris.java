package com.mycompany.AplikasiInventaris;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class AplikasiInventaris {
    private final Inventaris inventaris;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextArea textArea;

    public AplikasiInventaris() {
        inventaris = new Inventaris();
        inisialisasiInventaris();
        inisialisasiUI();
        redirectSystemStreams();
    }

    private void inisialisasiInventaris() {
        inventaris.tambahPeralatan(new Bola("Bola Sepak", "B1", 10, "Sepak Bola"));
        inventaris.tambahPeralatan(new Jaring("Jaring Voli", "J1", 5, "Bola Voli"));
        inventaris.tambahPengguna(new Admin("Alice", "A1"));
        inventaris.tambahPengguna(new Pelatih("Bob", "P1", "Basket"));
    }

    private void inisialisasiUI() {
        JFrame frame = new JFrame("Sistem Manajemen Peralatan Olahraga");
        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Nama");
        tableModel.addColumn("ID");
        tableModel.addColumn("Jumlah");
        tableModel.addColumn("Kategori");

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        buttonPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        JButton tampilkanPeralatanButton = new JButton("Tampilkan Peralatan");
        JButton tampilkanPenggunaButton = new JButton("Tampilkan Pengguna");
        JButton simpanButton = new JButton("Simpan ke JSON");
        JButton muatButton = new JButton("Muat dari JSON");

        buttonPanel.add(tampilkanPeralatanButton);
        buttonPanel.add(tampilkanPenggunaButton);
        buttonPanel.add(simpanButton);
        buttonPanel.add(muatButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);

        // Menerapkan gaya visual yang lebih menarik
        frame.getContentPane().setBackground(new Color(240, 240, 240));
        mainPanel.setBackground(Color.white);
        buttonPanel.setBackground(Color.white);
        table.setBackground(Color.white);

        tampilkanPeralatanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tampilkanPeralatan();
            }
        });

        tampilkanPenggunaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tampilkanPengguna();
            }
        });

        simpanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simpanKeJSON();
            }
        });

        muatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                muatDariJSON();
            }
        });

        frame.setVisible(true);
    }

    private void tampilkanPeralatan() {
        tableModel.setRowCount(0); // Menghapus semua baris yang ada sebelumnya

        for (Peralatan peralatan : inventaris.getPeralatan()) {
            Object[] row = {peralatan.getNama(), peralatan.getID(), peralatan.getJumlah(), peralatan.getKategori()};
            tableModel.addRow(row);
        }
    }

    private void tampilkanPengguna() {
        tableModel.setRowCount(0); // Menghapus semua baris yang ada sebelumnya

        for (Pengguna pengguna : inventaris.getPengguna()) {
            Object[] row = {pengguna.getNama(), pengguna.getID()};
            tableModel.addRow(row);
        }
    }

    private void simpanKeJSON() {
        // Implementasi menyimpan ke JSON
        // Tidak termasuk dalam ruang lingkup pembaruan ini
        textArea.setText("Simpan ke JSON");
    }

    private void muatDariJSON() {
        // Implementasi memuat dari JSON
        // Tidak termasuk dalam ruang lingkup pembaruan ini
        textArea.setText("Muat dari JSON");
    }

    private void updateTextArea(final String text) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                textArea.append(text);
            }
        });
    }

    private void redirectSystemStreams() {
        OutputStream out = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                updateTextArea(String.valueOf((char) b));
            }

            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                updateTextArea(new String(b, off, len));
            }

            @Override
            public void write(byte[] b) throws IOException {
                write(b, 0, b.length);
            }
        };

        System.setOut(new PrintStream(out, true));
        System.setErr(new PrintStream(out, true));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            }
        });
    }
}
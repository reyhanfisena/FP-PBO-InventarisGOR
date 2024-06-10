package com.mycompany.AplikasiInventaris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class AplikasiInventaris {
    private Inventaris inventaris;
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
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);

        JPanel panel = new JPanel();
        JButton tampilkanPeralatanButton = new JButton("Tampilkan Peralatan");
        JButton tampilkanPenggunaButton = new JButton("Tampilkan Pengguna");
        JButton simpanButton = new JButton("Simpan ke JSON");
        JButton muatButton = new JButton("Muat dari JSON");

        panel.add(tampilkanPeralatanButton);
        panel.add(tampilkanPenggunaButton);
        panel.add(simpanButton);
        panel.add(muatButton);
        frame.add(panel, BorderLayout.SOUTH);

        tampilkanPeralatanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                inventaris.tampilkanPeralatan();
            }
        });

        tampilkanPenggunaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                inventaris.tampilkanPengguna();
            }
        });

        simpanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String json = inventaris.keJson();
                textArea.setText(json);
            }
        });

        muatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String json = textArea.getText();
                inventaris.dariJson(json);
                JOptionPane.showMessageDialog(frame, "Data inventaris dimuat dari JSON.");
            }
        });

        frame.setVisible(true);
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
                new AplikasiInventaris();
            }
        });
    }
}

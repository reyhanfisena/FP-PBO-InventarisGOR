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
        frame.setSize(800, 500);
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

        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 0));
        buttonPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        JButton tampilkanPeralatanButton = new JButton("Tampilkan Peralatan");
        JButton tampilkanPenggunaButton = new JButton("Tampilkan Pengguna");
        JButton hapusPenggunaButton = new JButton("Hapus Pengguna");
        JButton tambahPenggunaButton = new JButton("Tambah Pengguna");

        buttonPanel.add(tampilkanPeralatanButton);
        buttonPanel.add(tampilkanPenggunaButton);
        buttonPanel.add(hapusPenggunaButton);
        buttonPanel.add(tambahPenggunaButton);
        mainPanel.add(buttonPanel, BorderLayout.NORTH);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane textScrollPane = new JScrollPane(textArea);
        textScrollPane.setPreferredSize(new Dimension(800, 100));
        mainPanel.add(textScrollPane, BorderLayout.SOUTH);

        frame.add(mainPanel, BorderLayout.CENTER);

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

        hapusPenggunaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hapusPengguna();
            }
        });

        tambahPenggunaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tambahPengguna();
            }
        });

        frame.setVisible(true);
        frame.revalidate();
        frame.repaint();
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
            Object[] row = {pengguna.getNama(), pengguna.getID(), "-", "-"}; // Menampilkan data pengguna di tabel
            tableModel.addRow(row);
        }
    }

    private void hapusPengguna() {
        String id = JOptionPane.showInputDialog(null, "Masukkan ID pengguna yang ingin dihapus:");
        if (id != null && !id.trim().isEmpty()) {
            Pengguna pengguna = inventaris.cariPengguna(id);
            if (pengguna != null) {
                inventaris.hapusPengguna(pengguna);
                textArea.setText("Pengguna dengan ID: " + id + " berhasil dihapus.");
                tampilkanPengguna();
            } else {
                textArea.setText("Pengguna dengan ID: " + id + " tidak ditemukan.");
            }
        } else {
            textArea.setText("ID pengguna tidak valid.");
        }
    }

    private void tambahPengguna() {
        String nama = JOptionPane.showInputDialog(null, "Masukkan nama pengguna:");
        String id = JOptionPane.showInputDialog(null, "Masukkan ID pengguna:");
        if (nama != null && !nama.trim().isEmpty() && id != null && !id.trim().isEmpty()) {
            Pengguna pengguna = new Pengguna(nama, id);
            inventaris.tambahPengguna(pengguna);
            textArea.setText("Pengguna baru dengan ID: " + id + " berhasil ditambahkan.");
            tampilkanPengguna();
        } else {
            textArea.setText("Nama atau ID pengguna tidak valid.");
        }
    }

    private void updateTextArea(final String text) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                textArea.append(text + "\n");
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
                new LoginForm(); // Panggil LoginForm di sini, bukan AplikasiInventaris
            }
        });
    }
}

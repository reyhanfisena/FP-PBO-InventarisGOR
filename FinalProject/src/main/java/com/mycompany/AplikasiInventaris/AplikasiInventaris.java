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
    private javax.swing.JTable table;
    private javax.swing.table.DefaultTableModel tableModel;
    private javax.swing.JTextArea textArea;

    public AplikasiInventaris() {
        inventaris = new Inventaris();
        inisialisasiInventaris();
        inisialisasiUI();  // Panggil inisialisasiUI langsung
        redirectSystemStreams();
    }

    private void inisialisasiInventaris() {
        inventaris.tambahPeralatan(new Peralatan("Bola Basket", "BB", 10, "Bola Basket"));
        inventaris.tambahPeralatan(new Peralatan("Ring Basket", "RB", 2, "Ring Basket"));
        inventaris.tambahPeralatan(new Peralatan("Bola Sepak", "BS", 10, "Bola Futsal"));
        inventaris.tambahPeralatan(new Peralatan("Gawang Futsal", "GW", 4, "Gawang Futsal"));
        inventaris.tambahPeralatan(new Peralatan("Jaring Voli", "J1", 5, "Bola Voli"));
        inventaris.tambahPeralatan(new Peralatan("Bola Voli", "BV", 10, "Bola Voli"));
        inventaris.tambahPeralatan(new Peralatan("Jaring Badminton", "J2", 8, "Jaring Badminton"));
        inventaris.tambahPengguna(new Admin("Alice", "A1"));
        inventaris.tambahPengguna(new Pelatih("Bob", "P1", "Basket"));
    }

    private void inisialisasiUI() {
        JFrame frame = new JFrame("Sistem Manajemen Peralatan Olahraga");
        frame.setSize(1000, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        buttonPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        JButton tampilkanPeralatanButton = new JButton("Tampilkan Peralatan");
        JButton tampilkanPenggunaButton = new JButton("Tampilkan Pengguna");
        JButton tambahPeralatanButton = new JButton("Tambah Peralatan");
        JButton hapusPeralatanButton = new JButton("Hapus Peralatan");
        JButton tambahPenggunaButton = new JButton("Tambah Pengguna");
        JButton hapusPenggunaButton = new JButton("Hapus Pengguna");

        buttonPanel.add(tampilkanPeralatanButton);
        buttonPanel.add(tampilkanPenggunaButton);
        buttonPanel.add(tambahPeralatanButton);
        buttonPanel.add(hapusPeralatanButton);
        buttonPanel.add(tambahPenggunaButton);
        buttonPanel.add(hapusPenggunaButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

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

        tambahPeralatanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tambahPeralatan();
            }
        });

        hapusPeralatanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hapusPeralatan();
            }
        });

        tambahPenggunaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tambahPengguna();
            }
        });

        hapusPenggunaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hapusPengguna();
            }
        });

        frame.setVisible(true);
    }

    private void tampilkanPeralatan() {
        tableModel.setRowCount(0); // Menghapus semua baris yang ada sebelumnya
        tableModel.setColumnIdentifiers(new String[]{"Nama", "ID", "Jumlah", "Kategori"}); // Set column headers for peralatan

        for (Peralatan peralatan : inventaris.getPeralatan()) {
            Object[] row = {peralatan.getNama(), peralatan.getID(), peralatan.getJumlah(), peralatan.getKategori()};
            tableModel.addRow(row);
        }
    }

    private void tampilkanPengguna() {
        tableModel.setRowCount(0); // Menghapus semua baris yang ada sebelumnya
        tableModel.setColumnIdentifiers(new String[]{"ID", "Nama", "Role"}); // Set column headers for pengguna

        for (Pengguna pengguna : inventaris.getPengguna()) {
            Object[] row = {pengguna.getID(), pengguna.getNama(), pengguna.getRole()};
            tableModel.addRow(row);
        }
    }

    private void tambahPeralatan() {
        JTextField namaField = new JTextField();
        JTextField idField = new JTextField();
        JTextField jumlahField = new JTextField();
        JTextField kategoriField = new JTextField();

        Object[] message = {
            "Nama:", namaField,
            "ID:", idField,
            "Jumlah:", jumlahField,
            "Kategori:", kategoriField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Tambah Peralatan", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String nama = namaField.getText();
            String id = idField.getText();
            int jumlah = Integer.parseInt(jumlahField.getText());
            String kategori = kategoriField.getText();
            Peralatan peralatanBaru = new Peralatan(nama, id, jumlah, kategori);
            inventaris.tambahPeralatan(peralatanBaru);
            tampilkanPeralatan();
        }
    }

    private void hapusPeralatan() {
        String id = JOptionPane.showInputDialog("Masukkan ID peralatan yang akan dihapus:");
        Peralatan peralatanDihapus = null;
        for (Peralatan peralatan : inventaris.getPeralatan()) {
            if (peralatan.getID().equals(id)) {
                peralatanDihapus = peralatan;
                break;
            }
        }
        if (peralatanDihapus != null) {
            inventaris.getPeralatan().remove(peralatanDihapus);
            tampilkanPeralatan();
        } else {
            JOptionPane.showMessageDialog(null, "Peralatan dengan ID " + id + " tidak ditemukan.");
        }
    }

    private void tambahPengguna() {
        JTextField namaField = new JTextField();
        JTextField idField = new JTextField();

        Object[] message = {
            "Nama:", namaField,
            "ID:", idField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Tambah Pengguna", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String nama = namaField.getText();
            String id = idField.getText();
            Pengguna penggunaBaru = new Pengguna(nama, id);
            inventaris.tambahPengguna(penggunaBaru);
            tampilkanPengguna();
        }
    }

    private void hapusPengguna() {
        String id = JOptionPane.showInputDialog("Masukkan ID pengguna yang akan dihapus:");
        Pengguna penggunaDihapus = null;
        for (Pengguna pengguna : inventaris.getPengguna()) {
            if (pengguna.getID().equals(id)) {
                penggunaDihapus = pengguna;
                break;
            }
        }
        if (penggunaDihapus != null) {
            inventaris.getPengguna().remove(penggunaDihapus);
            tampilkanPengguna();
        } else {
            JOptionPane.showMessageDialog(null, "Pengguna dengan ID " + id + " tidak ditemukan.");
        }
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
                new LoginForm(); // Panggil LoginForm di sini, bukan AplikasiInventaris
            }
        });
    }
}

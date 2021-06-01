package com.company;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class Main extends javax.swing.JFrame {

    private JPanel panelTugasAkhir;
    private JComboBox<String> cboMerk;
    private JButton lihatButton;
    private JButton hapusButton;
    private JTable tblBarang;
    private JTextField txtTipe;
    private JButton beliButton;
    private JTextField txtSatuan;
    private JLabel LabelJudul;
    private JLabel LabelMerk;
    private JLabel LabelWarna;
    private JLabel LabelPilih;
    private JLabel LabelSatuan;
    private JComboBox<String> cboTipe;
    private JComboBox<String> cboWarna;
    private JTextField txtPembelian;

    private double nama, harga, jmlhjual, total;
    private long hargaProduk1, hargaProduk2, hargaProduk3, hargaSatuan;
    private String kodeMerk, Merk, Jenis, SeriA, SeriB, SeriC, warna, kodeTipe;
    private final Tabel penjualan = new Tabel();
    private DefaultTableModel tabel;

    private final String[] samsungVariants = {"Samsung Galaxy A51", "Samsung Galaxy S10 Lite", "Samsung Galaxy S20"};
    private final String[] samsungVariantColors = {"Mate Black", "Shining Silver ", "Wonderful White"};
    private final int[] samsungVariantsPrices = {4700000, 8199000, 14500000};

    private final String[] appleVariants = {"iPhone 11", "iPhone SE 2020", "iPhone 12 Pro"};
    private final String[] appleVariantColors = {"Black", "White ", "Red"};
    private final int[] appleVariantsPrices = {12000000, 7500000, 21000000};

    private final String[] oppoVariants = {"Oppo A74", "Oppo Reno 3 Pro", "Oppo Find X2"};
    private final String[] oppoVariantColors = {"Ocean Blue", "Very Black ", "Muted White"};
    private final int[] oppoVariantsPrices = {3200000, 4300000, 13400000};

    public static void main(String[] args) {
        ImageIcon icon = new ImageIcon("icon2.png");
        JFrame frame = new JFrame("Online Phone Sale");
        frame.setContentPane(new Main().panelTugasAkhir);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.CYAN);
        frame.setIconImage(icon.getImage());
        frame.pack();
        frame.setVisible(true);
    }

    public Main() {
        TableColumn column;
        tblBarang.setModel(penjualan.getTabel());
        tblBarang.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        column = tblBarang.getColumnModel().getColumn(0);
        column.setPreferredWidth(50);
        column = tblBarang.getColumnModel().getColumn(1);
        column.setPreferredWidth(250);
        column = tblBarang.getColumnModel().getColumn(2);
        column.setPreferredWidth(150);
        initComponent();
    }

    private void initComponent() {

        lihatButton.addActionListener(e -> {
            String[] colNames = {"No", "Tipe Handphone", "Harga Satuan"};
            tabel = new DefaultTableModel(colNames, 0);
            tblBarang = new JTable();
            tblBarang.setModel(tabel);
            String selectedMerk = String.valueOf(cboMerk.getSelectedItem());
            if (!selectedMerk.isEmpty()) updateMerkComponent(selectedMerk);
        });

        beliButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Terima kasih berbelanja di toko kami. Have a great day!");
        });

        hapusButton.addActionListener(e -> penjualan.getTabel().setRowCount(0));

        cboMerk.addActionListener(e -> {
            String selectedMerk = String.valueOf(cboMerk.getSelectedItem());
            if (!selectedMerk.isEmpty()) updateMerkComponent(selectedMerk);
        });

        cboTipe.addActionListener(e -> updateHargaSatuan());

        populateTable(samsungVariants, samsungVariantsPrices);
        populateCboTipe(samsungVariants);
        populateCboWarna(samsungVariantColors);
        updateHargaSatuan();
    }

    private void updateMerkComponent(final String selectedMerk) {
        switch (selectedMerk) {
            case "Samsung":
                populateTable(samsungVariants, samsungVariantsPrices);
                populateCboTipe(samsungVariants);
                populateCboWarna(samsungVariantColors);
                break;
            case "Apple":
                populateTable(appleVariants, appleVariantsPrices);
                populateCboTipe(appleVariants);
                populateCboWarna(appleVariantColors);
                break;
            default:
                populateTable(oppoVariants, oppoVariantsPrices);
                populateCboTipe(oppoVariants);
                populateCboWarna(oppoVariantColors);
                break;
        }
        updateHargaSatuan();
    }

    private void updateHargaSatuan() {
        String selectedMerk = String.valueOf(cboMerk.getSelectedItem());
        int selectedTypeIndex = Math.max(cboTipe.getSelectedIndex(), 0);
        int price = 0;
        if (!selectedMerk.isEmpty()) {
            switch (selectedMerk) {
                case "Samsung":
                    price = samsungVariantsPrices[selectedTypeIndex];
                    break;
                case "Apple":
                    price = appleVariantsPrices[selectedTypeIndex];
                    break;
                default:
                    price = oppoVariantsPrices[selectedTypeIndex];
                    break;
            }
        }
        txtSatuan.setText(String.format("Rp. %d", price));
    }

    private void populateTable(final String[] names, final int[] prices) {

        penjualan.getTabel().setRowCount(0);

        for (int i = 0; i < names.length; i++) {
            String[] data = {String.valueOf(i + 1), names[i], String.format("Rp. %d", prices[i])};
            penjualan.getTabel().addRow(data);
        }
    }

    private void populateCboTipe(final String[] names) {
        cboTipe.removeAllItems();
        for (String name : names) cboTipe.addItem(name);
    }

    private void populateCboWarna(final String[] names) {
        cboWarna.removeAllItems();
        for (String name : names) cboWarna.addItem(name);
    }
}
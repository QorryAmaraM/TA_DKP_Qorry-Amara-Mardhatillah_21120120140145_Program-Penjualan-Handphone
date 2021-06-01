package com.company;

import javax.swing.table.DefaultTableModel;

public class Tabel {

        private DefaultTableModel tabel = new DefaultTableModel();
        public Tabel(){

            getTabel().addColumn("No");
            getTabel().addColumn("Tipe Handphone");
            getTabel().addColumn("Harga Satuan");

        }

        public DefaultTableModel getTabel() {
            return tabel;
        }
        public void setTabel(DefaultTableModel tabel) {
            this.tabel = tabel;
        }

    }


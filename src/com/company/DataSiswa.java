package com.company;

import com.sun.corba.se.pept.transport.ConnectionCache;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataSiswa extends javax.swing.JFrame {

    Connection koneksi;

    public DataSiswa() {
        initComponente();
        koneksi = DatabaseConnection.getKoneksi("localhost","3306","root","","db_sekolah");
        showData();
    }

    DefaultTableModel dtm;
    public void showData(){
        String[] kolom = {"NO","NIS","Nama","Kelas","Jurusan"};

        dtm = new DefaultTableModel(null, kolom);
        try {
            Statement stmt = koneksi.createStatement();
            String query = "SELECT * FORM t_siswa";
            ResultSet rs = stmt.executeQuery(query);
            int no = 1;
            while (rs.next()){
                String nis = rs.getString("nis");
                String nama = rs.getString("nama");
                String kelas = rs.getString("kelas");
                String jurusan = rs.getString("jurusan");

                dtm.addRow(new String[]{no+"",nis,nama,kelas,jurusan});
                no++;
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        tbl_siswa.setModel(dtm);
    }
    private void initComponente() {
    }
}

package com.cucimotor.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "antrian")
public class Antrian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama_pelanggan")
    private String namaPelanggan;

    @Column(name = "plat_nomor")
    private String platNomor;

    @ManyToOne
    @JoinColumn(name = "layanan_id")
    private Layanan layanan;

    @Column(name = "metode_pembayaran")
    private String metodePembayaran; // CASH / QRIS

    private String status; // MENUNGGU, DIPROSES, SELESAI

    @Column(name = "tanggal_dibuat")
    private LocalDateTime tanggalDibuat = LocalDateTime.now();
}
package com.cucimotor.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "layanan")
public class Layanan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String namaLayanan;
    private Double harga;
    private Integer estimasiMenit;
}
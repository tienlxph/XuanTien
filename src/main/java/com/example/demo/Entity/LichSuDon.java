package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "lichsudon")
@AllArgsConstructor
@NoArgsConstructor
public class LichSuDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idhoadon", referencedColumnName = "id")
    private HoaDon hoaDon;

    @Column(name = "thoigian")
    private LocalDateTime thoiGian;

    @Column(name = "mota")
    private String moTa;
}
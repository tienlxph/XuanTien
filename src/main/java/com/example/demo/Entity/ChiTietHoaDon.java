package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "chi_tiet_hoa_don")
@AllArgsConstructor
@NoArgsConstructor
public class ChiTietHoaDon {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "so_luong")
    private Integer soLuong;


    @Column(name = "gia")
    private Double gia;

    @ManyToOne
    @JoinColumn(name = "id_san_pham_chi_tiet")
    SanPhamChiTiet chiTietSanPham;

    @ManyToOne
    @JoinColumn(name = "id_hoa_don", referencedColumnName = "id")
    private HoaDon hoaDon;
}
package com.example.demo.Entity;

import com.example.demo.enums.TrangThaiSanPham;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import java.util.UUID;

@Data
@Entity
@Table(name = "san_pham")
@AllArgsConstructor
@NoArgsConstructor
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;


    @Column(name = "ten")
    private String tensanpham;

    @Column(name = "ngay_tao")
    private LocalDate ngaytao;

    @Column(name = "mo_ta")
    private String mota;

    @Column(name = "so_sao")
    private double sosao;

    @Column(name = "so_luot_danh_gia")
    private int soluotdanhgia;

//    @Column(name = "so_luong")
//    private int soluong;

    @Column(name = "trang_thai")
    private TrangThaiSanPham trangthai;

//    @OneToMany(mappedBy = "san_pham")
//    @JsonIgnore
//    private List<SanPhamChiTiet> chiTietSanPhamList;


}

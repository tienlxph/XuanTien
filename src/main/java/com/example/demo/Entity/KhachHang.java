package com.example.demo.Entity;


import com.example.demo.enums.GioiTinh;
import com.example.demo.enums.TrangThai;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.UUID;

@Data
@Entity
@Table(name="KHACHHANG")

public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "soDienThoai")
    private String soDienThoai;

    @Column(name = "tenDayDu")
    private String tenDayDu;

    @Column(name = "taiKhoan")
    private String taiKhoan;

    @Column(name = "matKhau")
    private String matKhau;

    @Column(name = "email")
    private String email;


    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "ngaySinh")
    private String ngaySinh;
    @Column(name = "anh")
    private String anh;
    @Column(name = "gioiTinh")
    private GioiTinh gioiTinh;
    @Column(name = "diaChi")
    private String diaChi;
    @Column(name = "trangThai")
    private TrangThai trangThai;
}
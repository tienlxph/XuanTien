package com.example.demo.Entity;


import com.example.demo.enums.GioiTinh;
import com.example.demo.enums.Quyen;
import com.example.demo.enums.TrangThaiNguoiDung;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "nhan_vien")
@AllArgsConstructor
@NoArgsConstructor
public class NhanVien {

        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private UUID id;
        @Column(name = "ten_day_du")
        private String tenDayDu;
        @Column(name = "so_dien_thoai")
        private String sdt;
        @Column(name = "tai_khoan")
        private String taikhoan;
        @Column(name = "mat_khau")
        private String matkhau;
        @Column(name = "email")
        private String email;
        @Column(name = "ngay_sinh")
        private LocalDate ngaySinh;
        @Column(name = "anh")
        private String anh;
        @Column(name = "quyen")
        private Quyen quyen;
//        @Column(name = "gioi_tinh")
//        private GioiTinh gt;
        @Column(name = "ngay_tao")
        private  LocalDate ngayTao;
        @Column(name = "dia_chi")
        private String  diaChi;
        @Column(name = "ma_nv")
        private  String maNhanVien;
        @Column(name = "trang_thai")
        private TrangThaiNguoiDung trangThai;


    }
package com.example.demo.Entity;




import com.example.demo.enums.TrangThaiSanPham;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

import java.util.UUID;

@Data
@Entity
@Table(name = "san_pham_chi_tiet")
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;


    @Column(name = "so_luong")
    private int soluong;

    @Column(name = "khoi_luong")
    private int khoiluong;

    @Column(name = "gia")
    private double gia;

    @Column(name = "ngay_tao")
    private LocalDate ngaytao;

    @Column(name = "trang_thai")
    private TrangThaiSanPham trangthai;

    @Column(name = "ma_qr")
    private String maqr;

    @JoinColumn(name = "id_thuong_hieu")
    @ManyToOne
    private ThuongHieu thuonghieu;

    @JoinColumn(name = "id_chat_lieu")
    @ManyToOne
    private ChatLieu chatlieu;

    @JoinColumn(name = "id_loai_san_pham")
    @ManyToOne
    private LoaiSanPham loaisanpham;

    @JoinColumn(name = "id_de_giay")
    @ManyToOne
    private DeGiay degiay;

    @JoinColumn(name = "id_mui_giay")
    @ManyToOne
    private MuiGiay muigiay;

//    @JoinColumn(name = "id_anh_san_pham")
//    @ManyToOne
//    private AnhSanPham anhsanpham;

    @JoinColumn(name = "id_kich_co")
    @ManyToOne
    private KichCo kichco;

    @JoinColumn(name = "id_mau_sac")
    @ManyToOne
    private MauSac mausac;


    @ManyToOne
    @JoinColumn(name = "id_san_pham")
    SanPham sanPham;

}

package com.example.demo.Entity;

import com.example.demo.enums.LoaiHD;
import com.example.demo.enums.TrangThaiHoaDon;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "hoa_don")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_KHACHHANG", referencedColumnName = "id")
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "id_nhan_vien", referencedColumnName = "id")
    private NhanVien nhanVien;

    @Column(name = "mahoadon")
    private String maHoaDon;

    @Column(name = "ngay_tao")
    private LocalDateTime ngayTao;

    @Column(name = "ten_khach_hang")
    private String tenKhachHang;

    @Column(name = "so_dien_thoai")
    private String SDT;

    @ManyToOne
    @JoinColumn(name = "id_phieu_giam_gia", referencedColumnName = "id")
    private PhieuGiamGia phieuGiamGia;

    @Column(name = "tong_tien")
    private Double tongTien;



    @Column(name = "tien_giam_con")
    private Double tienGiamCon;

    @Column(name = "tien_ship")
    private Double tienShip;

    @Column(name = "dia_chi")
    private String diaChi;

    @Column(name = "giam_gia")
    private Double GiamGia;

    @Column(name = "loaihoadon")
    private LoaiHD loaiHoaDon;

    @Column(name = "trang_thai")
    private TrangThaiHoaDon trangthai;

    @OneToMany(mappedBy = "hoaDon")
    @JsonIgnore
    private List<LichSuDon> lichSuDon;

    @OneToMany(mappedBy = "hoaDon")
    @JsonIgnore
    private List<ChiTietHoaDon>chiTietHoaDonList;
}
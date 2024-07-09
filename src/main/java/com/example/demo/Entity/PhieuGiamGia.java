package com.example.demo.Entity;

import com.example.demo.enums.KieuGiam;
import com.example.demo.enums.TrangThai;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name="phieu_giam_gia")
@NoArgsConstructor
@AllArgsConstructor
public class PhieuGiamGia {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;



    @Column(name = "ma")
    private String ma;
    @Column(name = "ten")
    private String ten;
    @Column(name = "muc_giam_gia")
    private Integer mucGiamGia;
    @Column(name = "kieu_giam_gia")
    private KieuGiam kieuGiamGia;
    @Column(name = "so_luong")
    private Integer soLuong;
    @ManyToOne
    @JoinColumn(name = "nguoi_tao")
    NhanVien nhanVien ;


    @Column(name = "ngay_bat_dau")
    private String ngayBatDau;


//    @DateTimeFormat(pattern = "dd/MM/yyyy")
//    @Temporal(TemporalType.DATE)
    @Column(name = "ngay_ket_thuc")
    private String ngayKetThuc;


    @Column(name = "trang_thai")
    private TrangThai trangThai;
    @Column(name = "so_tien_giam_gia_toi_da")
    private Integer soTienGiamGiaToiDa;

    @Column(name = "so_tien_ap_dung_toi_thieu")
    private Integer soTienApDungToiThieu;


}

package com.example.demo.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChiTietHoaDonDto {
        private UUID id;
        private Integer soLuong;
        private Double gia;
        private UUID chiTietSanPhamId;
        private UUID hoaDonId;
    }

package com.example.demo.sevice;


import com.example.demo.Entity.PhieuGiamGia;
import com.example.demo.Repo.PhieuGiamGiaRepo;
import com.example.demo.enums.TrangThai;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
public class capNhapTrang {
    @Autowired
    PhieuGiamGiaRepo phieuGiamGiaRepo;
       private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @Scheduled(cron = "0 04 17 * * *")

    public void DoiTrangThai(){

//    List<PhieuGiamGia> phieuGiamGia = phieuGiamGiaRepo.findAll();
//     for (PhieuGiamGia phieuGiamGia1 : phieuGiamGia){
//         phieuGiamGia1.setTrangThai(TrangThai.HOẠTĐỘNG);
//
//     }
//            phieuGiamGiaRepo.saveAll(phieuGiamGia);

            LocalDate now = LocalDate.now();
            List<PhieuGiamGia> vouchers = phieuGiamGiaRepo.findAll();

            for (PhieuGiamGia voucher : vouchers) {
                LocalDate ngayKetThuc = LocalDate.parse(voucher.getNgayKetThuc().toString(), formatter);
                if (voucher.getTrangThai().equals(TrangThai.DANG_DIEN_RA) && ngayKetThuc.isEqual(now)) {
                    voucher.setTrangThai(TrangThai.DA_KETTHUC);
                    phieuGiamGiaRepo.save(voucher);
                }
            }
//
        }
//        }
}



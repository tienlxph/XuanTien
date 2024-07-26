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
@Service
public class CapNgapNgayBD {
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
            LocalDate ngayKetThuc = LocalDate.parse(voucher.getNgayBatDau().toString(), formatter);
            if (voucher.getTrangThai().equals(TrangThai.SAP_DIEN_RA) && ngayKetThuc.isEqual(now)) {
                voucher.setTrangThai(TrangThai.DANG_DIEN_RA);
                phieuGiamGiaRepo.save(voucher);
            }
        }
//            for (PhieuGiamGia voucher1 : vouchers) {
//            LocalDate ngayKetThuc = LocalDate.parse(voucher1.getNgayKetThuc(), formatter);
//            if (voucher1.getTrangThai().equals(TrangThai.KHÔNGHOẠTĐỘNG) && ngayKetThuc.isEqual(now)) {
//                voucher1.setTrangThai(TrangThai.HOẠTĐỘNG);
//                phieuGiamGiaRepo.save(voucher1);
//            }
    }
//        }
}




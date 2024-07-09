package com.example.demo.ConTroller;

import com.example.demo.Entity.NhanVien;
import com.example.demo.Entity.PhieuGiamGia;
import com.example.demo.Repo.NhanVienRepo;
import com.example.demo.Repo.PhieuGiamGiaRepo;
import com.example.demo.enums.TrangThai;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.UUID;
//
//@Controller
@RestController
@CrossOrigin("*")

public class PhieuGiamGiaController {
    @Autowired
    PhieuGiamGiaRepo phieuGiamGiaRepo;
    @Autowired
    NhanVienRepo nhanVienRepo;
    @ModelAttribute("nhanList")
    public List<NhanVien> getCategoryList(){
        return  nhanVienRepo.findAll();
    }
//    @GetMapping("/phieu-giam-gia/hien-thi")
//    public  String hienThi(Model model){
//        List<PhieuGiamGia> pgg =phieuGiamGiaRepo.findAll();
//        model.addAttribute("pg",new PhieuGiamGia());
//        model.addAttribute("list", pgg);
//        return "phieu-giam-gia/hien-thi";
//    }
@RequestMapping(value = "/phieu-giam-gia/hien-thi", method = RequestMethod.GET)
public ResponseEntity<?> phieugiamgia() throws IOException {
    return ResponseEntity.ok(phieuGiamGiaRepo.findAll());
}
    @PostMapping("/phieu-giam-gia/add")
    public PhieuGiamGia add( @RequestBody PhieuGiamGia phieuGiamGia){


        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomPart = new StringBuilder();
        Random random = new Random();
        int length = 8 + random.nextInt(3);  // Độ dài từ 8 đến 10 ký tự
        for (int i = 0; i < length; i++) {
            randomPart.append(characters.charAt(random.nextInt(characters.length())));
        }
        phieuGiamGia.setMa("GG"+ randomPart.toString());
        phieuGiamGia.setTrangThai(TrangThai.HOẠTĐỘNG);
        return  phieuGiamGiaRepo.save(phieuGiamGia);
    }
    // hàm này sẽ được chạy mỗi khi đến giờ hẹn trước
    @PatchMapping("pgg/delete/{id}")

    public PhieuGiamGia delete(@PathVariable UUID id){
        PhieuGiamGia pgg = phieuGiamGiaRepo.findById(id).orElse(null);
        if (pgg.getTrangThai() == TrangThai.HOẠTĐỘNG){ pgg.setTrangThai(TrangThai.KHÔNGHOẠTĐỘNG);
        }
        else if(pgg.getTrangThai() == TrangThai.KHÔNGHOẠTĐỘNG){ pgg.setTrangThai(TrangThai.HOẠTĐỘNG);
        }
        return phieuGiamGiaRepo.save(pgg);
    }
//    @PatchMapping("pgg/delete/{id}")
//    public PhieuGiamGia hoatDOng(@PathVariable UUID id){
//        PhieuGiamGia pgg = phieuGiamGiaRepo.findById(id).orElse(null);
//
//        pgg.setTrangThai(0);
//
//
//        return phieuGiamGiaRepo.save(pgg);
//    }
    @GetMapping("/view-update/{id}")
    public PhieuGiamGia viewUpdate(@PathVariable UUID id){


        return phieuGiamGiaRepo.findById(id).orElse(null);
    }

    @PutMapping("/phieu-giam-gia/update/{id}")
//    public PhieuGiamGia update(@RequestBody PhieuGiamGia phieuGiamGia)
//    { PhieuGiamGia pgg = phieuGiamGiaRepo.findById(id).orElse(null);
//
//
//        return  phieuGiamGiaRepo.save(pgg);
    public PhieuGiamGia update( @RequestBody PhieuGiamGia phieuGiamGia , @PathVariable  UUID id){
        PhieuGiamGia pgg = phieuGiamGiaRepo.findById(id).orElse(null);
        pgg.setTen(phieuGiamGia.getTen()) ;
        pgg.setMa(phieuGiamGia.getMa()) ;
        pgg.setKieuGiamGia(phieuGiamGia.getKieuGiamGia());
        pgg.setMucGiamGia(phieuGiamGia.getMucGiamGia());
        pgg.setSoLuong(phieuGiamGia.getSoLuong());
        pgg.setNgayBatDau(phieuGiamGia.getNgayBatDau());
        pgg.setNgayKetThuc(phieuGiamGia.getNgayKetThuc());
        pgg.setSoTienApDungToiThieu(phieuGiamGia.getSoTienApDungToiThieu());
        pgg.setSoTienGiamGiaToiDa(phieuGiamGia.getSoTienGiamGiaToiDa());
        return  phieuGiamGiaRepo.save(pgg);

    }


}

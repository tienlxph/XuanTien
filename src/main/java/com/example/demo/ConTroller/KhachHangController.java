package com.example.demo.ConTroller;

import com.example.demo.Entity.KhachHang;
import com.example.demo.Repo.KhachHangRepo;
import com.example.demo.Repo.TinhRepo;
import com.example.demo.enums.GioiTinh;
import com.example.demo.enums.TrangThai;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

//@Controller
@RequestMapping("/kh")
@RestController
@CrossOrigin("*")
public class KhachHangController {
    @Autowired
    KhachHangRepo khachHangRepo;
    @Autowired
    TinhRepo tinhRepo;

    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    @RequestMapping(value = "kh/hien-thi",method = RequestMethod.GET)
    public ResponseEntity<?> khachhang() throws IOException{

        return ResponseEntity.ok(khachHangRepo.findAll());
    }
    @PostMapping("kh/add")
    public KhachHang add(@RequestBody KhachHang khachHang){

        khachHang.setTrangThai(TrangThai.HOẠTĐỘNG);
//
        return   khachHangRepo.save(khachHang);

    }
    @PatchMapping("kh/delete/{id}")

    public KhachHang delete(@PathVariable UUID id){
        KhachHang khachHang=khachHangRepo.findById(id).orElse(null);


        if (khachHang.getTrangThai() == TrangThai.HOẠTĐỘNG){ khachHang.setTrangThai(TrangThai.KHÔNGHOẠTĐỘNG);
        }
        else if(khachHang.getTrangThai() == TrangThai.KHÔNGHOẠTĐỘNG){ khachHang.setTrangThai(TrangThai.HOẠTĐỘNG);
        }
        return khachHangRepo.save(khachHang);

    }

    @GetMapping("/view-update/{id}")
    public KhachHang viewUpdate(@PathVariable UUID id){

        return khachHangRepo.findById(id).orElse(null);
    }

    @PutMapping("/kh/update/{id}")
    public KhachHang update( @RequestBody KhachHang khachHang , @PathVariable  UUID id){
        KhachHang kh = khachHangRepo.findById(id).orElse(null);
        kh.setSoDienThoai(khachHang.getSoDienThoai());
        kh.setTenDayDu(khachHang.getTenDayDu());
        kh.setTaiKhoan(khachHang.getTaiKhoan());
        kh.setMatKhau(khachHang.getMatKhau());
        kh.setEmail(khachHang.getEmail());
        kh.setNgaySinh(khachHang.getNgaySinh());
        kh.setAnh(khachHang.getAnh());
//        kh.setGioiTinh(khachHang.getGioiTinh());
        kh.setDiaChi(khachHang.getDiaChi());


        return  khachHangRepo.save(kh);

    }
    @GetMapping("/timkiem")
    public ResponseEntity<List<KhachHang>> searchProducts(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "status", required = false) TrangThai status) {
        List<KhachHang> result = khachHangRepo.searchByKeywordAndStatus(keyword, status);
        return ResponseEntity.ok(result);
    }
}
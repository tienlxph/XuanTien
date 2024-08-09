package com.example.demo.ConTroller;


import com.example.demo.Entity.ChiTietHoaDon;
import com.example.demo.Entity.ChiTietHoaDonDto;
import com.example.demo.Entity.HoaDon;
import com.example.demo.Repo.ChiTietHoaDonRepo;
import com.example.demo.Repo.HoaDonRepo;
import com.example.demo.Repo.SanPhamChiTietRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/chitiethoadon")
@CrossOrigin("*")

public class ChiTietHoaDonController {

    @Autowired
    ChiTietHoaDonRepo chiTietHoaDonRepository;

    @Autowired
    HoaDonRepo hoaDonRepository;

    @Autowired
    SanPhamChiTietRepo chiTietSanPhamRepo;
//    @PostMapping("/add")
//    public ResponseEntity<?> addChiTietHoaDon(@RequestBody ChiTietHoaDonDto chiTietHoaDonDto) {
//        try {
//            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
//            chiTietHoaDon.setSoLuong(chiTietHoaDonDto.getSoLuong());
//            chiTietHoaDon.setGia(chiTietHoaDonDto.getGia());
//            chiTietHoaDon.setChiTietSanPham(chiTietSanPhamRepo.findById(chiTietHoaDonDto.getChiTietSanPhamId()).orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại")));
//            chiTietHoaDon.setHoaDon(hoaDonRepository.findById(chiTietHoaDonDto.getHoaDonId()).orElseThrow(() -> new RuntimeException("Hóa đơn không tồn tại")));
//
//            chiTietHoaDon = chiTietHoaDonRepository.save(chiTietHoaDon);
//
//            return ResponseEntity.ok(chiTietHoaDon);
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body("Lỗi khi thêm chi tiết hóa đơn: " + e.getMessage());
//        }
//    }

    @PostMapping("/add")
    public ResponseEntity<ChiTietHoaDon> addChiTietHoaDon(@RequestBody ChiTietHoaDon chiTietHoaDon) {
        // Kiểm tra hóa đơn tồn tại
        Optional<HoaDon> hoaDonOpt = hoaDonRepository.findById(chiTietHoaDon.getHoaDon().getId());
        if (!hoaDonOpt.isPresent()) {
            return ResponseEntity.badRequest().body(null);
        }

        // Lưu chi tiết hóa đơn
        ChiTietHoaDon savedChiTietHoaDon = chiTietHoaDonRepository.save(chiTietHoaDon);

        // Cập nhật tổng tiền cho hóa đơn
        HoaDon hoaDon = hoaDonOpt.get();
        List<ChiTietHoaDon> chiTietHoaDonList = hoaDon.getChiTietHoaDonList();
        if (chiTietHoaDonList == null) {
            chiTietHoaDonList = new ArrayList<>();
            hoaDon.setChiTietHoaDonList(chiTietHoaDonList);
        }
        chiTietHoaDonList.add(savedChiTietHoaDon);
//        hoaDon.setTongTien(hoaDon.getTongTien() + (savedChiTietHoaDon.getGia() * savedChiTietHoaDon.getSoLuong()));
//        hoaDonRepository.save(hoaDon);

        return ResponseEntity.ok(savedChiTietHoaDon);

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateChiTietHoaDon(@PathVariable UUID id, @RequestBody ChiTietHoaDonDto chiTietHoaDonDto) {
        System.out.println(id);

        try {
            ChiTietHoaDon chiTietHoaDon = chiTietHoaDonRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Chi tiết hóa đơn không tồn tại"));

            chiTietHoaDon.setSoLuong(chiTietHoaDonDto.getSoLuong());
            chiTietHoaDon.setGia(chiTietHoaDonDto.getGia()); // Nếu cần cập nhật giá

            chiTietHoaDonRepository.save(chiTietHoaDon);

            return ResponseEntity.ok().body("Số lượng sản phẩm đã được cập nhật thành công");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Lỗi khi cập nhật số lượng sản phẩm");
        }
    }
    @GetMapping(value = "/{id}")
    public ChiTietHoaDon getHoaDon(@PathVariable UUID id) {
        return chiTietHoaDonRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }



}

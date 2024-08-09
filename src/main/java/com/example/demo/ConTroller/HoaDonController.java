package com.example.demo.ConTroller;


import com.example.demo.Entity.HoaDon;
import com.example.demo.Entity.LichSuDon;
import com.example.demo.Entity.PhieuGiamGia;
import com.example.demo.Entity.SanPham;
import com.example.demo.Repo.*;
import com.example.demo.enums.LoaiHD;
import com.example.demo.enums.TrangThai;
import com.example.demo.enums.TrangThaiHoaDon;
import com.example.demo.enums.TrangThaiSanPham;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/hoadon")
public class HoaDonController {
    @Autowired
    HoaDonRepo hoadonRepo;
    @Autowired
    SanPhamRepo sanPhamRepo;
    @Autowired
    LichSuDOnRepo lichSuDOnRepo;
    @Autowired
    private ChiTietHoaDonRepo chiTietHoaDonRepo;
    @Autowired
    private HoaDonRepo hoaDonRepo;
    @Autowired
    private PhieuGiamGiaRepo phieuGiamGiaRepo;

    // API để áp dụng mã giảm giá cho hóa đơn
    @PutMapping("/hoa-don/{id}/apply-discount")
    public ResponseEntity<?> applyDiscount(@PathVariable UUID id, @RequestParam String discountCode) {
        HoaDon hoaDon = hoaDonRepo.findById(id).orElse(null);
        if (hoaDon == null) {
            return ResponseEntity.notFound().build();
        }
     ;
        Optional<PhieuGiamGia> optionalPhieuGiamGia = phieuGiamGiaRepo.findByMa(discountCode);
        if (optionalPhieuGiamGia.isEmpty()) {
            return ResponseEntity.badRequest().body("Mã giảm giá không hợp lệ.");
        }

        // Lấy PhieuGiamGia từ Optional
        PhieuGiamGia phieuGiamGia = optionalPhieuGiamGia.get();
        if (!phieuGiamGia.getTrangThai().equals(TrangThai.DANG_DIEN_RA)) {
            return ResponseEntity.badRequest().body("Mã giảm giá không hợp lệ hoặc đã hết hạn.");
        }

        // Áp dụng mã giảm giá
        double discountAmount = phieuGiamGia.getMucGiamGia();
        hoaDon.setGiamGia(discountAmount);
        hoaDon.setTienGiamCon(discountAmount);

        // Tính toán tổng tiền sau khi áp dụng giảm giá
        double totalAmount = hoaDon.getTongTien();
        double finalAmount = totalAmount - discountAmount;
        hoaDon.setTongTien(finalAmount);

        hoaDonRepo.save(hoaDon);

        return ResponseEntity.ok("Mã giảm giá đã được áp dụng thành công. Tổng tiền sau giảm giá: " + finalAmount);
    }
    @GetMapping(value = "/{id}")
    public HoaDon getHoaDon(@PathVariable UUID id) {
        return hoadonRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    @PostMapping(value = "/add")
    public HoaDon create( @RequestBody HoaDon hoaDon) throws IOException {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomPart = new StringBuilder();
        Random random = new Random();
        int length = 6  + random.nextInt(3);  // Độ dài từ 8 đến 10 ký tự
        for (int i = 0; i < length; i++) {
            randomPart.append(characters.charAt(random.nextInt(characters.length())));
        }
        hoaDon.setMaHoaDon("HD-"+ randomPart);
        hoaDon.setLoaiHoaDon(LoaiHD.TAI_QUAY);
        hoaDon.setNgayTao(LocalDateTime.now());
        hoaDon.setTrangthai(TrangThaiHoaDon.CHO_XAC_NHAN);
      return   hoadonRepo.save(hoaDon);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<HoaDon> updateHoaDon(@PathVariable UUID id, @RequestBody HoaDon updatedHoaDon) {
        Optional<HoaDon> existingHoaDonOpt = hoaDonRepo.findById(id);
        if (existingHoaDonOpt.isPresent()) {
            HoaDon existingHoaDon = existingHoaDonOpt.get();

            // Kiểm tra và khởi tạo chiTietHoaDonList nếu nó null
            if (existingHoaDon.getChiTietHoaDonList() == null) {
                existingHoaDon.setChiTietHoaDonList(new ArrayList<>());
            }

            // Cập nhật các trường khác của HoaDon nếu cần thiết
            existingHoaDon.setTenKhachHang(updatedHoaDon.getTenKhachHang());
            existingHoaDon.setSDT(updatedHoaDon.getSDT());
            existingHoaDon.setDiaChi(updatedHoaDon.getDiaChi());
            existingHoaDon.setTongTien(updatedHoaDon.getTongTien());
            existingHoaDon.setTienGiamCon(updatedHoaDon.getTienGiamCon());
            existingHoaDon.setTienShip(updatedHoaDon.getTienShip());
            existingHoaDon.setGiamGia(updatedHoaDon.getGiamGia());

            // Cập nhật chiTietHoaDonList nếu cần thiết
            if (updatedHoaDon.getChiTietHoaDonList() == null) {
                updatedHoaDon.setChiTietHoaDonList(new ArrayList<>());
            }
            existingHoaDon.getChiTietHoaDonList().clear();
            existingHoaDon.getChiTietHoaDonList().addAll(updatedHoaDon.getChiTietHoaDonList());

            HoaDon savedHoaDon = hoaDonRepo.save(existingHoaDon);
            return ResponseEntity.ok(savedHoaDon);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);        }
    }

    @RequestMapping(value = "/laydanhsach", method = RequestMethod.GET)
    public ResponseEntity<?> laydanhsach() throws IOException {

        return ResponseEntity.ok(hoadonRepo.findAll());
    }
    @RequestMapping(value = "/lichsu/laydanhsach", method = RequestMethod.GET)
    public ResponseEntity<?> laylichsu() throws IOException {
        return ResponseEntity.ok(lichSuDOnRepo.findAll());
    }
    @GetMapping(value = "/lichsu/laydanhsach/{idhoadon}")
    public List<LichSuDon> laylichsutheohoadon(@PathVariable Long idhoadon) throws IOException {
        return lichSuDOnRepo.findAll().stream()
                .filter(chiTiet -> chiTiet.getHoaDon() != null && chiTiet.getHoaDon().getId().equals(idhoadon))
                .collect(Collectors.toList());
    }
    @RequestMapping(value = "/laydschosp", method = RequestMethod.GET)
    public ResponseEntity<?> laydanhsachspc() throws IOException {
        List<SanPham> allSanPhams = sanPhamRepo.findAll();
        List<SanPham> filteredSanPhams = allSanPhams.stream()
                .filter(sanPham -> sanPham.getTrangthai().equals(TrangThaiSanPham.CHO))
                .collect(Collectors.toList());
        return ResponseEntity.ok(filteredSanPhams);
    }


    @PostMapping(value = "/addlichsu/{idhoadon}")
    public LichSuDon createH( @PathVariable UUID idhoadon) throws IOException {
        Optional<HoaDon> hoaDon = hoadonRepo.findById(idhoadon);
        if(hoaDon.get().getTrangthai().equals(TrangThaiHoaDon.CHO_XAC_NHAN)) {
            hoaDon.get().setTrangthai(TrangThaiHoaDon.CHO_GIAO);
        } else if (hoaDon.get().getTrangthai().equals(TrangThaiHoaDon.CHO_GIAO)) {
            hoaDon.get().setTrangthai(TrangThaiHoaDon.DANG_GIAO);
        } else if (hoaDon.get().getTrangthai().equals(TrangThaiHoaDon.DANG_GIAO)) {
            hoaDon.get().setTrangthai(TrangThaiHoaDon.HOAN_THANH);
        }
        hoadonRepo.save(hoaDon.get());
        LichSuDon lichSuDon = new LichSuDon();
        String staytot = "";
        if (hoaDon.get().getTrangthai().equals(TrangThaiHoaDon.CHO_GIAO)){
            staytot = "Chờ giao";
        } else  if (hoaDon.get().getTrangthai().equals(TrangThaiHoaDon.DANG_GIAO)){
            staytot = "Giao hàng";
        }else  if (hoaDon.get().getTrangthai().equals(TrangThaiHoaDon.HOAN_THANH)){
            staytot = "Hoàn thành";
        }
        lichSuDon.setThoiGian(LocalDateTime.now());
        lichSuDon.setMoTa("đơn đã được chuyển sang trạng thái sau :"+staytot);
        lichSuDon.setHoaDon(hoaDon.get());
        return lichSuDOnRepo.save(lichSuDon);
    }
    @PutMapping(value = "/updatesanpham/{id}")
    public ResponseEntity<SanPham> update( @RequestBody SanPham nSanPham,@PathVariable UUID id) {
        Optional<SanPham> OP = sanPhamRepo.findById(id);
        if (OP.isPresent()) {
            SanPham oSanPham = OP.get();
            oSanPham.setTensanpham(nSanPham.getTensanpham());
//            oSanPham.setSoluong(nSanPham.getSoluong());
            sanPhamRepo.save(oSanPham);

            return new ResponseEntity<>(oSanPham, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }}


    @PatchMapping("/change/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        Optional<HoaDon> o = hoadonRepo.findById(id);
        System.out.println(o.get().getId());
        if (o.isPresent()) {
            HoaDon existingHoaDon = o.get();


                existingHoaDon.setTrangthai(TrangThaiHoaDon.HUY);
                hoadonRepo.save(existingHoaDon);
                    LichSuDon lichSuDon = new LichSuDon();
                    lichSuDon.setThoiGian(LocalDateTime.now());
                    lichSuDon.setMoTa("đơn đã hủy ");
                    lichSuDon.setHoaDon(o.get());
                lichSuDOnRepo.save(lichSuDon);
                return new ResponseEntity<>(existingHoaDon, HttpStatus.OK);
        }
        return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);


    }
}

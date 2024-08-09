package com.example.demo.ConTroller;



import com.example.demo.Entity.HoaDon;
import com.example.demo.Entity.NhanVien;
import com.example.demo.Entity.PhieuGiamGia;

import com.example.demo.Repo.HoaDonRepo;
import com.example.demo.Repo.NhanVienRepo;
import com.example.demo.Repo.PhieuGiamGiaRepo;
import com.example.demo.enums.TrangThai;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import static java.lang.String.valueOf;

//
//@Controller
@RestController
@CrossOrigin("*")

public class PhieuGiamGiaController {

//    private final JavaMailSender emailSender;
//    public PhieuGiamGiaController(JavaMailSender emailSender) {
//        this.emailSender = emailSender;
//    }
@Autowired
private JavaMailSender emailSender;
    @Autowired
    PhieuGiamGiaRepo phieuGiamGiaRepo;
    @Autowired
    NhanVienRepo nhanVienRepo;
@Autowired
HoaDonRepo hoaDonRepo;


//    @ModelAttribute("nhanList")
//    public List<NhanVien> getCategoryList(){
//        return  nhanVienRepo.findAll();
//    }
////    @GetMapping("/phieu-giam-gia/hien-thi")
////    public  String hienThi(Model model){
////        List<PhieuGiamGia> pgg =phieuGiamGiaRepo.findAll();
////        model.addAttribute("pg",new PhieuGiamGia());
////        model.addAttribute("list", pgg);
////        return "phieu-giam-gia/hien-thi";
////    }
@GetMapping("/timkiem")
public ResponseEntity<List<PhieuGiamGia>> searchProducts(
        @RequestParam(value = "keyword", required = false) String keyword,
        @RequestParam(value = "status", required = false) TrangThai status) {
    List<PhieuGiamGia> result = phieuGiamGiaRepo.searchByKeywordAndStatus(keyword, status);
    return ResponseEntity.ok(result);
}

//    public void sendPasswordEmail(String to, String ma) {
//    SimpleMailMessage message = new SimpleMailMessage();
//    message.setTo(to);
//    message.setSubject("Tài Khoản Và Mật Khẩu");
//    message.setText("Tài Khoản: " + to + "\nMật Khẩu: " + ma);
//
////    emailSender.send(message);
//}
public void sendPasswordEmail(String to, String ma) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(to);
    message.setSubject("Tài Khoản Và Mật Khẩu");
    message.setText("Tài Khoản: " + to + "\nMật Khẩu: " );
    emailSender.send(message);
}
@RequestMapping(value = "/phieu-giam-gia/hien-thi", method = RequestMethod.GET)
public ResponseEntity<?> phieugiamgia() throws IOException {
    return ResponseEntity.ok(phieuGiamGiaRepo.findAll());
}
//    @GetMapping("/phieu-giam-gia/tim-ma")
//    public  String hienThi(Model model){
//        List<PhieuGiamGia> pgg =phieuGiamGiaRepo.findAll();
//
//        for (PhieuGiamGia Pgg: pgg
//             ) { if(pgg = Pgg.getMa() );
//
//        }
//        return "phieu-giam-gia/hien-thi";
//    }


    @PostMapping("/phieu-giam-gia/add")
    public PhieuGiamGia add( @RequestBody PhieuGiamGia phieuGiamGia){


        String st = "hangnt169";
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomPart = new StringBuilder();
        Random random = new Random();
        int length = 4 + random.nextInt(3);  // Độ dài từ 8 đến 10 ký tự
        for (int i = 0; i < length; i++) {
            randomPart.append(characters.charAt(random.nextInt(characters.length())));
        }
        LocalDate now = LocalDate.now();
        LocalDate ngayBatDau = phieuGiamGia.getNgayBatDau().toLocalDate();

        if (ngayBatDau.isEqual(now)){ phieuGiamGia.setTrangThai(TrangThai.DANG_DIEN_RA);
        }
        else if(ngayBatDau.isAfter(now)){ phieuGiamGia.setTrangThai(TrangThai.SAP_DIEN_RA);
        }
        phieuGiamGia.setMa("VC"+ randomPart.toString());
//        phieuGiamGia.setTrangThai(TrangThai.DANG_DIEN_RA);
//    sendPasswordEmail("tienle3203@gmail.com", randomPart.toString());
        return  phieuGiamGiaRepo.save(phieuGiamGia);
    }

    @PutMapping("/phieu-giam-gia/apply/{hoaDonId}")
    public ResponseEntity<?> applyDiscountCode(@PathVariable UUID hoaDonId, @RequestParam String discountCode) {
        Optional<HoaDon> optionalHoaDon = hoaDonRepo.findById(hoaDonId);
        if (!optionalHoaDon.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hóa đơn không tồn tại");
        }
//   tim
        HoaDon hoaDon = optionalHoaDon.get();
        Optional<PhieuGiamGia> optionalPhieuGiamGia = phieuGiamGiaRepo.findByMa(discountCode);
        if (!optionalPhieuGiamGia.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mã giảm giá không tồn tại");
        }
        PhieuGiamGia phieuGiamGia = optionalPhieuGiamGia.get();

        double giamGia = phieuGiamGia.getMucGiamGia();
        hoaDon.setGiamGia(giamGia);

        double tongTienHienTai = hoaDon.getTongTien();

        phieuGiamGia.setSoLuong(phieuGiamGia.getSoLuong() - 1);

        hoaDonRepo.save(hoaDon);

        return ResponseEntity.ok("Mã giảm giá đã được áp dụng thành công");
    }




    // hàm này sẽ được chạy mỗi khi đến giờ hẹn trước
    @PatchMapping("pgg/delete/{id}")

    public PhieuGiamGia delete(@PathVariable UUID id){
        PhieuGiamGia pgg = phieuGiamGiaRepo.findById(id).orElse(null);
        LocalDate now = LocalDate.now();
        LocalDate ngayBatDau = pgg.getNgayKetThuc().toLocalDate();
        if (pgg.getTrangThai() == TrangThai.DANG_DIEN_RA && ngayBatDau.isAfter(now)){ pgg.setTrangThai(TrangThai.DA_KETTHUC);

        }
        else if(pgg.getTrangThai() == TrangThai.DA_KETTHUC && ngayBatDau.isAfter(now)){ pgg.setTrangThai(TrangThai.DANG_DIEN_RA);
        }
        return phieuGiamGiaRepo.save(pgg);
    }

    @GetMapping("/view-update/{id}")
    public PhieuGiamGia viewUpdate(@PathVariable UUID id){


        return phieuGiamGiaRepo.findById(id).orElse(null);
    }

    @PutMapping("/phieu-giam-gia/update/{id}")

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

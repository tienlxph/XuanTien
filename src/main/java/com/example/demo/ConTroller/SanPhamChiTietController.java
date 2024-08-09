package com.example.demo.ConTroller;


import com.example.demo.Entity.*;
import com.example.demo.Repo.*;

import com.example.demo.dto.SizeRequestlDDto;
import com.example.demo.enums.TrangThaiSanPham;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/chitietsanpham")
public class SanPhamChiTietController {
    @Autowired
    SanPhamChiTietRepo chiTietSanPhamRepo;
    @Autowired
    ChatLieuRepo chatLieuRepo;
    @Autowired
    ThuongHieuRepo thuongHieuRepo;
    @Autowired
    LoaiSanPhamRepo loaiSanPhamRepo;
    @Autowired
    MuiGiayRepo muiGiayRepo;
    @Autowired
    DeGiayRepo deGiayRepo;
    @Autowired
    KichCoRepo kichCoRepo;
    @Autowired
    MauSacRepo mauSacRepo;
    @Autowired
    private SanPhamRepo sanPhamRepo;

    @GetMapping(value = "/active")
    public List<SanPhamChiTiet> getActiveChiTietSanPham() {
        List<SanPhamChiTiet> allProducts = chiTietSanPhamRepo.findAll();
        return allProducts.stream()
                .filter(product -> product.getTrangthai() == TrangThaiSanPham.DANG_BAN )
                .collect(Collectors.toList());
    }
    @RequestMapping(value = "/laydschatlieu" , method = RequestMethod.GET)
    public ResponseEntity<?> laydschatlieu() throws IOException {
        return ResponseEntity.ok(chatLieuRepo.findAll());
    }
    @RequestMapping(value = "/laydsthuonghieu" , method = RequestMethod.GET)
    public ResponseEntity<?> laydsthuonghieu() throws IOException {
        return ResponseEntity.ok(thuongHieuRepo.findAll());
    }
    @RequestMapping(value = "/laydsloaisp" , method = RequestMethod.GET)
    public ResponseEntity<?> laydsloai() throws IOException {
        return ResponseEntity.ok(loaiSanPhamRepo.findAll());
    }
    @RequestMapping(value = "/laydsmuigy" , method = RequestMethod.GET)
    public ResponseEntity<?> laydsmuigi() throws IOException {
        return ResponseEntity.ok(muiGiayRepo.findAll());
    }
    @RequestMapping(value = "/laydsdegiay" , method = RequestMethod.GET)
    public ResponseEntity<?> laydsdegiay() throws IOException {
        return ResponseEntity.ok(deGiayRepo.findAll());
    }@RequestMapping(value = "/laydssize" , method = RequestMethod.GET)
    public ResponseEntity<?> laydssize() throws IOException {
        return ResponseEntity.ok(kichCoRepo.findAll());
    }@RequestMapping(value = "/laydscolors" , method = RequestMethod.GET)
    public ResponseEntity<?> laydscolores() throws IOException {
        return ResponseEntity.ok(mauSacRepo.findAll());
    }

    @GetMapping(value = "/{id}")
    public SanPhamChiTiet getChiTietSanPham(@PathVariable UUID id) {
        return chiTietSanPhamRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/laydanhsach", method = RequestMethod.GET)
    public ResponseEntity<?> laydanhsach() throws IOException {
        return ResponseEntity.ok(chiTietSanPhamRepo.findAll());
    }
    @PostMapping("/addsize/{id_san_pham}/{id_chat_lieu}/{id_thuong_hieu}/{id_loai_san_pham}/{id_mui_giay}/{id_de_giay}")
    public void  addSizes(@RequestBody SizeRequestlDDto sizeRequestIDDto,
//                          @RequestBody ColorRequestIDDto colorRequestIDDto,
                          @PathVariable UUID id_san_pham,
                          @PathVariable UUID id_chat_lieu,
                          @PathVariable UUID id_thuong_hieu,
                          @PathVariable UUID id_loai_san_pham,
                          @PathVariable UUID id_mui_giay,
                          @PathVariable UUID id_de_giay
    ) {
        List<UUID> sizeIds = sizeRequestIDDto.getSizeIds();
        List<UUID> colorIds = sizeRequestIDDto.getColorIds();
        System.out.println("Size IDs to add: " + sizeIds);
        System.out.println("Color IDs to add: " + colorIds);
        System.out.println("chat lieu id: " + id_chat_lieu);
        Optional<SanPham> sanPham = sanPhamRepo.findById(id_san_pham);
        Optional<ChatLieu> chatLieu = chatLieuRepo.findById(id_chat_lieu);
        Optional<ThuongHieu> thuongHieu = thuongHieuRepo.findById(id_thuong_hieu);
        Optional<LoaiSanPham> loaiSanPham = loaiSanPhamRepo.findById(id_loai_san_pham);
        Optional<DeGiay> deGiay = deGiayRepo.findById(id_de_giay);
        Optional<MuiGiay> muiGiay = muiGiayRepo.findById(id_mui_giay);
        List<KichCo> kichCos = kichCoRepo.findAllById(sizeIds);
        List<MauSac> mausacs = mauSacRepo.findAllById(colorIds);
        int s =0;
        for(KichCo kc : kichCos){
            System.out.println( "KichCo: " + kc.getTen() + "id: " + kc.getId() + " trang thái: " + kc.getTrangthai()  );
            for(MauSac ms : mausacs){

                //Thêm chitiet
                SanPhamChiTiet chiTietSanPham = new SanPhamChiTiet();
                chiTietSanPham.setSanPham(sanPham.get());
                chiTietSanPham.setChatlieu(chatLieu.get());
                chiTietSanPham.setThuonghieu(thuongHieu.get());
                chiTietSanPham.setLoaisanpham(loaiSanPham.get());
                chiTietSanPham.setDegiay(deGiay.get());
                chiTietSanPham.setMuigiay(muiGiay.get());
                chiTietSanPham.setNgaytao(LocalDate.now());
                chiTietSanPham.setTrangthai(TrangThaiSanPham.CHO);
                chiTietSanPham.setSoluong(1);
                s=s+1;
                chiTietSanPham.setKichco(kc);
                chiTietSanPham.setMausac(ms);
                chiTietSanPhamRepo.save(chiTietSanPham);
            }
        }
//        sanPham.get().setSoluong(s);
        sanPhamRepo.save(sanPham.get());
    }




//    @PostMapping(value = "/addctsp")
//    public ChiTietSanPham create(@RequestBody ChiTietSanPham chiTietSanPham) {
//        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
//        StringBuilder randomPart = new StringBuilder();
//        chiTietSanPham.setMaqr("SP"+randomPart.toString());
//        chiTietSanPham.setNgaytao(LocalDateTime.now());
//        chiTietSanPham.setTrangthai(TrangThaiSanPham.Chờ);
//        chiTietSanPham.getChatlieu();
//        return chiTietSanPhamRepo.save(chiTietSanPham);
//    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<SanPhamChiTiet> update(@RequestBody SanPhamChiTiet nchiTietSanPham, @PathVariable UUID id) {

        Optional<SanPhamChiTiet> ODP = chiTietSanPhamRepo.findById(id);
//        if (ODP.isPresent()) {
        SanPhamChiTiet oldChiTietSanPham = ODP.get();
        int osl=oldChiTietSanPham.getSoluong();
        oldChiTietSanPham.setThuonghieu(nchiTietSanPham.getThuonghieu());
        oldChiTietSanPham.setMaqr(nchiTietSanPham.getMaqr());
        oldChiTietSanPham.setGia(nchiTietSanPham.getGia());
        oldChiTietSanPham.setDegiay(nchiTietSanPham.getDegiay());
//            oldChiTietSanPham.setMausac(nchiTietSanPham.getMausac());
//            oldChiTietSanPham.setKichco(nchiTietSanPham.getKichco());
        oldChiTietSanPham.setMuigiay(nchiTietSanPham.getMuigiay());
        oldChiTietSanPham.setKhoiluong(nchiTietSanPham.getKhoiluong());
        oldChiTietSanPham.setSoluong(nchiTietSanPham.getSoluong());
        oldChiTietSanPham.setTrangthai(TrangThaiSanPham.DANG_BAN);

        chiTietSanPhamRepo.save(oldChiTietSanPham);
        Optional<SanPham> sanPham = sanPhamRepo.findById(oldChiTietSanPham.getSanPham().getId());
        System.out.println(osl + " " + nchiTietSanPham.getSoluong());
//        if(osl>= nchiTietSanPham.getSoluong()){
//            sanPham.get().setSoluong(sanPham.get().getSoluong()+(osl-nchiTietSanPham.getSoluong()));
//
//        }else{
//            sanPham.get().setSoluong(sanPham.get().getSoluong()+(nchiTietSanPham.getSoluong()-osl));
//
//        }
        sanPhamRepo.save(sanPham.get());
        return new ResponseEntity<>(oldChiTietSanPham, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }

    }

    @GetMapping(value = "/change/{id}")
    public ResponseEntity<?> thaydoitrangthai(@PathVariable UUID id) {
        Optional<SanPhamChiTiet> chiTietSanPham = chiTietSanPhamRepo.findById(id);
        System.out.println(chiTietSanPham.get().getTrangthai());
        if (chiTietSanPham.isPresent()) {
            SanPhamChiTiet dP = chiTietSanPham.get();
            if (dP.getTrangthai().equals(TrangThaiSanPham.DANG_BAN)) {
                dP.setTrangthai(TrangThaiSanPham.NGUNG);
                chiTietSanPhamRepo.save(dP);
            }
//            else if (dP.getTrangthai() == 2) {
//                dP.setTrangthai(1);
//                chiTietSanPhamRepo.save(dP);
//            }
        }
//return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(chiTietSanPhamRepo.findById(id));
    }
    @GetMapping("/sp/{id_san_pham}")
    public List<SanPhamChiTiet> getChiTietSanPhamBySanPhamId(@PathVariable UUID id_san_pham) {
        return chiTietSanPhamRepo.findAll().stream()
                .filter(chiTiet -> chiTiet.getSanPham() != null && chiTiet.getSanPham().getId().equals(id_san_pham))
                .collect(Collectors.toList());
    }

}

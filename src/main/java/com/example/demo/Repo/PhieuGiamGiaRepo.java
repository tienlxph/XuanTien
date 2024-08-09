package com.example.demo.Repo;


import com.example.demo.Entity.PhieuGiamGia;
import com.example.demo.enums.TrangThai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PhieuGiamGiaRepo extends JpaRepository<PhieuGiamGia, UUID> {
    @Query("SELECT sp FROM PhieuGiamGia sp WHERE " +
            "(:keyword IS NULL OR LOWER(sp.ten) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "CAST(sp.soLuong AS string) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "CAST(sp.ngayBatDau AS string) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "AND (:status IS NULL OR sp.trangThai = :status)")
    List<PhieuGiamGia> searchByKeywordAndStatus(
            @Param("keyword") String keyword,
            @Param("status") TrangThai status);

    Optional<PhieuGiamGia> findByMa(String ma);
}

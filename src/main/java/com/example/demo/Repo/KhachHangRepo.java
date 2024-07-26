package com.example.demo.Repo;


import com.example.demo.Entity.KhachHang;
import com.example.demo.enums.TrangThai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface KhachHangRepo extends JpaRepository<KhachHang, UUID> {
    @Query("SELECT sp FROM KhachHang sp WHERE " +
            "(:keyword IS NULL OR LOWER(sp.tenDayDu) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "CAST(sp.email AS string) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "CAST(sp.diaChi AS string) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "AND (:status IS NULL OR sp.trangThai = :status)")
    List<KhachHang> searchByKeywordAndStatus(
            @Param("keyword") String keyword,
            @Param("status") TrangThai status);

}
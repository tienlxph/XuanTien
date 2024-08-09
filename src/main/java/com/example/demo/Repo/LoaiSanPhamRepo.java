package com.example.demo.Repo;


import com.example.demo.Entity.LoaiSanPham;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LoaiSanPhamRepo extends JpaRepository<LoaiSanPham, UUID> {
}

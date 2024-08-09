package com.example.demo.Repo;

import com.example.demo.Entity.SanPhamChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SanPhamChiTietRepo extends JpaRepository<SanPhamChiTiet, UUID> {
}

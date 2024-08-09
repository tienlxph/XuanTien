package com.example.demo.Repo;


import com.example.demo.Entity.AnhSanPham;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AnhSanPhamRepo extends JpaRepository<AnhSanPham, UUID> {
}

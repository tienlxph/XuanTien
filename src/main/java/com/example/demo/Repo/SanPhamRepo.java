package com.example.demo.Repo;
import com.example.demo.Entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SanPhamRepo extends JpaRepository<SanPham, UUID> {
   
}

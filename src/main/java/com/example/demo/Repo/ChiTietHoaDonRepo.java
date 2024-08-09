package com.example.demo.Repo;



import com.example.demo.Entity.ChiTietHoaDon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChiTietHoaDonRepo extends JpaRepository<ChiTietHoaDon, UUID> {
}

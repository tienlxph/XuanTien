package com.example.demo.Repo;


import com.example.demo.Entity.PhieuGiamGia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PhieuGiamGiaRepo extends JpaRepository<PhieuGiamGia, UUID> {
}

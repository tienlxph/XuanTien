package com.example.demo.Repo;

import com.example.demo.Entity.ThuongHieu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ThuongHieuRepo extends JpaRepository<ThuongHieu, UUID> {
}

package com.example.demo.Repo;

import com.example.demo.Entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface HoaDonRepo extends JpaRepository<HoaDon, UUID> {

}

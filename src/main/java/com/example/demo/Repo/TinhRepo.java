package com.example.demo.Repo;

import com.example.demo.Entity.Tinh;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TinhRepo extends JpaRepository<Tinh, UUID> {
}
package com.example.demo.Repo;

import com.example.demo.Entity.MauSac;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MauSacRepo extends JpaRepository<MauSac, UUID> {
}

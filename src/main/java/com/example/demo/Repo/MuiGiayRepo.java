package com.example.demo.Repo;


import com.example.demo.Entity.MuiGiay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MuiGiayRepo extends JpaRepository<MuiGiay, UUID> {
}

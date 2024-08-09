package com.example.demo.Repo;


import com.example.demo.Entity.KichCo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface KichCoRepo extends JpaRepository<KichCo, UUID> {
}

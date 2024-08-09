package com.example.demo.Repo;



import com.example.demo.Entity.DeGiay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeGiayRepo extends JpaRepository<DeGiay, UUID> {
}

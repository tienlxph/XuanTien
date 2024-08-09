package com.example.demo.Repo;

import com.example.demo.Entity.ChatLieu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChatLieuRepo extends JpaRepository<ChatLieu, UUID> {
}

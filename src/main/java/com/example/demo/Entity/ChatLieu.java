package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "chat_lieu")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatLieu {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    @Column(name = "ten")
    private String ten;
    @Column(name = "trang_thai")
    private int trangthai;
//    @OneToMany (mappedBy = "chat_lieu")
//    @JsonIgnore
//    private List<SanPhamChiTiet> chiTietSanPhams;
}

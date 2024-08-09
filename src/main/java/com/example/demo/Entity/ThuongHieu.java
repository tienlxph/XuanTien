package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "thuong_hieu")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ThuongHieu {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "ten")
    private String ten;
    @Column(name = "trang_thai")
    private int trangthai;
}

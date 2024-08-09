package com.example.demo.Entity;

public class DiscountResponse {

        private double giamGia;
        private double soTienSauKhiGiam;

        public DiscountResponse(double giamGia, double soTienSauKhiGiam) {
            this.giamGia = giamGia;
            this.soTienSauKhiGiam = soTienSauKhiGiam;
        }

        // Getter và Setter
        public double getGiamGia() {
            return giamGia;
        }

        public void setGiamGia(double giamGia) {
            this.giamGia = giamGia;
        }

        public double getSoTienSauKhiGiam() {
            return soTienSauKhiGiam;
        }

        public void setSoTienSauKhiGiam(double soTienSauKhiGiam) {
            this.soTienSauKhiGiam = soTienSauKhiGiam;
        }

}

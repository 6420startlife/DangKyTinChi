package com.ptithcm.dangkytinchi.response;

public class ResponseUser {
    private String masv;
    private String ho;
    private String ten;
    private String phai;
    private String diachi;
    private String ngaysinh;
    private String malop;

    public ResponseUser(String masv, String ho, String ten, String phai, String diachi, String ngaysinh, String malop) {
        this.masv = masv;
        this.ho = ho;
        this.ten = ten;
        this.phai = phai;
        this.diachi = diachi;
        this.ngaysinh = ngaysinh;
        this.malop = malop;
    }

    public String getMasv() {
        return masv;
    }

    public void setMasv(String masv) {
        this.masv = masv;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getPhai() {
        return phai;
    }

    public void setPhai(String phai) {
        this.phai = phai;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getMalop() {
        return malop;
    }

    public void setMalop(String malop) {
        this.malop = malop;
    }

    public String getHoVaTen() {
        return this.ho + " " + this.ten;
    }
}

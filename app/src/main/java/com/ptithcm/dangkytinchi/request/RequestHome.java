package com.ptithcm.dangkytinchi.request;

public class RequestHome {
    private String maLTC;
    private String maSV;
    private int hocky;
    private int diemCC;
    private int diemGK;
    private int diemCK;

    public RequestHome(String maLTC, String maSV) {
        this.maLTC = maLTC;
        this.maSV = maSV;
        hocky = 1;
        diemCC = 0;
        diemCK = 0;
        diemGK = 0;
    }

    public String getMaLTC() {
        return maLTC;
    }

    public void setMaLTC(String maLTC) {
        this.maLTC = maLTC;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public int getHocky() {
        return hocky;
    }

    public void setHocky(int hocky) {
        this.hocky = hocky;
    }

    public int getDiemCC() {
        return diemCC;
    }

    public void setDiemCC(int diemCC) {
        this.diemCC = diemCC;
    }

    public int getDiemGK() {
        return diemGK;
    }

    public void setDiemGK(int diemGK) {
        this.diemGK = diemGK;
    }

    public int getDiemCK() {
        return diemCK;
    }

    public void setDiemCK(int diemCK) {
        this.diemCK = diemCK;
    }
}

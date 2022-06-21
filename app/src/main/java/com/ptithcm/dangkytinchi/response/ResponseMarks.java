package com.ptithcm.dangkytinchi.response;

public class ResponseMarks {
    private String tenMH;
    private String maSV;
    private int diemCC;
    private int diemGK;
    private int diemCK;

    public ResponseMarks(String maLTC, String maSV, int diemCC, int diemGK, int diemCK) {
        this.maSV = maSV;
        this.diemCC = diemCC;
        this.diemGK = diemGK;
        this.diemCK = diemCK;
    }

    public String getTenMH() {
        return tenMH;
    }

    public void setTenMH(String tenMH) {
        this.tenMH = tenMH;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
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

    public float averageDiem() {
        return (float) ((0.1 * diemCC) + (0.3 * diemGK) + (0.6 * diemCK));
    }
}

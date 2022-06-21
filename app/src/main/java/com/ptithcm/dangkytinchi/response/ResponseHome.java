package com.ptithcm.dangkytinchi.response;

public class ResponseHome {
    private String maLTC;
    private String nienKhoa;
    private int hocKy;
    private String maMH;
    private String maGV;
    private String maKhoa;
    private int stt;
    private boolean isCheck;

    public ResponseHome() {
        isCheck = false;
    }

    public ResponseHome(String maLTC, String nienKhoa, int hocKy, String maMH, String maGV, String maKhoa, int stt) {
        this.maLTC = maLTC;
        this.nienKhoa = nienKhoa;
        this.hocKy = hocKy;
        this.maMH = maMH;
        this.maGV = maGV;
        this.maKhoa = maKhoa;
        this.stt = stt;
        isCheck = false;
    }

    public String getMaLTC() {
        return maLTC;
    }

    public void setMaLTC(String maLTC) {
        this.maLTC = maLTC;
    }

    public String getNienKhoa() {
        return nienKhoa;
    }

    public void setNienKhoa(String nienKhoa) {
        this.nienKhoa = nienKhoa;
    }

    public int getHocKy() {
        return hocKy;
    }

    public void setHocKy(int hocKy) {
        this.hocKy = hocKy;
    }

    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public String getMaGV() {
        return maGV;
    }

    public void setMaGV(String maGV) {
        this.maGV = maGV;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}

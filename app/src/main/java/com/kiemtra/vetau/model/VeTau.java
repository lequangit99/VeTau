package com.kiemtra.vetau.model;

public class VeTau {
    private int mId;
    private String mGaDi;
    private String mGaDen;
    private float mDonGia;
    private boolean mLoaiVe;

    public VeTau() {
    }

    public VeTau(String mGaDi, String mGaDen, float mDonGia, boolean mLoaiVe) {
        this.mGaDi = mGaDi;
        this.mGaDen = mGaDen;
        this.mDonGia = mDonGia;
        this.mLoaiVe = mLoaiVe;
    }

    public VeTau(int mId, String mGaDi, String mGaDen, float mDonGia, boolean mLoaiVe) {
        this.mId = mId;
        this.mGaDi = mGaDi;
        this.mGaDen = mGaDen;
        this.mDonGia = mDonGia;
        this.mLoaiVe = mLoaiVe;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmGaDi() {
        return mGaDi;
    }

    public void setmGaDi(String mGaDi) {
        this.mGaDi = mGaDi;
    }

    public String getmGaDen() {
        return mGaDen;
    }

    public void setmGaDen(String mGaDen) {
        this.mGaDen = mGaDen;
    }

    public float getmDonGia() {
        return mDonGia;
    }

    public void setmDonGia(float mDonGia) {
        this.mDonGia = mDonGia;
    }

    public boolean ismLoaiVe() {
        return mLoaiVe;
    }

    public void setmLoaiVe(boolean mLoaiVe) {
        this.mLoaiVe = mLoaiVe;
    }
}

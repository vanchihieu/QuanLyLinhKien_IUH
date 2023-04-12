package model;

public class BangDiem {
	private int ma;
	private String maSinhVien;
	private float TiengAnh, TinHoc, GDTC;

	public BangDiem() {
	}

	public BangDiem(int ma, String maSinhVien, float tiengAnh, float tinHoc, float gDTC) {
		this.ma = ma;
		this.maSinhVien = maSinhVien;
		TiengAnh = tiengAnh;
		TinHoc = tinHoc;
		GDTC = gDTC;
	}

	public int getMa() {
		return ma;
	}

	public void setMa(int ma) {
		this.ma = ma;
	}

	public String getMaSinhVien() {
		return maSinhVien;
	}

	public void setMaSinhVien(String maSinhVien) {
		this.maSinhVien = maSinhVien;
	}

	public float getTiengAnh() {
		return TiengAnh;
	}

	public void setTiengAnh(float tiengAnh) {
		TiengAnh = tiengAnh;
	}

	public float getTinHoc() {
		return TinHoc;
	}

	public void setTinHoc(float tinHoc) {
		TinHoc = tinHoc;
	}

	public float getGDTC() {
		return GDTC;
	}

	public void setGDTC(float gDTC) {
		GDTC = gDTC;
	}

}

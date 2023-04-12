package model;

public class SinhVien {
	private String MaSinhVien, HoTen, Email, DiaChi, SoDT;
	private int GioiTinh;
	private byte[] Hinh;

	public SinhVien() {
	}

	public SinhVien(String maSinhVien, String hoTen, String email, String diaChi, String soDT, int gioiTinh,
			byte[] hinh) {
		MaSinhVien = maSinhVien;
		HoTen = hoTen;
		Email = email;
		DiaChi = diaChi;
		SoDT = soDT;
		GioiTinh = gioiTinh;
		Hinh = hinh;
	}

	public String getMaSinhVien() {
		return MaSinhVien;
	}

	public void setMaSinhVien(String maSinhVien) {
		MaSinhVien = maSinhVien;
	}

	public String getHoTen() {
		return HoTen;
	}

	public void setHoTen(String hoTen) {
		HoTen = hoTen;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getDiaChi() {
		return DiaChi;
	}

	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}

	public String getSoDT() {
		return SoDT;
	}

	public void setSoDT(String soDT) {
		SoDT = soDT;
	}

	public int getGioiTinh() {
		return GioiTinh;
	}

	public void setGioiTinh(int gioiTinh) {
		GioiTinh = gioiTinh;
	}

	public byte[] getHinh() {
		return Hinh;
	}

	public void setHinh(byte[] hinh) {
		Hinh = hinh;
	}

}

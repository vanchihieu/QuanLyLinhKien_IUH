package dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import helpers.Database;
import model.NguoiDung;
import model.SinhVien;

public class SinhVienDao {
	public boolean insert(SinhVien sv) throws Exception {

		String sql = "INSERT INTO dbo.SinhVien(MaSinhVien,HoTen,Email,SoDT,GioiTinh,DiaChi,Hinh)"
				+ " VALUES(?,?,?,?,?,?,?)";
		try (Connection con = Database.openConnection(); PreparedStatement pstmt = con.prepareStatement(sql);

		) {
			pstmt.setString(1, sv.getMaSinhVien());
			pstmt.setString(2, sv.getHoTen());
			pstmt.setString(3, sv.getEmail());
			pstmt.setString(4, sv.getSoDT());
			pstmt.setInt(5, sv.getGioiTinh());
			pstmt.setString(6, sv.getDiaChi());
			if (sv.getHinh() != null) {
				Blob hinh = new SerialBlob(sv.getHinh());
				pstmt.setBlob(7, hinh);
			} else {
				Blob hinh = null;
				pstmt.setBlob(7, hinh);

			}
			return pstmt.executeUpdate() > 0;
		}
	}

	public boolean update(SinhVien sv) throws Exception {
		String sql = "UPDATE dbo.SinhVien" + "	SET HoTen = ?,Email = ?,SoDT = ?,GioiTinh =?,DiaChi = ?,Hinh = ?"
				+ " where MaSinhVien = ?";
		try (Connection con = Database.openConnection(); PreparedStatement pstmt = con.prepareStatement(sql);

		) {
			pstmt.setString(7, sv.getMaSinhVien());
			pstmt.setString(1, sv.getHoTen());
			pstmt.setString(2, sv.getEmail());
			pstmt.setString(3, sv.getSoDT());
			pstmt.setInt(4, sv.getGioiTinh());
			pstmt.setString(5, sv.getDiaChi());
			if (sv.getHinh() != null) {
				Blob hinh = new SerialBlob(sv.getHinh());
				pstmt.setBlob(6, hinh);
			} else {
				Blob hinh = null;
				pstmt.setBlob(6, hinh);

			}
			return pstmt.executeUpdate() > 0;
		}
	}

	public boolean delete(String maSinhVien) throws Exception {
		String sql = "delete from sinhvien" + " where MaSinhVien = ?";
		try (Connection con = Database.openConnection(); PreparedStatement pstmt = con.prepareStatement(sql);

		) {
			pstmt.setString(1, maSinhVien);
			return pstmt.executeUpdate() > 0;
		}
	}

	public SinhVien findById(String maSinhVien) throws Exception {
		String sql = "select * from sinhvien where maSinhVien = ?";
		try (Connection con = Database.openConnection(); PreparedStatement pstmt = con.prepareStatement(sql);

		) {
			pstmt.setString(1, maSinhVien);
			try (ResultSet rs = pstmt.executeQuery();) {
				if (rs.next()) {
					SinhVien sv = new SinhVien();
					sv.setMaSinhVien(rs.getString("masinhvien"));
					sv.setHoTen(rs.getString("hoten"));
					sv.setEmail(rs.getString("email"));
					sv.setSoDT(rs.getString("soDT"));
					sv.setDiaChi(rs.getString("diaChi"));
					sv.setGioiTinh(rs.getInt("gioiTinh"));
					Blob blob = rs.getBlob("hinh");
					if(blob != null) {
						sv.setHinh(blob.getBytes(1, (int) blob.length()));
					}
					return sv;
				}
			}
			return null;
		}
	}

	public List<SinhVien> findAll() throws Exception {
		String sql = "select * from sinhvien";
		try (Connection con = Database.openConnection(); PreparedStatement pstmt = con.prepareStatement(sql);

		) {
			try (ResultSet rs = pstmt.executeQuery();) {
				List<SinhVien> list = new ArrayList<>();
				while (rs.next()) {
					SinhVien sv = createSinhVien(rs);
//					SinhVien sv = new SinhVien();
//					sv.setMaSinhVien(rs.getString("masinhvien"));
//					sv.setHoTen(rs.getString("hoten"));
//					sv.setEmail(rs.getString("email"));
//					sv.setSoDT(rs.getString("soDT"));
//					sv.setDiaChi(rs.getString("diaChi"));
//					sv.setGioiTinh(rs.getInt("gioiTinh"));
//					Blob blob = rs.getBlob("hinh");
//					sv.setHinh(blob.getBytes(0, (int) blob.length()));
					list.add(sv);
				}
				return list;
			}
		}
	}

	private SinhVien createSinhVien(final ResultSet rs) throws SQLException {
		SinhVien sv = new SinhVien();
		sv.setMaSinhVien(rs.getString("masinhvien"));
		sv.setHoTen(rs.getString("hoten"));
		sv.setEmail(rs.getString("email"));
		sv.setSoDT(rs.getString("soDT"));
		sv.setDiaChi(rs.getString("diaChi"));
		sv.setGioiTinh(rs.getInt("gioiTinh"));
		Blob blob = rs.getBlob("hinh");
		if(blob != null) {
			sv.setHinh(blob.getBytes(1, (int) blob.length()));
		}
		return sv;
	}
}

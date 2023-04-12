package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import helpers.Database;
import model.NguoiDung;

public class NguoiDungDao {
	public NguoiDung checkLogin(String tenDangNhap, String matKhau) throws Exception {
		String sql = "Select tenDangNhap, matKhau, vaitro from nguoidung " + " where tendangnhap = ? and matKhau = ?";
		try (Connection con = Database.openConnection(); PreparedStatement pstmt = con.prepareStatement(sql);

		) {
			pstmt.setString(1, tenDangNhap);
			pstmt.setString(2, matKhau);
			try (ResultSet rs = pstmt.executeQuery();) {
				if (rs.next()) {
					NguoiDung nd = new NguoiDung();
					nd.setTenDangNhap(tenDangNhap);
					nd.setVaiTro(rs.getString("vaitro"));
					return nd;
				}
			}
		}
		return null;
	}
}

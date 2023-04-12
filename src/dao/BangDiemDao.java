package dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import helpers.Database;
import model.BangDiem;
import model.SinhVien;

public class BangDiemDao {
	public boolean insert(BangDiem bd) throws Exception {

		String sql = "INSERT INTO [dbo].[BangDiem]([MaSinhVien],[TiengAnh],[TinHoc],[GDTC]) values(?,?,?,?)";

		try (Connection con = Database.openConnection(); PreparedStatement pstmt = con.prepareStatement(sql);

		) {

			pstmt.setString(1, bd.getMaSinhVien());
			pstmt.setFloat(2, bd.getTiengAnh());
			pstmt.setFloat(3, bd.getTinHoc());
			pstmt.setFloat(4, bd.getGDTC());

			return pstmt.executeUpdate() > 0;
		}
	}

	public boolean update(BangDiem bd) throws Exception {
		// can co khoang trang o dau set, where
		String sql = "UPDATE [dbo].[BangDiem]" + "	SET [TiengAnh] = ?,[TinHoc] = ?,[GDTC] = ?"
				+ "	WHERE [MaSinhVien] = ?";

		try (Connection con = Database.openConnection(); PreparedStatement pstmt = con.prepareStatement(sql);

		) {

			pstmt.setString(4, bd.getMaSinhVien());
			pstmt.setFloat(1, bd.getTiengAnh());
			pstmt.setFloat(2, bd.getTinHoc());
			pstmt.setFloat(3, bd.getGDTC());

			return pstmt.executeUpdate() > 0;
		}
	}

	public boolean deleteByMaSinhVien(String maSinhVien) throws Exception {
		String sql = "delete from bangdiem " + "	WHERE [MaSinhVien] = ?";

		try (Connection con = Database.openConnection(); PreparedStatement pstmt = con.prepareStatement(sql);

		) {

			pstmt.setString(1, maSinhVien);

			return pstmt.executeUpdate() > 0;
		}
	}

	public BangDiem findByMaSinhVien(String maSinhVien) throws Exception {
		String sql = "select * from bangdiem WHERE [MaSinhVien] = ?";

		try (Connection con = Database.openConnection(); PreparedStatement pstmt = con.prepareStatement(sql);

		) {

			pstmt.setString(1, maSinhVien);
			try (ResultSet rs = pstmt.executeQuery();) {
				if (rs.next()) {
					BangDiem bd = new BangDiem();
					bd.setMa(rs.getInt("Ma"));
					bd.setMaSinhVien(rs.getString("maSinhVien"));
					bd.setTiengAnh(rs.getFloat("TiengAnh"));
					bd.setTinHoc(rs.getFloat("TinHoc"));
					bd.setGDTC(rs.getFloat("GDTC"));

					return bd;
				}
			}
			return null;
		}
	}
	
	public List<BangDiem> findAll() throws Exception {
		String sql = "select * from bangdiem ";

		try (Connection con = Database.openConnection(); PreparedStatement pstmt = con.prepareStatement(sql);

		) {

			try (ResultSet rs = pstmt.executeQuery();) {
				List<BangDiem> list = new ArrayList<>();
				while (rs.next()) {
					BangDiem bd = new BangDiem();
					bd.setMa(rs.getInt("Ma"));
					bd.setMaSinhVien(rs.getString("maSinhVien"));
					bd.setTiengAnh(rs.getFloat("TiengAnh"));
					bd.setTinHoc(rs.getFloat("TinHoc"));
					bd.setGDTC(rs.getFloat("GDTC"));
					
					list.add(bd);
				}
				return list;
			}
		}
	}
	
	public List<BangDiem> findTop(int top) throws Exception {
		String sql = "select top " + top +" *, (TiengAnh + TinHoc + GDTC)/3 as DTB"
				 + " from BangDiem order by dtb desc ";

		try (Connection con = Database.openConnection(); PreparedStatement pstmt = con.prepareStatement(sql);

		) {

			try (ResultSet rs = pstmt.executeQuery();) {
				List<BangDiem> list = new ArrayList<>();
				while (rs.next()) {
					BangDiem bd = new BangDiem();
					bd.setMa(rs.getInt("Ma"));
					bd.setMaSinhVien(rs.getString("maSinhVien"));
					bd.setTiengAnh(rs.getFloat("TiengAnh"));
					bd.setTinHoc(rs.getFloat("TinHoc"));
					bd.setGDTC(rs.getFloat("GDTC"));
					
					list.add(bd);
				}
				return list;
			}
		}
	}
}

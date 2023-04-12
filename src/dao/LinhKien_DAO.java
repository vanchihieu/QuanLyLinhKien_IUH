package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import helpers.ConnectDB;
import model.LinhKien;
import model.LoaiLinhKien;

public class LinhKien_DAO {
	public List<LinhKien> getAllLinhKien(){
		 List<LinhKien>  dsLinhKien= new ArrayList<LinhKien>();
		 ConnectDB.getInstance();
		 Connection con= ConnectDB.getConnection();
		 try {
			 Statement  stm = con.createStatement();
			 ResultSet rs = stm.executeQuery("select * from LinhKien");
			 while(rs.next()) {
				 String ma =rs.getString(1);
				 String ten =rs.getString(2);
				 int soLuong =rs.getInt(3);
				 String diaChiAnh=rs.getString(4);
				 String maLoai =rs.getString(5);
				 String nhaCC =rs.getString(6);
				 double donGia =rs.getDouble(7);
				 LinhKien linhKien = new LinhKien(ma, ten, soLuong, diaChiAnh, maLoai, nhaCC, donGia);
				 dsLinhKien.add(linhKien);
			 }
		}  catch (SQLException e) {
		}
		 
		return dsLinhKien;
	}
	
	public List<LinhKien> getAllLinhKienMALLK(String maLoaiLinhKien) {
		List<LinhKien>  dsLinhKien= new ArrayList<LinhKien>();
		 ConnectDB.getInstance();
		 Connection con= ConnectDB.getConnection();
		 try {
			 PreparedStatement stm = con.prepareStatement("select * from LinhKien where maLoai=?");
			 stm.setNString(1, maLoaiLinhKien);
			 ResultSet rs= stm.executeQuery();
			 while(rs.next()) {
				 String ma =rs.getString(1);
				 String ten =rs.getString(2);
				 int soLuong =rs.getInt(3);
				 String diaChiAnh=rs.getString(4);
				 String maLoai =rs.getString(5);
				 String nhaCC =rs.getString(6);
				 double donGia =rs.getDouble(7);
				 LinhKien linhKien = new LinhKien(ma, ten, soLuong, diaChiAnh, maLoai, nhaCC, donGia);
				 dsLinhKien.add(linhKien);
			 }
		}  catch (SQLException e) {
		}
		 
		return dsLinhKien;
	}
	
	public List<LinhKien> getAllLinhKienMANCC(String maNCC){
		 List<LinhKien>  dsLinhKien= new ArrayList<LinhKien>();
		 ConnectDB.getInstance();
		 Connection con= ConnectDB.getConnection();
		 try {
			 PreparedStatement stm = con.prepareStatement("select * from LinhKien where maNCC=?");
			 stm.setNString(1, maNCC);
			 ResultSet rs= stm.executeQuery();
			 while(rs.next()) {
				 String ma =rs.getString(1);
				 String ten =rs.getString(2);
				 int soLuong =rs.getInt(3);
				 String diaChiAnh=rs.getString(4);
				 String maLoai =rs.getString(5);
				 String nhaCC =rs.getString(6);
				 double donGia =rs.getDouble(7);
				 LinhKien linhKien = new LinhKien(ma, ten, soLuong, diaChiAnh, maLoai, nhaCC, donGia);
				 dsLinhKien.add(linhKien);
			 }
		}  catch (SQLException e) {
		}
		 
		return dsLinhKien;
	}
	public List<LinhKien> getLinhKienTheoMa(String maLinhKien){
		 List<LinhKien>  dsLinhKien= new ArrayList<LinhKien>();
		 ConnectDB.getInstance();
		 Connection con= ConnectDB.getConnection();
		 String sql="select * from LinhKien where maLinhKien=? ";
		 try {
			 PreparedStatement  stm = con.prepareStatement(sql);
			 stm.setString(1, maLinhKien);
			 ResultSet rs = stm.executeQuery();
			 while(rs.next()) {
				 String ma =rs.getString(1);
				 String ten =rs.getString(2);
				 int soLuong =rs.getInt(3);
				 String diaChiAnh=rs.getString(4);
				 String maLoai =rs.getString(5);
				 String nhaCC =rs.getString(6);
				 double donGia =rs.getDouble(7);
				 LinhKien linhKien = new LinhKien(ma, ten, soLuong, diaChiAnh, maLoai, nhaCC, donGia);
				 dsLinhKien.add(linhKien);
			 }
		}  catch (SQLException e) {
		}
		 
		return dsLinhKien;
	}
	public List<LinhKien> getLinhKienTheoMaSP(String maloaiLinKien){
		 List<LinhKien>  dsLinhKien= new ArrayList<LinhKien>();
		 ConnectDB.getInstance();
		 Connection con= ConnectDB.getConnection();
		 String sql="select * from LinhKien where maLoai=?";
		 try {
			 PreparedStatement  stm = con.prepareStatement(sql);
			 stm.setString(1, maloaiLinKien);
			 ResultSet rs = stm.executeQuery();
			 System.out.println(rs.toString());
			 while(rs.next()) {
				 String ma =rs.getString(1);
				 String ten =rs.getString(2);
				 int soLuong =rs.getInt(3);
				 String diaChiAnh=rs.getString(4);
				 String maLoai =rs.getString(5);
				 String nhaCC =rs.getString(6);
				 double donGia =rs.getDouble(7);
				 LinhKien linhKien = new LinhKien(ma, ten, soLuong, diaChiAnh, maLoai, nhaCC, donGia);
				 dsLinhKien.add(linhKien);
			 }
		}  catch (SQLException e) {
		}
		 
		return dsLinhKien;
	}
	public boolean themLinhKien(LinhKien lk) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm =null;
		int n=0;
		try {
			stm =con.prepareStatement("insert into LinhKien values (?, ?, ?, ?, ?, ?, ?)");
			stm.setString(1, lk.getMaLinhKien());
			stm.setString(2, lk.getTenLinhKien());
			stm.setInt(3, lk.getSoLuong());
			stm.setString(4, lk.getDiaChiHinhAnh());
			stm.setString(5, lk.getMaLoai());
			stm.setString(6, lk.getMaNhaCungCap());
			stm.setDouble(7, lk.getDonGia());
			n=stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public boolean xoaLinhKien(String maLinhKien) throws SQLException{
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm =null;
		String sql ="delete LinhKien where maLinhKien=?";
		int n=0;
		try {
			stm =con.prepareStatement(sql);
			stm.setString(1, maLinhKien);
			n=stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			stm.close();
		}
		return n > 0;
	}
	public boolean xoaLinhKienTheoMaNhaCungCap(String maNhaCungCap) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		int n=0;
		String sql ="Delete LinhKien where maNCC=?";
		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, maNhaCungCap);
			n = stm.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return n>0;
	}
	public boolean xoaLinhKienTheoMaLoaiLinhKien(String maLLK) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		int n=0;
		String sql ="Delete LinhKien where maLoai=?";
		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, maLLK);
			n = stm.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return n>0;
	}
	
	public boolean capNhatLinhKien(LinhKien lk) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm =null;
		String sql ="update LinhKien set tenLinhKien=?, "
				+ "soLuongTon=?, diaChiHinhAnh=?, maLoai =?, maNCC=?, donGia=? where maLinhKien=?";
		int n=0;
		try {
			stm =con.prepareStatement(sql);
			stm.setString(1, lk.getTenLinhKien());
			stm.setInt(2, lk.getSoLuong());
			stm.setString(3, lk.getDiaChiHinhAnh());
			stm.setString(4, lk.getMaLoai());
			stm.setString(5, lk.getMaNhaCungCap());
			stm.setDouble(6, lk.getDonGia());
			stm.setString(7, lk.getMaLinhKien());
			n=stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			stm.close();
		}
		return n > 0;
	}
}

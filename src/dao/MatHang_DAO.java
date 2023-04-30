package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import helpers.ConnectDB;
import model.MatHangEntity;

public class MatHang_DAO {
	public ArrayList<MatHangEntity> getMatHang(){
		ArrayList<MatHangEntity> listMH = new ArrayList<MatHangEntity>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from chiTietHoaDon as ct join LinhKien as lk on ct.maLinhKien = lk.maLinhKien where soLuong >=2";
//			String sql = "select * from LinhKien where soLuongTon >=2";

			Statement statement = con.createStatement();
			
			ResultSet re = statement.executeQuery(sql);
			while(re.next()) {
//				listMH.add(new MatHangEntity(re.getString("maLinhKien"), re.getString("tenLinhKien"), re.getInt("soLuongTon")));
				listMH.add(new MatHangEntity(re.getString(6), re.getString(8), re.getInt(3)));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listMH;
	}
}

package UI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.KHTM_DAO;
import helpers.ConnectDB;
import model.KhachHangEntity;

import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.Color;

public class KhachHangTiemNang extends JPanel {
	private DefaultTableModel modelNhanVien;
	private JTable tableNhanVien;
	
	private KHTM_DAO khachHang_Dao = new KHTM_DAO();
	/**
	 * Create the panel.
	 */
	public KhachHangTiemNang() throws SQLException{
		ConnectDB.getInstance().connect();
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Khách Hàng Tiềm Năng");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(272, 10, 246, 25);
		add(lblNewLabel);
		
		String[] colHeader = { "Mã Khách Hàng", "Tên Khách Hàng", "Số Lượng Hóa Đơn" };
		modelNhanVien = new DefaultTableModel(colHeader, 0);
		tableNhanVien = new JTable(modelNhanVien);
		
		JScrollPane scrollPane = new JScrollPane(tableNhanVien);
		add(scrollPane);
		scrollPane.setBounds(23, 45, 685, 206);
		
		while (modelNhanVien.getRowCount() != 0) {
			modelNhanVien.removeRow(0);
		}
		ArrayList<KhachHangEntity> listMH = khachHang_Dao.getKhachHang();
		for (KhachHangEntity matHang : listMH) {
			String data[] = { matHang.getMaKH(), matHang.getTenKH(), matHang.getSoLuongHD() + "" };
			modelNhanVien.addRow(data);
		}
	}
}

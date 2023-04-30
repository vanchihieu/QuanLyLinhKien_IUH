package UI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.MatHang_DAO;
import helpers.ConnectDB;
import model.MatHangEntity;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;

public class MatHang extends JPanel {
	private DefaultTableModel modelNhanVien;
	private JTable tableNhanVien;
	private MatHang_DAO matHang_Dao = new MatHang_DAO();

	/**
	 * Create the panel.
	 */
	public MatHang() throws SQLException{
		ConnectDB.getInstance().connect();
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("MẶT HÀNG");
		lblNewLabel.setBounds(190, 10, 71, 20);
		add(lblNewLabel);
		
		String[] colHeader = { "Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng Bán Được" };
		modelNhanVien = new DefaultTableModel(colHeader, 0);
		
		tableNhanVien = new JTable(modelNhanVien);
		JScrollPane scrollPane = new JScrollPane(tableNhanVien);
		add(scrollPane);
		scrollPane.setBounds(38, 40, 439, 192);
		
		docDuLieu();
		
	}
	private void docDuLieu() {

		while (modelNhanVien.getRowCount() != 0) {
			modelNhanVien.removeRow(0);
		}
		ArrayList<MatHangEntity> listMH = matHang_Dao.getMatHang();
		for (MatHangEntity matHang : listMH) {
			String data[] = { matHang.getMaHang(), matHang.getTenHang(), matHang.getSoLuong() + "" };
			modelNhanVien.addRow(data);
		}
		tableNhanVien.setModel(modelNhanVien);
	}
}
